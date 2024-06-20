package com.microservice.accounts.service;

import com.microservice.accounts.dto.CustomerDto;

public interface AccountsService {

    /**
     *
     * @param customerDto --CustomerDto Object
     *
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber
     * @return
     */
    CustomerDto fetchAccount(String mobileNumber);


}
