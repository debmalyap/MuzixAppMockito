package com.stackroute.MuzixApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.MuzixApplication.domain.Muzix;
import com.stackroute.MuzixApplication.exceptions.SongAlreadyExistsException;
import com.stackroute.MuzixApplication.exceptions.SongNotFoundException;
import com.stackroute.MuzixApplication.repository.SongRepository;
import com.stackroute.MuzixApplication.service.SongServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest
{
    @Autowired
    private MockMvc mockMvc;
    private Muzix muzix;
    @MockBean
    private SongServiceImpl songService;
    @InjectMocks
    private SongController muzixController;

    private List<Muzix> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(muzixController).build();
        muzix = new Muzix();
        muzix.setSongid(15);
        muzix.setSongname("Girls like you");
        muzix.setComment("Maroon 5");
        list = new ArrayList();
        list.add(muzix);
    }

    @Test
    public void saveUser() throws Exception {
        when(songService.saveMuzix(any())).thenReturn(muzix);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }
    @Test
    public void saveUserFailure() throws Exception {
        when(songService.saveMuzix(any())).thenThrow(SongAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllUser() throws Exception {
        when(songService.getAllMuzix()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void updateUserSuccess() throws Exception
    {
        when(songService.updateMuzix(muzix)).thenReturn(muzix);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateUserFailure() throws Exception
    {
        when(songService.updateMuzix(muzix)).thenThrow(SongNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/muzix")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteUserSuccess() throws Exception
    {
        when(songService.deleteMuzix(muzix.getSongid())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/muzix/{id}",muzix.getSongid())
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteUserFailure() throws Exception
    {
        when(songService.deleteMuzix(muzix.getSongid())).thenReturn(false);
         mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/muzix/{id}",muzix.getSongid())
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
