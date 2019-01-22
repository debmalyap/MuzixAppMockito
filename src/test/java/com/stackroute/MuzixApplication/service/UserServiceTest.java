package com.stackroute.MuzixApplication.service;

import com.stackroute.MuzixApplication.domain.Muzix;
import com.stackroute.MuzixApplication.exceptions.SongAlreadyExistsException;
import com.stackroute.MuzixApplication.exceptions.SongNotFoundException;
import com.stackroute.MuzixApplication.repository.SongRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest
{
    Muzix muzix;

    //@Mock will create a mock implementation//
    @Mock
    SongRepository songRepository;

    //@InjectMocks will inject the mocks marked with @Mock to this instance when it is created.
    @InjectMocks
    SongServiceImpl songService;
    List<Muzix> list=null;

    @Before
    public void setup()
    {
        //mockito initialization//
        MockitoAnnotations.initMocks(this);
        muzix=new Muzix();
        muzix.setSongid(12);
        muzix.setSongname("Venom");
        muzix.setComment("Eminem");
        list=new ArrayList<>();
        list.add(muzix);
    }

    @Test
    public void saveMuzixTestSuccess()  throws SongAlreadyExistsException
    {
        when(songRepository.save((Muzix) any())).thenReturn(muzix);
        Muzix savedMuzix=songService.saveMuzix(muzix);
        Assert.assertEquals(muzix,savedMuzix);
        verify(songRepository,times(1)).save(muzix);
    }

    @Test(expected =SongAlreadyExistsException.class)
    public void saveMuzixTestFailure() throws SongAlreadyExistsException
    {
        when(songRepository.save((Muzix) any())).thenReturn(null);
        Muzix savedUser = songService.saveMuzix(muzix);
        System.out.println("savedUser" + savedUser);
        Assert.assertEquals(muzix,savedUser);
    }

    @Test
    public void getAllMuzix()
    {
        songRepository.save(muzix);
        when(songRepository.findAll()).thenReturn(list);
        List<Muzix> muzixlist=songService.getAllMuzix();
        Assert.assertEquals(list,muzixlist);
    }

//    @Test
//    public void updateMuzixTestSuccess() throws SongNotFoundException
//    {
//        //when(songRepository.save((Muzix)any())).thenReturn(muzix);
//        Muzix updateMuzix=songService.updateMuzix(muzix);
//        Assert.assertEquals(muzix,updateMuzix);
//        verify(songRepository,times(1)).save(muzix);
//    }

    @Test(expected =SongNotFoundException.class)
    public void updateMuzixTestFailure() throws SongNotFoundException
    {
        when(songRepository.save((Muzix) any())).thenReturn(null);
        Muzix updatedMuzix = songService.updateMuzix(muzix);
        System.out.println("updatedMuzix" + updatedMuzix);
        Assert.assertEquals(muzix,updatedMuzix);
    }

//    @Test
//    public void deleteMuzixTestSuccess() throws SongNotFoundException
//    {
//        //when(songRepository.save((Muzix)any())).thenReturn(muzix);
//        boolean deleteMuzix=songService.deleteMuzix(muzix.getSongid());
//        Assert.assertEquals(muzix,deleteMuzix);
//        verify(songRepository,times(1)).save(muzix);
//    }

    @Test(expected = SongNotFoundException.class)
    public void deleteMuzixTestFailure() throws SongNotFoundException
    {
        //when(songRepository.save((Muzix)any())).thenReturn(null);
        boolean deletedMuzix=songService.deleteMuzix(muzix.getSongid());
        System.out.println("deletedMuzix: "+deletedMuzix);
        Assert.assertEquals(muzix,deletedMuzix);
    }
}
