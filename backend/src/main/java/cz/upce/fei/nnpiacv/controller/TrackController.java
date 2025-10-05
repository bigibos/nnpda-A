package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.domain.Track;
import cz.upce.fei.nnpiacv.dto.TrackRequestDTO;
import cz.upce.fei.nnpiacv.dto.TrackRespondDTO;
import cz.upce.fei.nnpiacv.service.TrackService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@AllArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/tracks/{id}")
    public TrackRespondDTO updateTrack(@PathVariable Long id, @Valid @RequestBody TrackRequestDTO track) {
        return trackService.updateTrack(id, track);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/tracks")
    public TrackRespondDTO addTrack(@Valid @RequestBody TrackRequestDTO track) {
        return trackService.addTrack(track);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/tracks/{id}")
    public TrackRespondDTO deleteTrack(@PathVariable Long id) {
        return trackService.deleteTrack(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tracks/{id}")
    public TrackRespondDTO findTrack(@PathVariable Long id) {
        return trackService.findTrack(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tracks")
    public Collection<TrackRespondDTO> getTracks() {
        return trackService.findTracks();
    }

}
