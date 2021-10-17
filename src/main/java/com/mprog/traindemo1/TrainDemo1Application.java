package com.mprog.traindemo1;

import com.mprog.traindemo1.model.Ticket;
import com.mprog.traindemo1.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

@SpringBootApplication
@RequiredArgsConstructor
public class TrainDemo1Application {


    public static void main(String[] args) {
        SpringApplication.run(TrainDemo1Application.class, args);
    }

}
