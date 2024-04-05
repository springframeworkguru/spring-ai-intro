package guru.springframework.springaiintro.services;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.GetCapitalRequest;
import guru.springframework.springaiintro.model.GetCapitalResponse;
import guru.springframework.springaiintro.model.Question;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface OpenAIService {

    Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest);
    GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest);
    String getAnswer(String question);

    Answer getAnswer(Question question);
}
