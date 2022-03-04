package com.panda.ticketservice.model;

import lombok.Getter;

@Getter
public enum TicketStatus {
    OPEN("aciq"),
    IN_PROGRESS("prosesde"),
    RESOLVED("hell_edildi"),
    CLOSED("bagli");

    private String label;
    TicketStatus(String label){
        this.label=label;
    }
}
