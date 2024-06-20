package com.microservice.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.*;

@ConfigurationProperties(prefix = "accounts")
public record AccountsContactInfoDto(String message , Map<String , String> contactDetails , List<String> onCallSupport) {
}
