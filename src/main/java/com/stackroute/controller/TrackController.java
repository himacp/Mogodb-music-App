package com.stackroute.controller;
import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {

    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService)
    {
        this.trackService=trackService;
    }

//POST MAPPING METHOD TO HANDLE POST REQUEST
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackNotFoundException, TrackAlreadyExistsException {
        trackService.saveTrack(track);
        ResponseEntity responseEntity=new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);;
        return responseEntity;
    }


//POST MAPPING METHOD TO HANDLE UPDATE TRACK
    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track) throws TrackNotFoundException
    {
        ResponseEntity responseEntity;
            trackService.updateTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully Updated", HttpStatus.CREATED);
            return responseEntity;
    }

    //POST MAPPING METHOD TO HANDLE DELETE TRACK
    @DeleteMapping(value = "/track/{id}")
    public boolean deleteTrack(@PathVariable String id) throws TrackNotFoundException {
        ResponseEntity responseEntity;
            trackService.deleteTrack(Integer.parseInt(id));
            responseEntity=new ResponseEntity<String>("Successfully deleted",HttpStatus.OK);
        return true;
    }

    @GetMapping("/track")
    public List<Track>getAllTrack () throws TrackAlreadyExistsException {
        return trackService.getAllTrack();
    }

//TRACKING BY ID
    @GetMapping(value = "track/{id}")
    public ResponseEntity<?> getByIdTrack(@PathVariable int id) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        return new ResponseEntity<Optional<Track>>(trackService.getTrackById(id), HttpStatus.OK);
    }
}
