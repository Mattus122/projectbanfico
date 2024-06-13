package com.example.DemoProject.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
public class ValidationService {

    private static List<String> merchantAccess = Arrays.asList("POST","PUT");
    private static List<String> adminAccess = Arrays.asList("DELETE");
    private static List<String> customerAccess = Arrays.asList("GET");

    public boolean validaeteroles(String token, String requestType) throws Exception {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

//        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(payload);
        log.info("Role: " + jsonNode.get("role"));

        /*if(jsonNode.get("role") != null) {
            if (requestType.equals("POST") || requestType.equals("PUT")) {
                if (!jsonNode.get("role").asText().equals("merchant")) {
                    throw new Exception("Role mismatch");
                }
                else if(!jsonNode.get("role").asText().equals("admin")){
                        throw new Exception("Role Mismatch");
                }
                else if(!jsonNode.get("role").asText().equals("customer")){
                    throw new Exception("Role Mismatch");
                }

            }
        }*/

        if(jsonNode.get("role") != null) {
            if(jsonNode.get("role").asText() == "merchant" && !merchantAccess.contains(requestType)){
                throw new Exception("Mismatch ");
            }
            if(jsonNode.get("role").asText() == "admin" && !adminAccess.contains(requestType)){
                throw new Exception("");
            }
            if(jsonNode.get("role").asText() == "consumer" && !customerAccess.contains(requestType)){
                throw new Exception("1");
            }
        }

        return true;
    }
}
