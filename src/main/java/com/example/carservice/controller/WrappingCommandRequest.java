package com.example.carservice.controller;


import com.example.carservice.command.CommandRequest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public class WrappingCommandRequest implements CommandRequest {

    private final HttpServletRequest request;

    public WrappingCommandRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void addAttributeToJsp(String name, Object attribute) {
        request.setAttribute(name, attribute);
    }

    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }

    @Override
    public boolean sessionExists() {
        return request.getSession(false) != null;
    }

    @Override
    public boolean addToSession(String name, Object value) {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute(name, value);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Object> retrieveFromSession(String name) {
        return Optional.ofNullable(request.getSession(false))
                .map(session -> session.getAttribute(name));
    }

    @Override
    public void clearSession() {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public void removeFromSession(String name) {
        request.getSession().removeAttribute(name);
    }

    @Override
    public void createSession() {
        request.getSession(true);
    }

    @Override
    public String getURI() {return String.valueOf(request.getRequestURI());}

    @Override
    public Part getPart(String name) throws ServletException, IOException {
        return request.getPart(name);
    }

    @Override
    public ServletContext getServletContext() {
        return request.getServletContext();

    }

    @Override
    public Collection<Part> getParts() throws ServletException, IOException {
        return request.getParts();
    }

    @Override
    public String getUrl() {
        return request.getRequestURL().toString();
    }

    @Override
    public String getQueryString() {
        return request.getQueryString();
    }
}