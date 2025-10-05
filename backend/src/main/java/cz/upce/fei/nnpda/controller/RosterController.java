package cz.upce.fei.nnpda.controller;

import cz.upce.fei.nnpda.dto.RosterRespondDTO;
import cz.upce.fei.nnpda.service.RosterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class RosterController {

    private final RosterService rosterService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/events/{eventId}/racers/{racerId}/rosters")
    public RosterRespondDTO addRoster(@PathVariable Long eventId, @PathVariable Long racerId) {
        return rosterService.addRoster(eventId, racerId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/events/{eventId}/racers/{racerId}/rosters")
    public RosterRespondDTO findRoster(@PathVariable Long eventId, @PathVariable Long racerId) {
        return rosterService.findRoster(eventId, racerId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/events/{eventId}/racers/{racerId}/rosters")
    public RosterRespondDTO deleteRoster(@PathVariable Long eventId, @PathVariable Long racerId) {
        return rosterService.deleteRoster(eventId, racerId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rosters")
    public Collection<RosterRespondDTO> getRosters() {
        return rosterService.findRosters();
    }

}
