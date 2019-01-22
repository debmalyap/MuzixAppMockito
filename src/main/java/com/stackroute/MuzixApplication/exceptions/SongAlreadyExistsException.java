package com.stackroute.MuzixApplication.exceptions;

public class SongAlreadyExistsException extends Exception
{
    private String message1;

    public SongAlreadyExistsException(String message1)
    {
        super(message1);
        this.message1 = message1;
    }

    public SongAlreadyExistsException()
    {
        super();
    }
}
