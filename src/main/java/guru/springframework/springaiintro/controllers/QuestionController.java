package guru.springframework.springaiintro.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import guru.springframework.springaiintro.models.Answer;
import guru.springframework.springaiintro.models.CapitalRequest;
import guru.springframework.springaiintro.models.CapitalResponse;
import guru.springframework.springaiintro.models.Question;

import guru.springframework.springaiintro.services.OpenAIService;

@RestController
public class QuestionController {

    private final OpenAIService openAIService;

    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/capital-json")
    public CapitalResponse getCapitalJson(@RequestBody CapitalRequest capitalRequest) {
        return openAIService.getCapitalJson(capitalRequest);
    }

    @PostMapping("/capital-info")
    public Answer getCapitalInfo(@RequestBody CapitalRequest capitalRequest) {
        return openAIService.getCapitalInfo(capitalRequest);
    }

    @PostMapping("/capital")
    public Answer getCapital(@RequestBody CapitalRequest capitalRequest) {
        return openAIService.getCapital(capitalRequest);
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }
    

}
