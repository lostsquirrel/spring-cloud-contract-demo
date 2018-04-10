package demo.spring.cloud.contract.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class FraudDetectionController {

    @PutMapping("/fraudcheck")
    public Object fraudcheck(Map<String, String> params) {

        Map<String, String> result = new HashMap<>();
        result.put("fraudCheckStatus", "FRAUD");
        result.put("rejectionReason", "Amount too high");


        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/vnd.fraud.v1+json")).body(result);
    }
}
