package com.panda.ticketservice.model;

import lombok.Getter;

@Getter
public enum PriorityType {
    URGENT("vacib"),
    LOW("Ashagi"),
    HIGH("Yuxari");
    private String label;

    PriorityType(String label){
        this.label=label;
    }
}
