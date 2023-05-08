package gpt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenAIAPIClientTest {

    @Autowired OpenAIAPIClient openAIAPIClient;

    @Test
    void generateText() {
        String prompt = "Predict non-duplicate 6 from 1 to 45 numbers";
//        String prompt = "The last answer is 5, 3, 25, 23, 44, 45, Predict non-duplicate 6 from 1 to 45 numbers";
//        String prompt = "You predicted 5 combinations like: 3, 7, 8, 17, 29, 45 and 1, 6, 7, 11, 18, 30 and 6, 12, 17, 21, 35, 43 and 4, 8, 13, 31, 36, 45 and 4, 11, 15, 17, 25, 40. But your prediction is wrong. The last answer is 6, 11, 16, 19, 21, 32, Predict non-duplicate 6 from 1 to 45 numbers";
        String response = null;
        try {
            response = openAIAPIClient.generateText(prompt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 튜딩 후 정상이 아닌 값이 나오기도 함 로또 용으로 max_tokens 값을 12로 수정하면 적당한 6자리 값이 나옴
        // 튜닝할 때 답변의 마지막에 \n을 추가해서 항상 \n으로 끝나니 0번째의 값을 가지고 split하여 6자리 로또 값만 획득 
        String[] splitArray = response.split("\\n");
        System.out.println(splitArray[0]);
    }

}