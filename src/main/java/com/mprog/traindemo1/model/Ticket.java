package com.mprog.traindemo1.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    int id;
    String passengerNo;
    String passengerName;
    String passengerLastName;
    int routeId;
    int railwayCarNo;
    String seatNo;
    BigDecimal cost;
//    Route route;

    @Override
    public String toString() {
//        if (route == null){
//            return "Id:                    " + id +
//                    "\nPassenger number:      " + passengerNo +
//                    "\nName:                  " + passengerName +
//                    "\nSurname:               " + passengerLastName +
//                    "\nRoute id:              " + routeId +
//                    "\nNumber of railway car: " + railwayCarNo +
//                    "\nSeat number:           " + seatNo +
//                    "\nPrice:                 " + cost;
//        }
        return "Id:                    " + id +
                "\nPassenger number:      " + passengerNo +
                "\nName:                  " + passengerName +
                "\nSurname:               " + passengerLastName +
//                "\nRoute:                 " + route.routeToString() +
                "\nNumber of railway car: " + railwayCarNo +
                "\nSeat number:           " + seatNo +
                "\nPrice:                 " + cost;
    }

    public static Ticket buildNullTicket(){
        return Ticket.builder()
                .id(0)
                .passengerNo("")
                .passengerName("")
                .passengerLastName("")
                .routeId(0)
                .railwayCarNo(0)
                .seatNo("")
                .cost(BigDecimal.valueOf(0))
//                .route(Route.builder().build())
                .build();
    }
}
