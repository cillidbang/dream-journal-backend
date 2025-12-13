package app.dto;

import java.util.List;

public class Dto {
    public record OpenAIResponse(Long created, List<Base64String> data){}
    public record Base64String (String b64_json, String revised_prompt){}
}
