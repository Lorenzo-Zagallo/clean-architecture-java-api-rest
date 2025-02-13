package com.lorenzozagallo.ca24.domain.ports;

public interface GenerativeAiService {

    String generateContent(String objective, String context);
}
