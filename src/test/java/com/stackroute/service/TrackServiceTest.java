package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TrackServiceTest {
    private Track track;

    //Create a mock for TrackRepository
    @Mock
    private TrackRepository trackRepository;

    //Inject the mocks as dependencies into TrackServiceImpl
    @InjectMocks
    private TrackServiceImpl trackService;
    List<Track> list = null;


    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setTrackComments("haha");
        track.setTrackId(23);
        track.setTrackName("Jonny123");
        list = new ArrayList<>();
        list.add(track);


    }

    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track, savedTrack);

        //verify here verifies that trackRepository save method is only called once
        verify(trackRepository, times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track) any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        System.out.println("savedTrack" + savedTrack);
        Assert.assertEquals(track, savedTrack);
    }


    @Test
    public void getAllTrackSuccess() {

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist = trackService.getAllTrack();
        Assert.assertEquals(list, tracklist);
    }

    @Test
    public void getTrackbyIdsuccess() throws TrackNotFoundException {
        trackRepository.save(track);
        when(trackRepository.existsById(23)).thenReturn(true);
        when(trackRepository.findById(23)).thenReturn(Optional.of(track));
        assertEquals(Optional.of(track),trackService.getTrackById(23));

    }

    @Test
    public void getTrackbyIdfailure() throws TrackNotFoundException {
        trackRepository.save(track);
        when(trackRepository.existsById(23)).thenReturn(false);
        when(trackRepository.findById(23)).thenReturn(Optional.of(track));
        assertNotEquals(true,trackService.getTrackById(23));


    }
@Test
    public void updateTrackSuccess() throws TrackNotFoundException
    {
        when(trackRepository.existsById(track.getTrackId())).thenReturn(true);
        Track updatedTrack=new Track();
        updatedTrack.setTrackId(23);
        updatedTrack.setTrackName("hai");
        updatedTrack.setTrackComments("heloo");
        when(trackRepository.save(updatedTrack)).thenReturn(updatedTrack);
        assertEquals(updatedTrack,trackService.updateTrack(updatedTrack));
    }
    @Test(expected = TrackNotFoundException.class)
    public void updateTrackFailure() throws TrackNotFoundException
    {
        when(trackRepository.existsById(track.getTrackId())).thenReturn(false);
        when(trackRepository.save((Track)any())).thenReturn(null);
        Track updateTrack=trackService.updateTrack(new Track(36,"hima","chandra"));
        assertNotEquals(track,updateTrack);
    }
    @Test
    public void deleteTracksuccess() throws TrackNotFoundException{
        trackRepository.save(track);
        when(trackRepository.existsById(anyInt())).thenReturn(true);
        boolean expected=trackService.deleteTrack(23);
        assertEquals(true,expected);

    }
    @Test
    public void deleteTrackfailure() throws TrackNotFoundException{
        trackRepository.save(track);
        when(trackRepository.existsById(anyInt())).thenReturn(true);
        boolean expected=trackService.deleteTrack(23);
        assertNotEquals(false,expected);

    }
}