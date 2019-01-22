package com.stackroute.MuzixApplication.service;

import com.stackroute.MuzixApplication.domain.Muzix;
import com.stackroute.MuzixApplication.exceptions.SongAlreadyExistsException;
import com.stackroute.MuzixApplication.exceptions.SongNotFoundException;

import java.util.List;

public interface SongService
{
    public Muzix saveMuzix(Muzix muzix) throws SongAlreadyExistsException;

    public List<Muzix> getAllMuzix();

    public boolean deleteMuzix(int songid)throws SongNotFoundException;
    public Muzix updateMuzix(Muzix muzix) throws SongNotFoundException;

}
