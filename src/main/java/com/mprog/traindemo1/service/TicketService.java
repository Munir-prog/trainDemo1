package com.mprog.traindemo1.service;

import com.mprog.traindemo1.model.Ticket;
import com.mprog.traindemo1.repository.TicketRepository;
import com.mprog.traindemo1.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class TicketService implements DaoService<Ticket> {

    private final TicketRepository ticketRepository;

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
    public void save(Ticket object) {
        ticketRepository.save(object);
    }

    @Override
    public void update(Ticket object) {
        ticketRepository.update(object);
    }
}
