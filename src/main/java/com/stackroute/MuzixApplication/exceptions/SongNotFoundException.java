package com.stackroute.MuzixApplication.exceptions;

public class SongNotFoundException extends Exception
{
    private String message2;

    public SongNotFoundException(String message2)
    {
        super(message2);
        this.message2 = message2;
    }

    public SongNotFoundException()
    {
        super();
    }
}
