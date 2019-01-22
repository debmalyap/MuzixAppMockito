package com.stackroute.MuzixApplication.controller;

import com.stackroute.MuzixApplication.domain.Muzix;
import com.stackroute.MuzixApplication.exceptions.SongAlreadyExistsException;
import com.stackroute.MuzixApplication.exceptions.SongNotFoundException;
import com.stackroute.MuzixApplication.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class SongController
{
    public SongService songService;
    public ResponseEntity responseEntity;

    @Autowired
    public SongController(SongService songService)
    {
        this.songService = songService;
    }

    @PostMapping("muzix")
    public ResponseEntity<?>saveMuzix(@RequestBody Muzix muzix)
    {
        //ResponseEntity responseEntity=null;
        try
        {
            songService.saveMuzix(muzix);//Call to saveUser() passing the user object recieved from request//
            responseEntity=new ResponseEntity<Muzix>(muzix, HttpStatus.CREATED);
        }
        catch (SongAlreadyExistsException exception)
        {
            responseEntity=new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("muzix")
    public ResponseEntity<?> getAllMuzix()
    {
        return new ResponseEntity<List<Muzix>>(songService.getAllMuzix(),HttpStatus.OK);
    }


    @PutMapping("muzix")
    public ResponseEntity<?> updateMuzix(@RequestBody Muzix muzix)
    {
        try
        {
            songService.updateMuzix(muzix);
            responseEntity=new ResponseEntity<Muzix>(muzix,HttpStatus.OK);

        }
        catch (SongNotFoundException ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.OK);
        }
        return responseEntity;
    }


    @DeleteMapping("muzix/{songid}")
    public ResponseEntity<?> delTrack(@PathVariable("songid") int songid) throws SongNotFoundException
    {
       try
       {
           songService.deleteMuzix(songid);
           responseEntity=new ResponseEntity<String>("Deleted data",HttpStatus.OK);
       }
       catch (SongNotFoundException ex)
       {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.OK);
       }
       return responseEntity;
    }
}
