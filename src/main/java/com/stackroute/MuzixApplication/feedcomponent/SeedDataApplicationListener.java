//package com.stackroute.MuzixApplication.feedcomponent;
//
//import com.stackroute.MuzixApplication.domain.Muzix;
//import com.stackroute.MuzixApplication.exceptions.SongAlreadyExistsException;
//import com.stackroute.MuzixApplication.exceptions.SongNotFoundException;
//import com.stackroute.MuzixApplication.repository.SongRepository;
//import com.stackroute.MuzixApplication.service.SongService;
////import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//@Component
////@Data
//@Primary
//public class SeedDataApplicationListener implements ApplicationListener<ContextRefreshedEvent>
//{
//    @Autowired
//    Environment environment;
//    @Autowired
//    SongService songService;
//    Muzix feedMuzixData=new Muzix();
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event)
//    {
//        feedMuzixData.setSongid(Integer.parseInt(environment.getProperty("song.1.songid")));
//        feedMuzixData.setSongname(environment.getProperty("song.1.songname"));
//        feedMuzixData.setComment(environment.getProperty("song.1.comment"));
//        try {
//            songService.saveMuzix(feedMuzixData);
//        } catch (SongAlreadyExistsException e) {
//            e.printStackTrace();
//        }
//
//        feedMuzixData.setSongid(Integer.parseInt(environment.getProperty("song.2.songid")));
//        feedMuzixData.setSongname(environment.getProperty("song.2.songname"));
//        feedMuzixData.setComment(environment.getProperty("song.2.comment"));
//        try {
//            songService.saveMuzix(feedMuzixData);
//        } catch (SongAlreadyExistsException e) {
//            e.printStackTrace();
//        }
//
//        feedMuzixData.setSongid(Integer.parseInt(environment.getProperty("song.3.songid")));
//        feedMuzixData.setSongname(environment.getProperty("song.3.songname"));
//        feedMuzixData.setComment(environment.getProperty("song.3.comment"));
//        try {
//            songService.saveMuzix(feedMuzixData);
//        } catch (SongAlreadyExistsException e) {
//            e.printStackTrace();
//        }
//    }
//}