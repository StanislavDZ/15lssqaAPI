package reqres;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresTestAPI {

    @Test
    void singlUserTest(){
        given()
                .when()
                .get("https://reqres.in/api/users/9")
                .then()
                .body("data.first_name", is("Tobias"))
                .statusCode(200);
    }

    @Test
    void singlUserNotFoundTest(){
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                //.body("data.first_name", is("Zaratus"))
                .statusCode(404);
    }


    String inputDataCreate = "{\"name\": \"Zaratus\", " +
            "\"job\": \"global legend\"}";
    @Test
    void createUserTest(){
        given()
                .body(inputDataCreate)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .body("name", is("Zaratus"))
                .statusCode(201);
    }



    String inputDataUpdate = "{\"name\": \"Zaratus\", " +
            "\"job\": \"local legend\"}";
    @Test
    void updateUserTest(){
        given()
                .body(inputDataUpdate)
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users")
                .then()
                .body("job", is("local legend"))
                .statusCode(200);
    }

    @Test
    void deleteUserTest(){
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }


    String inputDataRegister = "{\"email\": \"eve.holt@reqres.in\", " +
            "\"password\": \"pistol\"}";
    @Test
    void registerSuccessfulTest(){
        given()
                .body(inputDataRegister)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .statusCode(200);
    }


    String inputDataError = "{\"email\": \"sydney@fife\"}" ;
    @Test
    void registerUnSuccessfulTest(){
        given()
                .body(inputDataError)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400);
    }



}