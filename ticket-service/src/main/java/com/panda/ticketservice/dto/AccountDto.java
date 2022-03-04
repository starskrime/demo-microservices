package com.panda.ticketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String id ;
    private String name;
    private String surname;
    private String username;
    private String email;
    private LocalDate birthDate;

    public String getNameAndSurname (){
        return this.name + " " + this.surname;
    }

}
