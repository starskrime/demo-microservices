package com.panda.ticketservice.repository.es;

import com.panda.ticketservice.model.es.TicketModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TicketElasticRepository extends ElasticsearchRepository<TicketModel, String> {

}
