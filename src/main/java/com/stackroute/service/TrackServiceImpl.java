package com.stackroute.service;


import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TrackServiceImpl implements TrackService{

    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository=trackRepository;
    }
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if( trackRepository.existsById(track.getTrackId()))
        {
            throw new TrackAlreadyExistsException("Track Already Exist");
        }
        Track savedTrack=trackRepository.save(track);

        if( savedTrack == null )
        {
            throw new TrackAlreadyExistsException("Track Already Exist");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTrack() {
        return trackRepository.findAll();
    }

    @Override
    public int deleteTrack(int id) throws TrackNotFoundException {

        if( !trackRepository.existsById(id) )
        {
            throw new TrackNotFoundException("ID is not found");
        }
        trackRepository.deleteById(id);

        return 0;
    }

    @Override
    public Track updateTrack(Track track){
        Track updateTrack= trackRepository.save(track);
        return updateTrack;
    }

    @Override
    public Optional<Track> getTrackById(int id) throws TrackNotFoundException {


        if (trackRepository.existsById(id)) {
            return trackRepository.findById(id);
        } else {

            throw new TrackNotFoundException("Given ID is not there");
        }
    }

}