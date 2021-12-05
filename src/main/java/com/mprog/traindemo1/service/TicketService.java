package com.mprog.traindemo1.service;

import com.mprog.traindemo1.model.Ticket;
import com.mprog.traindemo1.repository.RouteRepository;
import com.mprog.traindemo1.repository.TicketRepository;
import com.mprog.traindemo1.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class TicketService implements DaoService<Ticket> {

    private final TicketRepository ticketRepository;
    private final RouteRepository routeRepository;

    @Override
    public Collection<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(int id) {
        return ticketRepository.findById(id).orElseThrow(
                () -> new ServiceException("No such Ticket!")
        );
    }

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public Collection<Ticket> findByName(String name) {
        return ticketRepository.findByName(name);
    }

    @Override
    public void deleteById(int id) {
        ticketRepository.deleteById(id);
    }

    private boolean routeIdIsValid(int routeId) {
        var routeIds = routeRepository.getIds();
        return routeIds.contains(routeId);
    }

}
