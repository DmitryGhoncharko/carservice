package com.example.carservice.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private static final Logger LOG = LoggerFactory.getLogger(Cache.class);
    private final Map<String, Command> commandCache = new ConcurrentHashMap<>();


    public Optional<Command> getCommand(String commandName) {
        LOG.info("Get command by name from cache");
        if(commandName!=null){
            return Optional.ofNullable(commandCache.get(commandName));
        }
        return Optional.empty();
    }

    public void addCommand(String commandName, Command command) {
        LOG.info("Add command into cache");
        commandCache.put(commandName, command);
    }
}
