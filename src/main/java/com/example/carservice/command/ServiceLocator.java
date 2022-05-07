package com.example.carservice.command;

public interface ServiceLocator {
    Command getCommand(String commandName);
}
