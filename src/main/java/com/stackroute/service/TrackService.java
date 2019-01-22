package com.stackroute.service;



import com.stackroute.domain.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    public Track saveTrack(Track track);
    public List<Track> getAllTrack();
    public boolean deleteTrack(int id);
    public Track updateTrack(Track track);
    public Optional<Track> getTrackById(int id);
    public List<Track> getTrackByName(String name);

}
