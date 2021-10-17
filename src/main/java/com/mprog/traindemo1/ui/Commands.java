package com.mprog.traindemo1.ui;

import com.mprog.traindemo1.model.Ticket;
import com.mprog.traindemo1.service.CurrentLocaleService;
import com.mprog.traindemo1.service.TicketService;
import com.mprog.traindemo1.service.exception.AppException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.math.BigDecimal;
import java.util.Locale;

@ShellComponent
@RequiredArgsConstructor
public class Commands {

    private final TicketService ticketService;
    private final IO io;
    private final CurrentLocaleService currentLocaleService;
    @Getter
    private ShellState state = ShellState.MAIN_MENU;
    private Ticket handlingTicket;


    @ShellMethod(value = "change current language", key = {"language", "lang"})
    @ShellMethodAvailability("availableInMainMenu")
    public void setLanguage(String language){
        try {
            currentLocaleService.set(language.strip().toLowerCase());
        } catch (AppException e) {
            io.interPrintln(e.getMessage(), e.getParams());
        }
    }

    @ShellMethod(value = "show all tickets", key = "ticket-all")
    @ShellMethodAvailability("availableInMainMenu")
    public void showAllTickets() {
        var tickets = ticketService.findAll();
        if (tickets.isEmpty()) {
            io.interPrintln("no-book-found");
        } else {
            // TODO pretty output for tickets
            io.println(tickets);
        }
    }


    @ShellMethod(value = "insert new ticket", key = "ticket-insert")
    @ShellMethodAvailability("availableInMainMenu")
    public void insertTicket() {
        handlingTicket = new Ticket(0, "", "",
                "", 0, 0, "", BigDecimal.valueOf(0));
        state = ShellState.PROCESSING_TICKET;
        show();
    }

    @ShellMethod(value = "show handling book", key = "show")
    @ShellMethodAvailability("availableInUpdatingTicket")
    private void show() {
        // TODO pretty output for tickets
        io.println(handlingTicket);
    }

    @ShellMethod(value = "set passenger number", key = "set-passenger-number")
    @ShellMethodAvailability("availableInUpdatingTicket")
    public void setPassengerNumber(@ShellOption(defaultValue = "") String passengerNumber) {
        if (passengerNumber.isBlank()) {
            io.interPrint("set-passenger-number");
            io.println("""
                    Examples:
                    QYAQDE
                    555321
                    """);
            passengerNumber = io.readLine();
        }
        if (passengerNumber.isBlank()) {
            io.interPrintln("Empty line entered, operation cancelled");
        } else {
            handlingTicket.setPassengerNo(passengerNumber);
            io.interPrintln("new-passenger-number-is", handlingTicket.getPassengerNo());
        }
    }

    @ShellMethod(value = "set passenger name", key = "set-passenger-name")
    @ShellMethodAvailability("availableInUpdatingTicket")
    public void setPassengerName(@ShellOption(defaultValue = "") String passengerName) {
        if (passengerName.isBlank()) {
            io.interPrint("set-passenger-name");
            passengerName = io.readLine();
        }
        if (passengerName.isBlank()) {
            io.interPrintln("Empty line entered, operation cancelled");
        } else {
            handlingTicket.setPassengerName(passengerName);
            io.interPrintln("new-passenger-name-is", handlingTicket.getPassengerName());
        }
    }

    @ShellMethod(value = "set passenger last name", key = "set-passenger-last-name")
    @ShellMethodAvailability("availableInUpdatingTicket")
    public void setPassengerLastName(@ShellOption(defaultValue = "") String passengerLastName) {
        if (passengerLastName.isBlank()) {
            io.interPrint("set-passenger-last-name");
            passengerLastName = io.readLine();
        }
        if (passengerLastName.isBlank()) {
            io.interPrintln("Empty line entered, operation cancelled");
        } else {
            handlingTicket.setPassengerName(passengerLastName);
            io.interPrintln("new-passenger-last-name-is", handlingTicket.getPassengerLastName());
        }
    }

    //TODO make route with meaning from database
    @ShellMethod(value = "set route id", key = "set-route-id")
    @ShellMethodAvailability("availableInUpdatingTicket")
    public void setRouteId(@ShellOption(defaultValue = "") int routeId) {
        if (routeId > 10 || routeId < 1) {
            io.interPrintln("invalid-route-id");
            io.interPrintln("write-one-more");
            routeId = io.nextInt();
        }
        if (routeId > 10 || routeId < 1) {
            io.interPrintln("invalid-route-id");
            io.interPrintln("operation-cancelled");
        } else {
            handlingTicket.setRouteId(routeId);
            io.interPrintln("new-route-id-is", handlingTicket.getRouteId());
        }
    }

    @ShellMethod(value = "set railway car number", key = "set-railway-car-number")
    @ShellMethodAvailability("availableInUpdatingTicket")
    public void setRailwayCarNumber(@ShellOption(defaultValue = "") int railwayCarNumber) {
        if (railwayCarNumber > 10 || railwayCarNumber < 1) {
            io.interPrintln("invalid-railway-car-number-id");
            io.interPrintln("write-one-more");
            railwayCarNumber = io.nextInt();
        }
        if (railwayCarNumber > 10 || railwayCarNumber < 1) {
            io.interPrintln("invalid-railway-car-number-id");
            io.interPrintln("operation-cancelled");
        } else {
            handlingTicket.setRailwayCarNo(railwayCarNumber);
            io.interPrintln("new-railway-car-number-is", handlingTicket.getRailwayCarNo());
        }
    }

    @ShellMethod(value = "set seat value", key = "set-seat-number")
    @ShellMethodAvailability("availableInUpdatingTicket")
    public void setSeatValue(@ShellOption(defaultValue = "") String seatNumber) {
        if (seatNumber.isBlank()) {
            io.interPrintln("invalid-railway-seat-number-id");
            io.interPrintln("write-one-more");
            seatNumber = io.readLine();
        }
        if (seatNumber.isBlank()) {
            io.interPrintln("invalid-railway-seat-number-id");
            io.interPrintln("operation-cancelled");
        } else {
            handlingTicket.setSeatNo(seatNumber);
            io.interPrintln("new-seat-number-is", handlingTicket.getSeatNo());
        }
    }

    @ShellMethod(value = "set ticket cost", key = "set-cost")
    @ShellMethodAvailability("availableInUpdatingTicket")
    public void setCost(@ShellOption(defaultValue = "") BigDecimal cost) {
        if (cost.equals(BigDecimal.valueOf(0))) {
            io.interPrintln("invalid-cost");
            io.interPrintln("write-one-more");
            cost = io.nextBigDecimal();
        }
        if (cost.equals(BigDecimal.valueOf(0))) {
            io.interPrintln("invalid-cost");
            io.interPrintln("operation-cancelled");
        } else {
            handlingTicket.setCost(cost);
            io.interPrintln("new-cost-is", handlingTicket.getCost());
        }
    }

    @ShellMethod(value = "perform current operation", key = "perform")
    @ShellMethodAvailability("availableInUpdatingBook")
    public void perform() {
        try {
            if (checkHandlingTicket()) {
                ticketService.save(handlingTicket);
                io.interPrintln("operation-successful");
                state = ShellState.MAIN_MENU;
            }
        } catch (AppException e) {
            io.interPrintln(e.getMessage(), e.getParams());
        }
    }

    private boolean checkHandlingTicket() {
        if (handlingTicket.getPassengerNo().isBlank()) {
            throw new AppException("ticket.check.passenger-no-must-not-be-empty");
        }
        if (handlingTicket.getPassengerName().isBlank()) {
            throw new AppException("ticket.check.name-must-be-set");
        }
        if (handlingTicket.getPassengerLastName().isBlank()) {
            throw new AppException("ticket.check.last-name-must-be-set");
        }
        if (handlingTicket.getRouteId() == 0) {
            throw new AppException("ticket.check.route-id-must-be-set");
        }
        if (handlingTicket.getRailwayCarNo() == 0) {
            throw new AppException("ticket.check.railway-car-no-must-be-set");
        }
        if (handlingTicket.getSeatNo().isBlank()) {
            throw new AppException("ticket.check.seat-no-must-not-be-empty");
        }
        if (handlingTicket.getCost().equals(BigDecimal.valueOf(0))){
            throw new AppException("ticket.check.cost-must-be-set");
        }
        return true;
    }
}
