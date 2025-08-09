package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import tests.TestBase;

import static io.restassured.RestAssured.with;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class RestApiSpec extends TestBase {

    public static RequestSpecification baseRequestSpecification = with()
            .header(apiKeyName, apiKey)
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body()
            .contentType(ContentType.JSON);

    public static ResponseSpecification baseResponseSpecification(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(STATUS)
                .log(BODY)
                .build();
    }


}
