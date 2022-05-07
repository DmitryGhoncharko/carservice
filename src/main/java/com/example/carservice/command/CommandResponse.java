package com.example.carservice.command;

public interface CommandResponse {

    boolean isRedirect();

    String getPath();

}