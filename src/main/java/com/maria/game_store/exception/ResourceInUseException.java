package com.maria.game_store.exception;

public class ResourceInUseException extends RuntimeException{
    public ResourceInUseException(String message) {
        super(message);
    }
}
