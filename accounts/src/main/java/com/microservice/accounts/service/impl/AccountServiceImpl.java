package com.microservice.accounts.service.impl;

import com.microservice.accounts.constants.AccountsConstants;
import com.microservice.accounts.dto.AccountsDto;
import com.microservice.accounts.dto.CustomerDto;
import com.microservice.accounts.entity.Accounts;
import com.microservice.accounts.entity.Customer;
import com.microservice.accounts.exception.CustomerAlreadyExistsException;
import com.microservice.accounts.exception.ResourceNotFoundException;
import com.microservice.accounts.mapper.AccountsMapper;
import com.microservice.accounts.mapper.CustomerMapper;
import com.microservice.accounts.repository.AccountsRepository;
import com.microservice.accounts.repository.CustomerRepository;
import com.microservice.accounts.service.AccountsService;
import lombok.AllArgsConstructor;
import org.hibernate.ResourceClosedException;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     *
     * @param customerDto --CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto , new Customer());
        Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(existingCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer Already exists registered with the given mobile Number"
                    + customerDto.getMobileNumber());
        }
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
//        newAccount.setCreatedAt(LocalDateTime.now());
//        newAccount.setCreatedBy("anonymous");
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer not found")
        );
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account not found")
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer , new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account,new AccountsDto()));
        return customerDto;
    }




}
