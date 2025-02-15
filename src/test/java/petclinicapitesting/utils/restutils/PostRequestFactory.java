package petclinicapitesting.utils.restutils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static petclinicapitesting.utils.restutils.PredefinedUsers.ADMIN_USER;

@Component
public class PostRequestFactory {

    @Autowired
    ApiResponseStorage apiResponseStorage;

    public ValidatableResponse sendPostRequest(String endPoint, String jsonBody) {
        apiResponseStorage.setCurrentResponse(
                RestAssured
                        .given()
                        .auth()
                        .preemptive()
                        .basic(ADMIN_USER.getUserName(), ADMIN_USER.getPassword())
                        .contentType(ContentType.JSON)
                        .with()
                        .body(jsonBody)
                        .when()
                        .post(endPoint)
                        .then()
                        .log().all()
                        .assertThat());

        return apiResponseStorage.getCurrentResponse();
    }
}
