package demo.spring.cloud.contract.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.toomuchcoding.jsonassert.JsonAssertion;
import demo.spring.cloud.contract.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureStubRunner(ids = {"demo.spring.cloud:hello-contract-demo-server:+:stubs:8080"}, workOffline = true)
public class LoanApplicationServiceTests {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ContractVerifierObjectMapper contractVerifierObjectMapper;

    public LoanApplicationServiceTests() {
        this.contractVerifierObjectMapper = new ContractVerifierObjectMapper();
    }

    @Test
    public void testFraudCheck() throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/vnd.fraud.v1+json");
        String response = restTemplate.exchange("http://localhost:8080/fraudcheck", HttpMethod.PUT,
                new HttpEntity<>("{\"clientId\":\"1234567890\",\"loanAmount\":99999}", httpHeaders),
                String.class).getBody();
        System.out.println("================================");
        System.out.println(response);
        DocumentContext parsedJson = JsonPath.parse(contractVerifierObjectMapper.writeValueAsString(response));
//        assertThat(response).isEqualTo("{\"fraudCheckStatus\":\"FRAUD\",\"rejectionReason\":\"Amount too high\"}");
        JsonAssertion.assertThatJson(parsedJson).field("['fraudCheckStatus']").isEqualTo("FRAUD");
        JsonAssertion.assertThatJson(parsedJson).field("['rejectionReason']").isEqualTo("Amount too high");
    }
}
