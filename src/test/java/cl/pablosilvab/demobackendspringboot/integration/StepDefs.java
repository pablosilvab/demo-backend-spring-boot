package cl.pablosilvab.demobackendspringboot.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.*;

@Slf4j
public class StepDefs extends SpringIntegrationTest {
    private final static String BASE_URI = "http://localhost";
    @LocalServerPort
    private int port;
    private ValidatableResponse validatableResponse;
    private String jsonString;
    private static Response response;

    private void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }

    protected RequestSpecification requestSpecification() {
        configureRestAssured();
        return RestAssured.given();
    }

    @Given("A list of products are available")
    public void setupEndpoint() {
        validatableResponse = requestSpecification().contentType(ContentType.JSON)
                .when().get("api/v1/products/").then();
        jsonString = validatableResponse.extract().asString();
        log.info("Response: {}", jsonString);
    }

    @Then("I receive valid HTTP response code {int}")
    public void statusCode(int statusCode) {
        validatableResponse.assertThat().statusCode(statusCode);
    }

    @When("I add a product to system")
    public void addProject() throws JSONException {
        String requestBody = "{\n" +
                "  \"name\": \"Bunny\",\n" +
                "  \"url\": \"www.google.cl\" \n}";

        response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("api/v1/products/")
                .then()
                .extract().response();
    }

    @Then("The product is added")
    public void productIsAdded() {
        Assertions.assertEquals(201, response.getStatusCode());
    }

    @And("The products list has more than one item")
    public void theProjectHasTheName() throws JSONException, JsonProcessingException {
        RequestSpecification request = RestAssured.given();
        response = request.get("/api/v1/products/");
        jsonString = response.asString();
        List<Map<String, String>> projects = JsonPath.from(jsonString).get("products");
        Assertions.assertTrue(projects.size() > 0);
    }


}
