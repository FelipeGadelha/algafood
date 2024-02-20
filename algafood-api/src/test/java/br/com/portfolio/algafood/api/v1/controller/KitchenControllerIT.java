package br.com.portfolio.algafood.api.v1.controller;

import br.com.portfolio.algafood.domain.model.Kitchen;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;


@ActiveProfiles(value = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KitchenControllerIT {

    @LocalServerPort
    private int port;
    private final String BASE_URL = "/api/algafood/v1/kitchens";
    @Autowired private Flyway flyway;

    @BeforeEach
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = BASE_URL;
        flyway.migrate();
    }

    @Test
    @DisplayName("should return status code 200 when search kitchens")
    public void test1() {
        given()
            .port(port)
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("name", hasSize(6))
            .body("name", hasItems("Indiana", "Tailandesa"))
        ;
    }

    @Test
    @DisplayName("should save kitchen successfully")
    public void test3() {
        given()
            .accept(ContentType.JSON)
            .body(new Kitchen(null, "italiana", null))
            .contentType(ContentType.JSON)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.CREATED.value())
        ;
    }
    @Test
    @DisplayName("not should save kitchen when kitchen does not exists")
    public void test4() {
        given()
            .accept(ContentType.JSON)
            .pathParam("id", 1000)
        .when()
            .get("/{id}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
        ;
    }
    @Test
    @DisplayName("should return kitchen when find by id")
    public void test5() {
        given()
            .pathParam("id", 1)
            .accept(ContentType.JSON)
        .when()
            .get("/{id}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("name", equalTo("Tailandesa"))
        ;
    }
}
