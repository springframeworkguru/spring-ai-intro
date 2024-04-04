package guru.springframework.springaiintro.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenAIServiceImplTest {

    @Autowired
    OpenAIService openAIService;

    @Test
    void getAnswer() {

        String answer = openAIService.getAnswer("Create JSON for the following: There are 3 people, two males. " +
                "One is named Mark. Another is named Joe. And a third person is a woman named Sam. The woman is age 20 " +
                "and the two men are both 19.");
        System.out.println("Got the answer");
        System.out.println(answer);
    }
}