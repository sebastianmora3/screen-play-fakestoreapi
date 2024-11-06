package co.com.calidad.practica.automatizacion.stepdefinitions;

import co.com.calidad.practica.automatizacion.questions.TheLoginResponse;
import co.com.calidad.practica.automatizacion.tasks.ConnectTo;
import co.com.calidad.practica.automatizacion.tasks.Login;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.*;

public class LoginStepDefinitions {

    private Actor usuario = Actor.named("usuario");

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
    }

    @Given("I am connected to the authentication service")
    public void iAmConnectedToTheAuthenticationService() {
        usuario.attemptsTo(ConnectTo.theService());
    }

    @When("I try to login with username {string} and password {string}")
    public void iTryToLoginWithUsernameAndPassword(String username, String password) {
        usuario.attemptsTo(Login.withCredentials(username, password));
    }

    @Then("I should receive a valid token")
    public void iShouldReceiveAValidToken() {
        usuario.should(
                seeThat(TheLoginResponse.hasValidToken()),
                seeThat(TheLoginResponse.token(), not(isEmptyOrNullString()))
        );
    }

    @Then("I should receive an error message")
    public void iShouldReceiveAnErrorMessage() {
        usuario.should(seeThat(TheLoginResponse.hasErrorMessage()));
    }

    @Then("the response status code should be {int} for the login request")
    public void theLoginResponseStatusCodeShouldBe(Integer statusCode) {
        usuario.should(
                seeThatResponse("El código de estado del login debería ser " + statusCode,
                        response -> response.statusCode(statusCode))
        );
    }

}
