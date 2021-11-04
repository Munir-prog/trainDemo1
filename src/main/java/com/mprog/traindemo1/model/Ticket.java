package com.mprog.traindemo1.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class Ticket {
    int id;
    String passengerNo;
    String passengerName;
    String passengerLastName;
    int routeId;
    int railwayCarNo;
    String seatNo;
    BigDecimal cost;
    Route route;

    @Override
    public String toString() {
        return "Id:                    " + id +
                "\nPassenger number:      " + passengerNo +
                "\nName:                  " + passengerName +
                "\nSurname:               " + passengerLastName +
                "\nRoute:                 " + route.routeToString() +
                "\nNumber of railway car: " + railwayCarNo +
                "\nSeat number:           " + seatNo +
                "\nPrice:                 " + cost;
    }

    public static Ticket buildNullTicket(){
        return Ticket.builder()
                .id(0)
                .passengerNo(null)
                .passengerName(null)
                .passengerLastName(null)
                .routeId(0)
                .railwayCarNo(0)
                .seatNo(null)
                .cost(null)
                .route(null)
                .build();
    }
}
