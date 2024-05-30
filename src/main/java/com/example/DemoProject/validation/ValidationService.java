package com.example.DemoProject.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
public class ValidationService {
    public boolean validaeteroles(String token, String requestType) throws Exception {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

//        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(payload);
        log.info("Role: " + jsonNode.get("role"));

        if(jsonNode.get("role") != null) {
            if (requestType.equals("POST") || requestType.equals("PUT")) {
                if (!jsonNode.get("role").asText().equals("merchant")) {
                    throw new Exception("Role mismatch");
                }
            }
        }
        if(jsonNode.get("role") != null){
            if(requestType.equals("DELETE")){
                if(!jsonNode.get("role").asText().equals("admin")){
                    throw new Exception("Role Mismatch");
                }
            }
        }
        if(jsonNode.get("role") != null){
            if(requestType.equals("GET")){
                if(!jsonNode.get("role").asText().equals("customer")){
                    throw new Exception("Role Mismatch");
                }
            }
        }
        return false;
    }
}
