package com.panda.ticketservice.model.es;

import com.panda.ticketservice.model.PriorityType;
import com.panda.ticketservice.model.TicketStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Date;
@Data
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "ticket" )
public class TicketModel implements Serializable {
    @Id
    private String id;

    private String description;
    private String notes;
    private String assignee;
    private Date ticketDate;
    private String priorityType;
    private String ticketStatus;

}
