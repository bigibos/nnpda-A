package cz.upce.fei.nnpiacv.service;

import cz.upce.fei.nnpiacv.domain.Event;
import cz.upce.fei.nnpiacv.domain.Track;
import cz.upce.fei.nnpiacv.domain.Team;
import cz.upce.fei.nnpiacv.domain.Track;
import cz.upce.fei.nnpiacv.dto.TrackRequestDTO;
import cz.upce.fei.nnpiacv.dto.TrackRespondDTO;
import cz.upce.fei.nnpiacv.repository.EventRepository;
import cz.upce.fei.nnpiacv.repository.TrackRepository;
import cz.upce.fei.nnpiacv.repository.TeamRepository;
import cz.upce.fei.nnpiacv.repository.TrackRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.ResponseCache;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class TrackService {

    private final TrackRepository trackRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public TrackRespondDTO addTrack(TrackRequestDTO track) {
        Track newTrack = modelMapper.map(track, Track.class);
        newTrack = trackRepository.save(newTrack);

        return modelMapper.map(newTrack, TrackRespondDTO.class);
    }

    @Transactional
    public TrackRespondDTO updateTrack(Long id, TrackRequestDTO track) {
        Track existingTrack = trackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        modelMapper.map(track, existingTrack);

        Track updatedTrack = trackRepository.save(existingTrack);
        return modelMapper.map(updatedTrack, TrackRespondDTO.class);
    }

    @Transactional
    public TrackRespondDTO deleteTrack(Long id) {
        Track existingTrack = trackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        trackRepository.deleteById(existingTrack.getId());

        return modelMapper.map(existingTrack, TrackRespondDTO.class);
    }

    public Collection<TrackRespondDTO> findTracks() {
        return trackRepository.findAll().stream()
                .map(track -> modelMapper.map(track, TrackRespondDTO.class))
                .collect(Collectors.toList());
    }

    public TrackRespondDTO findTrack(Long id) {
        Track book = trackRepository.findById(id).orElseThrow();
        return modelMapper.map(book, TrackRespondDTO.class);
    }

}
