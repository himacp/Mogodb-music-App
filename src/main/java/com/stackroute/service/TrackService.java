package com.stackroute.service;



import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.domain.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException, TrackNotFoundException;
    public List<Track> getAllTrack()throws TrackAlreadyExistsException;
    public int deleteTrack(int id) throws TrackNotFoundException;
    public Track updateTrack(Track track) throws TrackNotFoundException;
    public Optional<Track> getTrackById(int id) throws TrackNotFoundException;

}
