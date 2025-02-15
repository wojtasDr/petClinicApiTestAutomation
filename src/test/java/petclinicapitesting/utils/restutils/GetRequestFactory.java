package petclinicapitesting.utils.restutils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static petclinicapitesting.utils.restutils.PredefinedUsers.ADMIN_USER;

@Component
public class GetRequestFactory {

    @Autowired
    ApiResponseStorage apiResponseStorage;

    public ValidatableResponse sendGetRequest(String endPoint) {
        apiResponseStorage.setCurrentResponse(
                RestAssured
                        .given()
                        .auth()
                        .preemptive()
                        .basic(ADMIN_USER.getUserName(), ADMIN_USER.getPassword())
                        .contentType(ContentType.JSON)
                        .when()
                        .get(endPoint)
                        .then()
                        .log().all().assertThat());

        return apiResponseStorage.getCurrentResponse();
    }
}
