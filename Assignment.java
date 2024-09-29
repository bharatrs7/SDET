package com.fancode.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;

public class FanCodeTaskChecker {

    // Base URI for the API
    static String BASE_URI = "http://jsonplaceholder.typicode.com";

    public static void main(String[] args) {

        // Fetch all users in FanCode city
        List<Integer> fancodeUsers = getFanCodeUsers();

        // Check task completion for each FanCode user
        for (Integer userId : fancodeUsers) {
            checkUserTodoCompletion(userId);
        }

        // Additional methods to fetch and process other resources
        fetchPosts();
        fetchComments();
        fetchAlbums();
        fetchPhotos();
    }

    // Fetch users belonging to FanCode city (lat between -40 to 5, long between 5 to 100)
    public static List<Integer> getFanCodeUsers() {
        Response response = RestAssured.get(BASE_URI + "/users");

        List<Integer> fanCodeUserIds = response.jsonPath()
                .getList("findAll { user -> user.address.geo.lat.toDouble() >= -40 && user.address.geo.lat.toDouble() <= 5 && user.address.geo.lng.toDouble() >= 5 && user.address.geo.lng.toDouble() <= 100 }.id");

        System.out.println("FanCode User IDs: " + fanCodeUserIds);
        return fanCodeUserIds;
    }

    // Check if a user's completed tasks are more than 50%
    public static void checkUserTodoCompletion(int userId) {
        Response response = RestAssured.get(BASE_URI + "/todos?userId=" + userId);
        List<Boolean> todos = response.jsonPath().getList("completed");

        // Calculate completed tasks percentage
        long totalTasks = todos.size();
        long completedTasks = todos.stream().filter(Boolean::booleanValue).count();
        double completionPercentage = (double) completedTasks / totalTasks * 100;

        System.out.println("User ID: " + userId + " - Completed: " + completionPercentage + "%");
        if (completionPercentage > 50) {
            System.out.println("User has completed more than 50% tasks.");
        } else {
            System.out.println("User has NOT completed more than 50% tasks.");
        }
    }

    // Fetch posts
    public static void fetchPosts() {
        Response response = RestAssured.get(BASE_URI + "/posts");
        List<String> postTitles = response.jsonPath().getList("title");
        System.out.println("\nPost Titles:");
        postTitles.forEach(System.out::println);
    }

    // Fetch comments
    public static void fetchComments() {
        Response response = RestAssured.get(BASE_URI + "/comments");
        List<String> commentBodies = response.jsonPath().getList("body");
        System.out.println("\nComment Bodies:");
        commentBodies.forEach(System.out::println);
    }

    // Fetch albums
    public static void fetchAlbums() {
        Response response = RestAssured.get(BASE_URI + "/albums");
        List<String> albumTitles = response.jsonPath().getList("title");
        System.out.println("\nAlbum Titles:");
        albumTitles.forEach(System.out::println);
    }

    // Fetch photos
    public static void fetchPhotos() {
        Response response = RestAssured.get(BASE_URI + "/photos");
        List<String> photoTitles = response.jsonPath().getList("title");
        System.out.println("\nPhoto Titles:");
        photoTitles.forEach(System.out::println);
    }
}
