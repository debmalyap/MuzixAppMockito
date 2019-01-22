package com.stackroute.MuzixApplication.repository;

import com.stackroute.MuzixApplication.domain.Muzix;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
//@TestPropertySource("application.properties")
public class UserRepositoryTest
{
    @Autowired
    private SongRepository songRepository;
    private Muzix muzix;

    @Before
    public void setUp()
    {
        muzix=new Muzix();
        muzix.setSongid(20);
        muzix.setSongname("mere nam tu");
        muzix.setComment("zero");
    }

    @After
    public void tearDown()
    {
        songRepository.deleteAll();
    }

    @Test
    public void testSaveUserSuccess()
    {
        songRepository.save(muzix);
        Muzix fetchmuzix=songRepository.findById(muzix.getSongid()).get();
        assertEquals(20, fetchmuzix.getSongid());
    }

    @Test
    public void testSaveUserFailure()
    {
        Muzix testUser = new Muzix(24,"mere","srk");
        songRepository.save(muzix);
        Muzix fetchMuzix = songRepository.findById(muzix.getSongid()).get();
        Assert.assertNotSame(testUser,muzix);
    }

    @Test
    public void testUpdateUserSuccess()
    {
        Muzix testUser=songRepository.save(muzix);
        Muzix updatemuzix=new Muzix();
        updatemuzix.setSongid(testUser.getSongid());
        updatemuzix.setSongname(testUser.getSongname());
        updatemuzix.setComment(testUser.getComment());
        Muzix savedMuzix=songRepository.save(updatemuzix);
        Muzix newMuzix=songRepository.findById(savedMuzix.getSongid()).get();
        Assert.assertNotSame(updatemuzix,newMuzix);
    }

    @Test
    public void testUpdateUserFailure()
    {
        Muzix testUser=songRepository.save(muzix);
        Muzix updatemuzix=new Muzix();
        updatemuzix.setSongid(testUser.getSongid());
        updatemuzix.setSongname(testUser.getSongname());
        updatemuzix.setComment(testUser.getComment());
        Muzix savedMuzix=songRepository.save(updatemuzix);
        Muzix newMuzix=songRepository.findById(savedMuzix.getSongid()).get();
        Assert.assertNotSame(updatemuzix,newMuzix);
    }


}
