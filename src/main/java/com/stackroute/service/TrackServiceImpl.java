package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.AlreadyUpdatedException;
import com.stackroute.exception.TrackAlreadyExistException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {
    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }


    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistException {
        if(trackRepository.existsById(track.getTrackId()))
        {
            throw new TrackAlreadyExistException("Track already exists");
        }
        Track savedTrack = trackRepository.save(track);
        if(savedTrack == null)
        {
            throw new TrackAlreadyExistException("Track already exists");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getTrack() {
        return trackRepository.findAll();
    }

    @Override
    public Track updateTrack(Track track) throws AlreadyUpdatedException{
        if(trackRepository.existsById(track.getTrackId())) {
            Track updatedTrack = trackRepository.save(track);
            return updatedTrack;
        }
        else
        {
            throw new AlreadyUpdatedException("Track already updated");

        }

    }

    @Override
    public boolean removeTrack(int id) throws TrackNotFoundException {
        boolean status = false;
        if (trackRepository.existsById(id)) {
            trackRepository.deleteById(id);
            status = true;
            return status;
        } else {

            throw new TrackNotFoundException("Track not found");

        }
    }

}
