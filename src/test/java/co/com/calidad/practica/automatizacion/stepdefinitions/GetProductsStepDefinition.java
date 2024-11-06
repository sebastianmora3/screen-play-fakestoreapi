package co.com.calidad.practica.automatizacion.stepdefinitions;

import co.com.calidad.practica.automatizacion.tasks.ConnectTo;
import co.com.calidad.practica.automatizacion.tasks.ConsumerThe;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class GetProductsStepDefinition {

    Actor usuario = Actor.named("usuario");

    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
    }

    @Given("I am connect to capacities of the service")
    public void iAmConnectToCapacitiesOfTheService() {
        usuario.attemptsTo(ConnectTo.theService());
    }
    @When("I get the information of products")
    public void iGetTheInformationOfIndustry() {
        usuario.attemptsTo(ConsumerThe.service());
    }
    @Then("I can see all information about the products")
    public void iCanSeeAllInformationAboutTheProducts() {
        usuario.should(seeThatResponse(response->response.statusCode(200)
                        .body("size()", Matchers.equalTo(20))
                        .body("id", Matchers.notNullValue())
                        .body("title", Matchers.everyItem(Matchers.notNullValue()))
                        .body("price", Matchers.everyItem(Matchers.notNullValue()))
                        .body("category", Matchers.everyItem(Matchers.notNullValue()))
                )
        );
    }



}
