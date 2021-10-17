package com.mprog.traindemo1.ui;

import lombok.Getter;

public enum ShellState {

    MAIN_MENU("main menu"),
    PROCESSING_TICKET("ticket processing");

    @Getter
    private final String title;

    ShellState(String title) {
        this.title = title;
    }
}
