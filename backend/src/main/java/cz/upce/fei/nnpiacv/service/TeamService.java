package cz.upce.fei.nnpiacv.service;

import cz.upce.fei.nnpiacv.domain.Event;
import cz.upce.fei.nnpiacv.domain.Team;
import cz.upce.fei.nnpiacv.domain.Team;
import cz.upce.fei.nnpiacv.domain.Track;
import cz.upce.fei.nnpiacv.dto.TeamRequestDTO;
import cz.upce.fei.nnpiacv.dto.TeamRespondDTO;
import cz.upce.fei.nnpiacv.repository.EventRepository;
import cz.upce.fei.nnpiacv.repository.TeamRepository;
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
public class TeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public TeamRespondDTO addTeam(TeamRequestDTO team) {
        Team newTeam = modelMapper.map(team, Team.class);
        newTeam = teamRepository.save(newTeam);

        return modelMapper.map(newTeam, TeamRespondDTO.class);
    }



    @Transactional
    public TeamRespondDTO updateTeam(Long id, TeamRequestDTO team) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        modelMapper.map(team, existingTeam);

        Team updatedTeam = teamRepository.save(existingTeam);
        return modelMapper.map(updatedTeam, TeamRespondDTO.class);
    }

    @Transactional
    public TeamRespondDTO deleteTeam(Long id) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        teamRepository.deleteById(existingTeam.getId());

        return modelMapper.map(existingTeam, TeamRespondDTO.class);
    }

    public Collection<TeamRespondDTO> findTeams() {
        return teamRepository.findAll().stream()
                .map(team -> modelMapper.map(team, TeamRespondDTO.class))
                .collect(Collectors.toList());
    }

    public TeamRespondDTO findTeam(Long id) {
        Team book = teamRepository.findById(id).orElseThrow();
        return modelMapper.map(book, TeamRespondDTO.class);
    }

}
