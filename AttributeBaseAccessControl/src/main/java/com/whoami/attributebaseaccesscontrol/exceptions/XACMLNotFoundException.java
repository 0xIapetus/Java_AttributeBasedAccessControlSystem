package com.whoami.attributebaseaccesscontrol.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class XACMLNotFoundException extends RuntimeException
{
    public XACMLNotFoundException(String message)
    {
        super(message);
    }
}