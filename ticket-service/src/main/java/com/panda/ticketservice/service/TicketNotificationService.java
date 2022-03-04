package com.panda.ticketservice.service;

import com.panda.ticketservice.model.Ticket;

public interface TicketNotificationService {
    void sendToQueue( Ticket ticket);
}
