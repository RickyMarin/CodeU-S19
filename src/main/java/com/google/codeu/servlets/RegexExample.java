package com.google.codeu.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.codeu.data.Datastore;
import com.google.gson.JsonObject;

//stats page will currently only display total number of messages

public class RegexExample{
    public static void main(String[] args){
        String regex = "(https?://\\S+\\.(png|jpg|gif))";
        String replacement = "<img src=\"$1\" />";

        String text = "here is an image: https://example.com/images/cat.jpg";

        String result = text.replaceAll(regex, replacement);
        System.out.println(result);
    }
}
