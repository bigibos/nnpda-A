package cz.upce.fei.nnpda.controller;

import cz.upce.fei.nnpda.dto.PhaseRequestDTO;
import cz.upce.fei.nnpda.dto.PhaseRespondDTO;
import cz.upce.fei.nnpda.service.PhaseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class PhaseController {

    private final PhaseService phaseService;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/phases/{id}")
    public PhaseRespondDTO updatePhase(@PathVariable Long id, @Valid @RequestBody PhaseRequestDTO phase) {
        return phaseService.updatePhase(id, phase);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/phases")
    public PhaseRespondDTO addPhase(@Valid @RequestBody PhaseRequestDTO phase) {
        return phaseService.addPhase(phase);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/phases/{id}")
    public PhaseRespondDTO deletePhase(@PathVariable Long id) {
        return phaseService.deletePhase(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/phases/{id}")
    public PhaseRespondDTO findPhase(@PathVariable Long id) {
        return phaseService.findPhase(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/phases")
    public Collection<PhaseRespondDTO> getPhases() {
        return phaseService.findPhases();
    }

}
