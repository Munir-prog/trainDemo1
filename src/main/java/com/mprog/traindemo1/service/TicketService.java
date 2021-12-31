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
import java.util.Random;
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
        if (ticket.getPassengerNo() == null || ticket.getPassengerNo().equals(""))
            ticket.setPassengerNo(generatePassengerNo());
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

    private String generatePassengerNo() {
        var random = new Random();
        var i = random.nextInt(3);
        var builder = new StringBuilder();
        if (i == 0){
            appendThreeNumbers(random, builder);
            appendThreeNumbers(random, builder);
            return builder.toString();
        } else if (i == 1){
            appendThreeChars(random, builder);
            appendThreeChars(random, builder);
        } else {
            var i1 = random.nextInt(2);
            if (i1 == 0){
                appendThreeChars(random, builder);
                appendThreeNumbers(random, builder);
            }
            if (i1 == 1){
                appendThreeNumbers(random, builder);
                appendThreeChars(random, builder);
            }
        }
        return builder.toString();
    }

    private void appendThreeChars(Random random, StringBuilder builder) {
        for (int i = 0; i < 3; i++) {
            builder.append((char) random.nextInt(65, 91));
        }
    }

    private void appendThreeNumbers(Random random, StringBuilder builder) {
        for (int j = 0; j < 3; j++) {
            builder.append(random.nextInt(10));
        }
    }


}
