package com.mprog.traindemo1.ui;

import com.mprog.traindemo1.service.MessageService;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

@Component
public class IO {
    private final PrintWriter writer;
    private final Scanner scanner;
    private final MessageService messageService;

    public IO(InputStream is, OutputStream os, MessageService messageService) {
        this.writer = new PrintWriter(os);
        this.scanner = new Scanner(is);
        this.messageService = messageService;
    }

    public String inter(String code, Object ... params) {
        return messageService.localize(code, params);
    }

    public void interPrintln(String code, Object ... params) {
        var msg = messageService.localize(code, params);
        println(msg);
    }

    public void interPrint(String code, Object ... params) {
        var msg = messageService.localize(code, params);
        print(msg);
    }

    public void println(Object o) {
        writer.println(o);
        writer.flush();
    }

    public void print(Object o) {
        writer.print(o);
        writer.flush();
    }

    public String readLine() {
        return scanner.nextLine().strip();
    }
}
