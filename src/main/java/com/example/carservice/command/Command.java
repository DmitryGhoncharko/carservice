package com.example.carservice.command;



import com.example.carservice.exception.ServiceError;

public interface Command {

    CommandResponse execute(CommandRequest request) throws ServiceError;

}
