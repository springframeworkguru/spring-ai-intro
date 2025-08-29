package guru.springframework.springaiintro.services;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import guru.springframework.springaiintro.models.Answer;
import guru.springframework.springaiintro.models.Question;
import guru.springframework.springaiintro.models.CapitalRequest;
import guru.springframework.springaiintro.models.CapitalResponse;

@Service
public class OpenAIServiceImpl implements OpenAIService {
    
    private final ChatModel chatModel;

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-info-promp.st")
    private Resource getCapitalInfoPrompt;

    @Value("classpath:templates/get-capital-json-prompt.st")
    private Resource getCapitalJsonPrompt;

    @Autowired
    ObjectMapper objectMapper;

    public OpenAIServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        
        ChatResponse response = chatModel.call(prompt);
        
        return response.getResult().getOutput().getContent();
    }

    @Override
    public Answer getAnswer(Question question) {
        return new Answer(getAnswer(question.question()));
    }

    @Override
    public Answer getCapital(CapitalRequest capitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry()));

        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());
    }

    @Override
    public Answer getCapitalInfo(CapitalRequest capitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalInfoPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry()));

        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());
    }

    @Override
    public CapitalResponse getCapitalJson(CapitalRequest capitalRequest) {
        BeanOutputConverter<CapitalResponse> converter = new BeanOutputConverter<>(CapitalResponse.class);
        String format = converter.getFormat();
        System.out.println("Format: \n" + format);

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalJsonPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry(), "format", format));

        ChatResponse response = chatModel.call(prompt);
        System.out.println("Response: \n" + response.getResult().getOutput().getContent());

        return converter.convert(response.getResult().getOutput().getContent());
    }

}
