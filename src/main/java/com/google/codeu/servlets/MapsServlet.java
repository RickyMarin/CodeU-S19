package com.google.codeu.servlets;

import java.io.IOException;
import java.util.Scanner;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

/**
 * Returns Map data as a JSON array, e.g. [{"lat": 38.4404675, "lng": -122.7144313}]
 */
@WebServlet("/TestRestaurants")
public class MapsServlet extends HttpServlet {

    JsonArray MapsArray;

    @Override
    public void init() {
        MapsArray = new JsonArray();
        Gson gson = new Gson();
        Scanner scanner = new Scanner(getServletContext().getResourceAsStream("/WEB-INF/TestRestaurants.csv"));
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] cells = line.split(",");

           // String state = cells[0];
            double lat = Double.parseDouble(cells[0]);
            double lng = Double.parseDouble(cells[1]);

            MapsArray.add(gson.toJsonTree(new Restaurant(lat, lng)));
        }
        scanner.close();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getOutputStream().println(MapsArray.toString());
    }

    private static class Restaurant{

        double lat;
        double lng;

        private Restaurant(double lat, double lng) {

            this.lat = lat;
            this.lng = lng;
        }
    }
}

