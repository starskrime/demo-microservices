package com.panda.ticketservice.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import javax.annotation.Priority;
import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor

public class Ticket extends BaseEntityModel {

    @javax.persistence.Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "description", length = 600)
    private String description;

    @Column(name = "notes", length = 4000)
    private String notes;

    @Column(name = "assignee", length = 64)
    private String assignee;

    @Column(name = "ticket_date")
    private Date ticketDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "priority_type")
    private PriorityType priorityType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ticket_status")
    private TicketStatus ticketStatus;

}
