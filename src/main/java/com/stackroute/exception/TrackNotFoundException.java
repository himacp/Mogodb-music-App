package com.stackroute.exception;

public class TrackNotFoundException extends Exception{
    private String message;

    public TrackNotFoundException(String message){
        super(message);
    }

}

