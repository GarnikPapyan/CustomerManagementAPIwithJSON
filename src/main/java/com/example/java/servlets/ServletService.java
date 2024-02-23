package com.example.java.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

public class ServletService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        CustomersService customersService =  new CustomersService();
        List<Customers> list = customersService.getAllCustomers();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonCustomers = gson.toJson(list);
        resp.setContentType("application/json");
        resp.setContentType("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(LocalDateTime.now() + "\n");
        out.write(jsonCustomers);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        reader.close();
        String jsonString = jsonBuilder.toString();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

        String name = jsonObject.get("name").getAsString();
        String email = jsonObject.get("email").getAsString();
        String address = jsonObject.get("address").getAsString();

        CustomersService customersService =  new CustomersService();
        customersService.createCustomer(name,email,address);
        resp.getWriter().println("Customer added successfully!");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Long customerId = Long.parseLong(req.getParameter("customerId"));

        CustomersService customersService = new CustomersService();
        customersService.deleteCustomer(customerId);
        resp.getWriter().println("Customer deleted successfully!");
    }
}
