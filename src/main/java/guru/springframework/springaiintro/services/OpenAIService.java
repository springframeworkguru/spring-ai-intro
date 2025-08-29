package guru.springframework.springaiintro.services;

import guru.springframework.springaiintro.models.Answer;
import guru.springframework.springaiintro.models.CapitalRequest;
import guru.springframework.springaiintro.models.CapitalResponse;
import guru.springframework.springaiintro.models.Question;

public interface OpenAIService {

    String getAnswer(String question);

    Answer getAnswer(Question question);

    Answer getCapital(CapitalRequest capitalRequest);

    Answer getCapitalInfo(CapitalRequest capitalRequest);

    CapitalResponse getCapitalJson(CapitalRequest capitalRequest);

}
