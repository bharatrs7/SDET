import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;
import java.util.stream.Collectors;

public class FanCodeTaskChecker {

    // Base URI for the API
    static String BASE_URI = "http://jsonplaceholder.typicode.com";

    public static void main(String[] args) {
        // Fetch all users
        List<Integer> fancodeUsers = getFanCodeUsers();

        // Check if all FanCode users have more than 50% tasks completed
        for (Integer userId : fancodeUsers) {
            checkUserTodoCompletion(userId);
        }
    }

    // Fetch users who belong to the FanCode city
    public static List<Integer> getFanCodeUsers() {
        Response response = RestAssured.get(BASE_URI + "/users");
        List<Integer> fanCodeUserIds = response.jsonPath().getList("findAll { it.address.geo.lat.toDouble() >= -40 && it.address.geo.lat.toDouble() <= 5 && it.address.geo.lng.toDouble() >= 5 && it.address.geo.lng.toDouble() <= 100 }.id");
        return fanCodeUserIds;
    }

    // Check if a user's completed tasks percentage is greater than 50%
    public static void checkUserTodoCompletion(int userId) {
        Response response = RestAssured.get(BASE_URI + "/todos?userId=" + userId);
        List<Boolean> todos = response.jsonPath().getList("completed");

        // Calculate percentage of completed tasks
        long totalTasks = todos.size();
        long completedTasks = todos.stream().filter(Boolean::booleanValue).count();
        double completionPercentage = (double) completedTasks / totalTasks * 100;

        System.out.println("User ID: " + userId + " - Completion Percentage: " + completionPercentage + "%");
        if (completionPercentage > 50) {
            System.out.println("Status: User has more than 50% tasks completed.");
        } else {
            System.out.println("Status: User has less than 50% tasks completed.");
        }
    }
}
