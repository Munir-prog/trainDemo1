package com.mprog.traindemo1.ui;

import com.mprog.traindemo1.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@RequiredArgsConstructor
public class Commands {

    private final TicketService ticketService;

    @ShellMethod(value = "show all tickets", key = "ticket-all")
    public void showAllTickets() {
        var tickets = ticketService.findAll();
        if (tickets.isEmpty()) {
            System.out.println("no-book-found");
        } else {
            System.out.println(tickets);
        }
    }
}
