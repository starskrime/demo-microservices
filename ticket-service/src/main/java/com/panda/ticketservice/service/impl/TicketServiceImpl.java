package com.panda.ticketservice.service.impl;


import com.panda.ticketservice.clients.AccountServiceClient;
import com.panda.ticketservice.dto.AccountDto;
import com.panda.ticketservice.dto.TicketDto;
import com.panda.ticketservice.model.PriorityType;
import com.panda.ticketservice.model.Ticket;
import com.panda.ticketservice.model.TicketStatus;
import com.panda.ticketservice.model.es.TicketModel;
import com.panda.ticketservice.repository.TicketRepository;
import com.panda.ticketservice.repository.es.TicketElasticRepository;
import com.panda.ticketservice.service.TicketNotificationService;
import com.panda.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@ComponentScan("com.panda.client.AccountServiceClient")
public class TicketServiceImpl implements TicketService {

    private final TicketElasticRepository ticketElasticRepository;
    private final TicketRepository ticketRepository;
    private final AccountServiceClient accountServiceClient;
    private final TicketNotificationService ticketNotificationService;


    @Override
    @Transactional
    public TicketDto save(TicketDto ticketDto) {
        Ticket ticket = new Ticket();

         ResponseEntity<AccountDto> accountDtoResponseEntity = accountServiceClient.get(ticketDto.getAssignee());


        if (ticketDto.getDescription() == null){
            throw new IllegalArgumentException("Description should not be null");
        }
        ticket.setDescription(ticketDto.getDescription());
        ticket.setNotes(ticketDto.getNotes());
        ticket.setTicketDate(ticketDto.getTicketDate());
        ticket.setAssignee(accountDtoResponseEntity.getBody().getId());
        ticket.setTicketStatus(TicketStatus.valueOf(ticketDto.getTicketStatus()));
        ticket.setPriorityType(PriorityType.valueOf(ticketDto.getPriorityType()));

        ticket = ticketRepository.save(ticket);

         TicketModel ticketModel= TicketModel.builder()
                .description(ticket.getDescription())
                .notes(ticket.getNotes())
                 .assignee(accountDtoResponseEntity.getBody().getNameAndSurname())
                .id(ticket.getId())
                .priorityType(ticket.getPriorityType().getLabel())
                .ticketStatus(ticket.getTicketStatus().getLabel())
                .ticketDate(ticket.getTicketDate()).build();
        ticketElasticRepository.save(ticketModel);

        ticketNotificationService.sendToQueue(ticket);

        ticketDto.setId(ticket.getId());
        return ticketDto;
    }

    @Override
    public TicketDto update(String id,TicketDto ticketDto) {
        return null;
    }

    @Override
    public TicketDto getById(String ticketId) {
        return null;
    }

    @Override
    public Page<TicketDto> getPagination(Pageable pageable) {
        return null;
    }
}
