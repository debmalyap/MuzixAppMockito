package com.stackroute.MuzixApplication.domain;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Muzix
{
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    public int songid;
    public String songname;
    public String comment;

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Muzix(int songid, String songname, String comment)
    {
        this.songid = songid;
        this.songname = songname;
        this.comment = comment;
    }

    public Muzix()
    {

    }

    @Override
    public String toString()
    {
        return "Songid:" + songid + " Songname: " + songname + " Comment: " + comment;
    }
}
