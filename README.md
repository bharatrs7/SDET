# FanCode Automation

FanCode User Task Completion Automation
Project Overview
This project automates the verification of users from a specific city, identified as "FanCode," to ensure they have completed more than 50% of their todos tasks.

City Identification
FanCode users are identified based on:
Latitude: Between -40 and 5
Longitude: Between 5 and 100
APIs Used
Fetch Users:
http://jsonplaceholder.typicode.com/users
Retrieves the list of all users, including their geographic location (latitude and longitude).

Fetch Todos:
http://jsonplaceholder.typicode.com/todos
Retrieves the todos list for each user. Each user has multiple tasks, some of which may be marked as completed.

Task Objective
Ensure that all users from the FanCode city have completed more than 50% of their todos tasks.
A user is considered valid for this check if they meet the geographic location criteria (latitude and longitude).
Workflow
Fetch user data from the Users API.
Identify users from FanCode city based on the latitude and longitude constraints.
Fetch todos data for each FanCode user.
Calculate the percentage of completed todos for each user.
Verify that each user has completed more than 50% of their todos tasks.
Log the results indicating whether each user passed the criteria or not.

