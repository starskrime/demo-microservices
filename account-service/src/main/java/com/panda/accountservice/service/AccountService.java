package com.panda.accountservice.service;

import com.google.inject.internal.cglib.core.$CodeGenerationException;
import com.panda.accountservice.dto.AccountDto;
import com.panda.accountservice.entity.Account;
import com.panda.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;


    public AccountDto get(String id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());
        return modelMapper.map(account,AccountDto.class);
    }
    public AccountDto save(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto,Account.class);
        account = accountRepository.save(account);
        accountDto.setId(account.getId());
        return accountDto;
    }
    public AccountDto update(AccountDto accountDto) {
        Assert.isNull(accountDto.getId(), "ID - can not be null");
        Optional<Account> account = accountRepository.findById(accountDto.getId());
        Account accountToUpdate = account.map(it -> {
            it.setBirthDate(accountDto.getBirthDate());
            it.setName(accountDto.getName());
            it.setSurname(accountDto.getSurname());
            return it;
        }).orElseThrow(() -> new IllegalArgumentException());
        return modelMapper.map(accountRepository.save(accountToUpdate),AccountDto.class);
    }
    public void delete(String id) {
      Account account = accountRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException());
        accountRepository.delete(account);
    }

    public Slice<AccountDto> findAll(Pageable pageable) {
        Slice<Account> accountPage = accountRepository.findAll(pageable);
        return null;
    }
}
