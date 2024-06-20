package com.microservice.accounts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Accounts extends BaseEntity {
    private Long customerId;

    @Id
    private Long accountNumber;

    private String accountType ;

    private String branchAddress;


}
