package tests;

import config.ApiConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import specs.RestApiSpec;

public class TestBase extends TestData {
    static ApiConfig config = ConfigFactory.create(ApiConfig.class, System.getProperties());


    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = config.baseApiUri();
        RestAssured.basePath = config.basePath();


        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.log().all();
    }
}
