package com.mprog.traindemo1.controller;

import com.mprog.traindemo1.model.Ticket;
import com.mprog.traindemo1.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@Controller
@RequestMapping("/app")
@RequiredArgsConstructor
public class TicketController {

    @Value("${spring.application.name}")
    private String appName;

    private final TicketService ticketService;



    @GetMapping("appName")
    public String appName(Model model){
        model.addAttribute("appName", appName);
        return "index";
    }

    @GetMapping("/tickets")
    public String allTickets(Model model){
        var tickets = ticketService.findAll();
        model.addAttribute("tickets", tickets);
        return "table/tableTicket";
    }

    @GetMapping("/ticket/{id}")
    public String ticket(Model model, @PathVariable int id){
        model.addAttribute("ticket", ticketService.findById(id));
        return "view/ticket";
    }

    @GetMapping("/ticket-by-name")
    public String newTicket(Model model){
        model.addAttribute("ticket", new Ticket());
        return "find/ticketByName";
    }

    @PostMapping("/ticket-by-name")
    public String ticketByName(@ModelAttribute Ticket ticket, Model model){
//        model.addAttribute("ticket", new Ticket());
        var ticketsByName = ticketService.findByName(ticket.getPassengerName());
        model.addAttribute("tickets", ticketsByName);
        return "table/tableTicket";
    }

//    @GetMapping("/ticket/{name}")
//    public String ticketByName(Model model, @PathVariable String name){
//        model.addAttribute("ticket", ticketService.findByName(name));
//        return "view/ticket";
//    }
}
