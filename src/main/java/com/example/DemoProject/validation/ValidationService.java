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

//    private static List<String> merchantAccess = Arrays.asList("POST","PUT");
//    private static List<String> adminAccess = Arrays.asList("DELETE");
//    private static List<String> customerAccess = Arrays.asList("GET");
//if role == nule throw conflict exxception and check if this works
    //then check if merchaant works then validation service is in right direction else code changes have to be made

    public boolean validaeteroles(String token, String requestType) throws Exception {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

//        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(payload);
        log.info("Role: " + jsonNode.get("role"));
        log.info("RequestType :" +requestType);

//        if(jsonNode.get("role") != null) {
//            if (requestType.equals("POST") || requestType.equals("PUT")) {
//                if (!jsonNode.get("role").asText().equals("merchant")) {
//                    throw new Exception("Role mismatch");
//                }
//                else if(!jsonNode.get("role").asText().equals("admin")){
//                        throw new Exception("Role Mismatch");
//                }
//                else if(!jsonNode.get("role").asText().equals("customer")){
//                    throw new Exception("Role Mismatch");
//                }
//
//            }
//        }

//        if(jsonNode.get("role").asText() == "merchant" ||jsonNode.get("role").asText() == "admin" ||jsonNode.get("role").asText() == "consumer" ){
//            if(jsonNode.get("role").asText().equals("merchant") && merchantAccess.contains(requestType)){
//                return true;
//            }
//            if(jsonNode.get("role").asText().equals("admin") && adminAccess.contains(requestType)){
//                return true;
//            }
//            if(jsonNode.get("role").asText().equals("consumer") && customerAccess.contains(requestType)){
//                return true;
//            }
//        }
//        //exception occured that we can specify any other role and it would allow get request not tested for others
//       if(jsonNode.get("role").asText() == "merchant" ||jsonNode.get("role").asText() == "admin" ||jsonNode.get("role").asText() == "consumer" ){
//           if(jsonNode.get("role") != null) {
//               if(jsonNode.get("role").asText().equals("merchant") && !merchantAccess.contains(requestType)){
//                   throw new UnauthorizedException();
//               }
//               if(jsonNode.get("role").asText().equals("admin") && !adminAccess.contains(requestType)){
//                   throw new UnauthorizedException();
//               }
//               if(jsonNode.get("role").asText().equals("consumer") && !customerAccess.contains(requestType)){
//                   throw new UnauthorizedException();
//               }
//           }
//       }
//       else {
//           return false;
//       }
        // Throw custom exception for invalid roles
        if (jsonNode.get("role").asText().equals("merchant")||jsonNode.get("role").asText().equals("admin")||jsonNode.get("role").asText().equals("consumer") ){
            // Proceed with the action for ADMIN or MEMBER roles
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
