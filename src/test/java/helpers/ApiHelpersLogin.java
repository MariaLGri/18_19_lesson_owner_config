
package helpers;
import models.LoginRequestModel;
import models.LoginResponseModel;

import static config.ProjectConfig.getLogin;
import static config.ProjectConfig.getPassword;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static specs.SpecsList.*;
import static tests.TestData.*;

public class ApiHelpersLogin {
    public static LoginResponseModel loginRequest() {
        LoginRequestModel authData = new LoginRequestModel(getLogin(), getPassword());

        return given(RequestSpec(token))
                .baseUri("https://demoqa.com")
                .filter(CustomAllureListener.withCustomTemplates())
                .contentType(JSON)
                .log().all()
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(authResponseSpec(200))
                .extract().as(LoginResponseModel.class);
    }

}
