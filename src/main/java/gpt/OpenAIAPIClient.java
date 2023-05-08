package gpt;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class OpenAIAPIClient {
    private static final String API_KEY = "개인 API 키 입력";
    private static final String API_URL = "https://api.openai.com/v1/completions";

    public String generateText(String prompt) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL(API_URL).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + API_KEY);

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", prompt);
//        data.put("max_tokens", 12);
        data.put("max_tokens", 500);
        data.put("temperature", 0.7);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();

        System.out.println("output = " + output);
        return new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");
    }
}
