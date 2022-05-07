package com.example.carservice.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface CommandRequest {

    void addAttributeToJsp(String name, Object attribute);

    String getParameter(String name);

    boolean sessionExists();

    boolean addToSession(String name, Object value);

    Optional<Object> retrieveFromSession(String name);

    void clearSession();

    void createSession();

    void removeFromSession(String name);

    String getURI();

    Part getPart(String name) throws ServletException, IOException;

    ServletContext getServletContext();

    Collection<Part> getParts() throws ServletException, IOException;
}