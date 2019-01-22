package com.stackroute.MuzixApplication.service;

import com.stackroute.MuzixApplication.domain.Muzix;
import com.stackroute.MuzixApplication.exceptions.SongAlreadyExistsException;
import com.stackroute.MuzixApplication.exceptions.SongNotFoundException;
import com.stackroute.MuzixApplication.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService
{
    private SongRepository songRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository)
    {
        this.songRepository = songRepository;
    }

    //save-track method//
    @Override
    public Muzix saveMuzix(Muzix muzix) throws SongAlreadyExistsException
    {

        if(songRepository.existsById(muzix.getSongid()))
        {
            throw new SongAlreadyExistsException("Songs already exists");
        }
        Muzix savedMuzix= songRepository.save(muzix);
        if(savedMuzix==null)
        {
            throw new SongAlreadyExistsException();
        }
        return savedMuzix;
    }

    //display all-tracks method//
    @Override
    public List<Muzix> getAllMuzix()
    {
        return songRepository.findAll();
    }

    @Override
    public boolean deleteMuzix(int songid) throws SongNotFoundException
    {
        boolean deletestatus=false;
        if(songRepository.existsById(songid))
        {
            songRepository.deleteById(songid);
            deletestatus=true;
            return deletestatus;
        }
        else
        {
            throw new SongNotFoundException();
        }
    }

    @Override
    public Muzix updateMuzix(Muzix muzix) throws SongNotFoundException
    {
        if(songRepository.existsById(muzix.getSongid()))
        {
            Muzix updateMuzix=songRepository.save(muzix);
            return updateMuzix;
        }
        else
        {
            throw new SongNotFoundException();
        }
    }
}

