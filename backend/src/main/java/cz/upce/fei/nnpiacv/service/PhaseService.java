package cz.upce.fei.nnpiacv.service;

import cz.upce.fei.nnpiacv.domain.Event;
import cz.upce.fei.nnpiacv.domain.Phase;
import cz.upce.fei.nnpiacv.domain.Team;
import cz.upce.fei.nnpiacv.domain.Track;
import cz.upce.fei.nnpiacv.dto.PhaseRequestDTO;
import cz.upce.fei.nnpiacv.dto.PhaseRespondDTO;
import cz.upce.fei.nnpiacv.repository.EventRepository;
import cz.upce.fei.nnpiacv.repository.PhaseRepository;
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
public class PhaseService {

    private final PhaseRepository phaseRepository;

    private final EventRepository eventRepository;
    private final TrackRepository trackRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PhaseRespondDTO addPhase(PhaseRequestDTO phase) {
        Event event = eventRepository.findById(phase.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Track track = trackRepository.findById(phase.getTrackId())
                .orElseThrow(() -> new RuntimeException("Track not found"));

        Phase newPhase = new Phase();

        newPhase.setStartTime(phase.getStartTime());
        newPhase.setEvent(event);
        newPhase.setTrack(track);

        phaseRepository.save(newPhase);

        return modelMapper.map(newPhase, PhaseRespondDTO.class);
    }



    @Transactional
    public PhaseRespondDTO updatePhase(Long id, PhaseRequestDTO phase) {
        Phase existingPhase  = phaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phase not found"));

        Event event = eventRepository.findById(phase.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Track track = trackRepository.findById(phase.getTrackId())
                .orElseThrow(() -> new RuntimeException("Track not found"));

        existingPhase.setStartTime(phase.getStartTime());
        existingPhase.setEvent(event);
        existingPhase.setTrack(track);

        phaseRepository.save(existingPhase);

        return modelMapper.map(existingPhase, PhaseRespondDTO.class);
    }

    @Transactional
    public PhaseRespondDTO deletePhase(Long id) {
        Phase existingPhase = phaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        phaseRepository.deleteById(existingPhase.getId());

        return modelMapper.map(existingPhase, PhaseRespondDTO.class);
    }

    public Collection<PhaseRespondDTO> findPhases() {
        return phaseRepository.findAll().stream()
                .map(phase -> modelMapper.map(phase, PhaseRespondDTO.class))
                .collect(Collectors.toList());
    }

    public PhaseRespondDTO findPhase(Long id) {
        Phase book = phaseRepository.findById(id).orElseThrow();
        return modelMapper.map(book, PhaseRespondDTO.class);
    }

}
