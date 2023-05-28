package com.akkulov.service.command;

public interface Command<RequestT, ResponseT> {

  ResponseT execute(RequestT requestT);
}
