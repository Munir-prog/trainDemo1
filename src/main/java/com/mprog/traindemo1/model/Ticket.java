package com.mprog.traindemo1.model;

import lombok.*;

import java.math.BigDecimal;

@Setter
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

    @Override
    public String toString() {
        return "Id:                    " + id +
                "\nName:                  " + passengerName +
                "\nSurname:               " + passengerLastName +
                "\nRoute id:              " + routeId +
                "\nNumber of railway car: " + railwayCarNo +
                "\nSeat number:           " + seatNo +
                "\nPrice:                 " + cost;
    }
}
