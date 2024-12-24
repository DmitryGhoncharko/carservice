package com.example.carservice.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class SimpleServiceLocator implements ServiceLocator {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleServiceLocator.class);
    private static final Cache CACHE = new Cache();

    @Override
    public Command getCommand(String commandName) {
        final Optional<Command> commandFromCache = CACHE.getCommand(commandName);
        if(commandName==null){
            commandName = "mainPage";
        }
        if (commandFromCache.isPresent()) {
            return commandFromCache.get();
        }
            final InitialContext initialContext = new InitialContext();
        String dt = null;
        if(commandName.contains("?")){
                dt = commandName.split("\\?")[0];
                commandName = dt;
        }

            final Command command = initialContext.lookup(commandName);
            CACHE.addCommand(commandName, command);
            LOG.info("Get command in Service Locator from Cache");
            return command;

    }
}
