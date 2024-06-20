package com.eazybytes.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.*;
@ConfigurationProperties(prefix = "cards")
public record CardsInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
}
