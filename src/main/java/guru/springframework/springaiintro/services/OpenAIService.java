package guru.springframework.springaiintro.services;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.Question;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface OpenAIService {

    String getAnswer(String question);

    Answer getAnswer(Question question);
}
