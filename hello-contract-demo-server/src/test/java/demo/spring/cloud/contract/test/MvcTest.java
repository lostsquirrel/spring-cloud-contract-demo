package demo.spring.cloud.contract.test;

import demo.spring.cloud.contract.controller.FraudDetectionController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class MvcTest {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new FraudDetectionController());
    }
}
