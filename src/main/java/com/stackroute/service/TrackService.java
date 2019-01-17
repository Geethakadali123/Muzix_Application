package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.AlreadyUpdatedException;
import com.stackroute.exception.TrackAlreadyExistException;
import com.stackroute.exception.TrackNotFoundException;

import java.util.List;
public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistException;
    public List<Track> getTrack();
    public Track updateTrack(Track track) throws AlreadyUpdatedException;
    public boolean removeTrack(int id) throws TrackNotFoundException;

}
