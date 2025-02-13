package com.lorenzozagallo.ca24.adapters.out;

import com.lorenzozagallo.ca24.domain.ports.GenerativeAiService;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "openAiChatApi", url = "${openai.base-url}", configuration = OpenAiChatService.Config.class)
public interface OpenAiChatService extends GenerativeAiService {

    @PostMapping("/v1/chat/completions")
    OpenAiChatCompletionResponse chatCompletion(OpenAiChatCompletionRequest req);

    @Override
    default String generateContent(String objective, String context) {
        String model = "gpt-3.5-turbo" ;
        List<Message> messages = List.of(
                new Message("system", objective),
                new Message("user", context)
        );

        OpenAiChatCompletionRequest req = new OpenAiChatCompletionRequest(model, messages);
        OpenAiChatCompletionResponse resp = chatCompletion(req);
        resp.choices().getFirst();
        return model;
    }

    record OpenAiChatCompletionRequest(String model, List<Message> message) { }
    record Message(String role, String content) { }

    record OpenAiChatCompletionResponse(List<Choice> choices) { }
    record Choice(Message message) { }

    class Config {
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${openai.api-key}") String apiKey) {
            return requestTemplate -> requestTemplate.header(
                    HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(apiKey));
        }
    }
}
// https://platform.openai.com/docs/api-reference/chat/create
