package co.com.calidad.practica.automatizacion.stepdefinitions;

import co.com.calidad.practica.automatizacion.questions.TheDeletedProduct;
import co.com.calidad.practica.automatizacion.tasks.ConnectTo;
import co.com.calidad.practica.automatizacion.tasks.DeleteProduct;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class DeleteProductStepDefinitions {

    private Actor usuario = Actor.named("usuario");

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
    }

    @Given("I am connected to the product service")
    public void iAmConnectedToTheProductService() {
        usuario.attemptsTo(ConnectTo.theService());
    }

    @When("I try to delete the product with id {string}")
    public void iTryToDeleteTheProductWithId(String productId) {
        usuario.attemptsTo(DeleteProduct.withId(productId));
    }

    @Then("I should see the deleted product details")
    public void iShouldSeeTheDeletedProductDetails() {
        usuario.should(seeThat(TheDeletedProduct.hasExpectedFields()));
    }

    @Then("I should receive a null response")
    public void iShouldReceiveANullResponse() {
        usuario.should(seeThat(TheDeletedProduct.isNull()));
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(Integer statusCode) {
        usuario.should(seeThatResponse(response -> response.statusCode(statusCode)));
    }
}
