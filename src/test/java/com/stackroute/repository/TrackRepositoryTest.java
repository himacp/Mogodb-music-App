package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@DataMongoTest
public class TrackRepositoryTest {
    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setUp()
    {
        track = new Track();
        track.setTrackComments("Music is good");
        track.setTrackId(23);
        track.setTrackName("hima");

    }

    @After
    public void tearDown(){

        trackRepository.deleteAll();
    }


    @Test
    public void testSaveTrack(){
        trackRepository.save(track);
        Track tracks = trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(23,tracks.getTrackId());

    }

    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track(23,"Sahana","music");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testTrack,track);
    }

    @Test
    public void testGetAllTrack(){
        Track u = new Track(23,"tulasi","music is not good");
        Track u1 = new Track(24,"yashu","awsome!!");
        trackRepository.save(u);
        trackRepository.save(u1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals(23,list.get(1).getTrackId());

    }
    @Test
    public void deleteTrack() {
        trackRepository.save(track);
        Track tracksuccess = new Track(23, "Hima", "music");
        Assert.assertEquals(23, tracksuccess.getTrackId());
    }
     @Test
        public void deleteTrackfailure(){
         trackRepository.save(track);
         Track trackfailure=new Track(24,"prabha","Music is not good");
         Assert.assertNotEquals(23,trackfailure);
        }
    @Test
    public void updateTrackSuccess() {
        trackRepository.save(track);
        Track tracks = trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(23, tracks.getTrackId());
    }
        @Test
    public void updateTrackFailure()
    {
        track.setTrackId(45);
        trackRepository.save(track);
        Assert.assertNotEquals(44,trackRepository.findById(track.getTrackId()).get());

    }
    @Test
    public void getTrackbyIdSuccess()
    {
        trackRepository.save(track);
        Assert.assertEquals(23,trackRepository.findById(track.getTrackId()).get().getTrackId());
    }

    @Test
    public void getTrackByIdFailure()
    {
        trackRepository.save(track);
        Assert.assertNotEquals(66,trackRepository.findById(track.getTrackId()).get().getTrackId());
    }


}


