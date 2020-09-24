package com.example.bootpractice.controller;

import com.example.bootpractice.model.BackendDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Nawa
 * on 9/24/20.
 * (c)Marathon Computer Systems
 */
public class BackendHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("caught here....");
        ObjectMapper objectMapper = new ObjectMapper();
        String greeting = request.getParameter("greeting");

        BackendDTO backendDTO = new BackendDTO();
        backendDTO.setGreeting(greeting + " from cluster Backend");
        backendDTO.setTime(System.currentTimeMillis());
        backendDTO.setIp(getIp());

        PrintWriter out = response.getWriter();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(out, response);
    }


    private String getIp() {
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
        return hostname;
    }
}
