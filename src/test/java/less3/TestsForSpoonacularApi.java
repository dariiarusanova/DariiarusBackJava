package less3;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestsForSpoonacularApi extends AbstractTest {
    @Test
    void getRecipesExcludeIndian() {
        given()
                .when().get(getBase_url() + "/recipes/complexSearch?" + "excludeCuisine=Indian+false&apiKey=" + getApiKey()).then().statusCode(200);
    }

    @Test
    void SimplePostToCuisine() {
        given().when().request(Method.POST, getBase_url() + "/recipes/cuisine?" + "language=de" + getApiKey()).then().statusCode(200);
    }

    @Test
    void FirstSpecifyRequestByID() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .pathParam("id", 715422)
                .when()
                .get(getBase_url() + "/recipes/{id}/information")
                .then()
                .statusCode(200);
    }

    @Test
    void SecondSpecifyRequestByID() {
        given()
                .when()
                .get(getBase_url() + "/recipes/{id}/information?" +
                        "includeNutrition={Nutrition}&apiKey={apiKey}", 716429, false, getApiKey())
                .then()
                .statusCode(200);
        System.out.println("Object is in base");
    }

    @Test
    void FindObject() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Soy-and-Ginger-Glazed Salmon with Udon Noodles")
                .when()
                .post(getBase_url() + "/recipes/cuisine")
                .then()
                .statusCode(200);
        System.out.println("Object is in base");
    }
    @Test
    void GetResponces(){
        String cuisine = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getBase_url()+"/recipes/cuisine")
                .path("cuisine");

        System.out.println("cuisine: " + cuisine);
    }
    @Test
    void addMealTest() {
        String id = given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1,\n"
                        + " \"slot\": 10,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/geekbrains/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/geekbrains/items/" + id)
                .then()
                .statusCode(200);
    }
    @Test
    void FindChia(){given()
            .queryParam("apiKey", getApiKey())
            .formParam("tags", "chia")
            .when()
            .get(getBase_url() + "/recipes/random")
            .then()
            .statusCode(200);
        System.out.println("Object is in base");
    }

}


