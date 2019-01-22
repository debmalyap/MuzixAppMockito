package com.stackroute.MuzixApplication.feedcomponent;

import com.stackroute.MuzixApplication.domain.Muzix;
import com.stackroute.MuzixApplication.repository.SongRepository;
import com.stackroute.MuzixApplication.service.SongService;
//import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
//@Data
@PropertySource("application.properties")
public class SeedDataCommandLineRunner implements CommandLineRunner
{
    private Muzix feedMuzixData=new Muzix();

    @Value("${song.total}")
    private int totalSongs;

    @Value("${song.1.songid}")
    private int songid1;
    @Value("${song.1.songname}")
    private String songname1;
    @Value("${song.1.comment}")
    private String comment1;

    @Value("${song.2.songid}")
    private int songid2;
    @Value("${song.2.songname}")
    private String songname2;
    @Value("${song.2.comment}")
    private String comment2;

    @Value("${song.3.songid}")
    private int songid3;
    @Value("${song.3.songname}")
    private String songname3;
    @Value("${song.3.comment}")
    private String comment3;


    @Autowired
    Environment environment;
    @Autowired
    private SongService songService;

    @Override
    public void run(String... args) throws Exception
    {
        System.out.println("app.name");
        System.out.println("JAVA_HOME");

        feedMuzixData.setSongid(songid1);
        feedMuzixData.setSongname(songname1);
        feedMuzixData.setComment(comment1);
        songService.saveMuzix(feedMuzixData);

        feedMuzixData.setSongid(songid2);
        feedMuzixData.setSongname(songname2);
        feedMuzixData.setComment(comment2);
        songService.saveMuzix(feedMuzixData);

        feedMuzixData.setSongid(songid3);
        feedMuzixData.setSongname(songname3);
        feedMuzixData.setComment(comment3);
        songService.saveMuzix(feedMuzixData);
    }
}