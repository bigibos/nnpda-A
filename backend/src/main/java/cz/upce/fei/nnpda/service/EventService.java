package cz.upce.fei.nnpda.service;

import cz.upce.fei.nnpda.domain.Event;
import cz.upce.fei.nnpda.dto.EventRequestDTO;
import cz.upce.fei.nnpda.dto.EventRespondDTO;
import cz.upce.fei.nnpda.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public EventRespondDTO addEvent(EventRequestDTO event) {
        Event newEvent = modelMapper.map(event, Event.class);
        newEvent = eventRepository.save(newEvent);

        return modelMapper.map(newEvent, EventRespondDTO.class);
    }



    @Transactional
    public EventRespondDTO updateEvent(Long id, EventRequestDTO event) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        modelMapper.map(event, existingEvent);

        Event updatedEvent = eventRepository.save(existingEvent);
        return modelMapper.map(updatedEvent, EventRespondDTO.class);
    }

    @Transactional
    public EventRespondDTO deleteEvent(Long id) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        eventRepository.deleteById(existingEvent.getId());

        return modelMapper.map(existingEvent, EventRespondDTO.class);
    }

    public Collection<EventRespondDTO> findEvents() {
        return eventRepository.findAll().stream()
                .map(event -> modelMapper.map(event, EventRespondDTO.class))
                .collect(Collectors.toList());
    }

    public EventRespondDTO findEvent(Long id) {
        Event book = eventRepository.findById(id).orElseThrow();
        return modelMapper.map(book, EventRespondDTO.class);
    }

}
