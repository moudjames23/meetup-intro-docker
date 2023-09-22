package com.moudjames23.springcollob.exceptions;

public class ResourceAlreadyExistException extends RuntimeException{

    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}
