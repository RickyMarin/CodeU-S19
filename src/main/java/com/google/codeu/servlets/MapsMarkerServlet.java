package com.google.codeu.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.google.codeu.data.Datastore;
import com.google.codeu.data.User;
import com.google.gson.Gson;

/**
 * Handles fetching and saving {@link User} instances.
 */
@WebServlet("/user-markers")
public class MapsMarkerServlet extends HttpServlet {

    private Datastore datastore;

    @Override
    public void init() {
        datastore = new Datastore();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        List<User> markers = datastore.getMarkers();
        Gson gson = new Gson();
        String json = gson.toJson(markers);

        response.getOutputStream().println(json);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        double lat = Double.parseDouble(request.getParameter("lat"));
        double lng = Double.parseDouble(request.getParameter("lng"));
        String content = Jsoup.clean(request.getParameter("content"), Whitelist.none());

        User marker = new User(lat, lng, content);
        datastore.storeMarker(marker);
    }
}
