package org.exercise4.students.peter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ReqResClient {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static void listUsers() throws IOException, InterruptedException {
        String url = "https://reqres.in/api/users?page=2";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response (List Users): " + response.body());
    }

    public static void getUser(int userId) throws IOException, InterruptedException {
        String url =  "https://reqres.in/api/users/" + userId;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            System.out.println("User Details: " + response.body());
        } else {
            System.out.println("User not found.");
        }
    }

    public static void deleteUser(int userId) throws IOException, InterruptedException {
        String url = "https://reqres.in/api/users/" + userId;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 204) {
            System.out.println("User successfully deleted.");
        } else {
            System.out.println("Couldn't delete user / User not found.");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        listUsers();
        getUser(6);
        getUser(19);
        deleteUser(3);
    }
}
