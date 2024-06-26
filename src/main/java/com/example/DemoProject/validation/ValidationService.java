package com.example.DemoProject.validation;

import com.example.DemoProject.exception.InvalidRoleException;
import com.example.DemoProject.exception.UnauthorizedException;
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
        log.info("RequestType :" +requestType);

        if (jsonNode.get("role").asText().equals("merchant")||jsonNode.get("role").asText().equals("admin")||jsonNode.get("role").asText().equals("consumer") ){
            log.info("Valid Role" + jsonNode.get("role").asText());
        } else throw new InvalidRoleException("400" , "Invalid role: " + jsonNode.get("role").asText());

            if(jsonNode.get("role").asText() .equals( "merchant") && requestType.equals("POST")){
                return true;
            }
            else if(jsonNode.get("role").asText().equals("merchant") && requestType.equals("PUT")){
                return true;
            }
            else if (jsonNode.get("role").asText().equals("admin") && requestType.equals("DELETE")){
                return true;
            }
            else if (jsonNode.get("role").asText().equals("consumer") && requestType.equals("GET")){
                return true;
            }

        throw new UnauthorizedException( "403" , "Access Denied ");


    }
}
