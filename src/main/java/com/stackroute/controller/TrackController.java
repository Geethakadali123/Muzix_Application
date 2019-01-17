package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.AlreadyUpdatedException;
import com.stackroute.exception.TrackAlreadyExistException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController {
    TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        }
        catch(TrackAlreadyExistException e)
        {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getTrack()
    {
        return new ResponseEntity<List<Track>>(trackService.getTrack(),HttpStatus.OK);
    }

    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try {
            trackService.updateTrack(track);
            responseEntity = new ResponseEntity<String>("successfully updated", HttpStatus.CREATED);
        }
        catch(AlreadyUpdatedException e)
        {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id)
    {
        ResponseEntity responseEntity;
        try {
            trackService.removeTrack(id);
            responseEntity = new ResponseEntity<String>("successfully deleted", HttpStatus.OK);
        }
        catch(TrackNotFoundException e)
        {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}
