package co.com.calidad.practica.automatizacion.stepdefinitions;

import co.com.calidad.practica.automatizacion.questions.TheCartResponse;
import co.com.calidad.practica.automatizacion.tasks.ConnectTo;
import co.com.calidad.practica.automatizacion.tasks.UpdateCart;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class UpdateCartStepDefinitions {

    private Actor usuario = Actor.named("usuario");
    private String cartId;
    private int userId;
    private String date;
    private int productId;
    private int quantity;

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("I am connected to the cart service")
    public void iAmConnectedToTheCartService() {
        usuario.attemptsTo(ConnectTo.theService());
    }

    @When("I try to update cart with id {string} with the following data")
    public void iTryToUpdateCartWithTheFollowingData(String cartId, DataTable dataTable) {
        this.cartId = cartId;
        this.userId = Integer.parseInt(dataTable.row(0).get(1));
        this.date = dataTable.row(1).get(1);
        this.productId = Integer.parseInt(dataTable.row(2).get(1));
        this.quantity = Integer.parseInt(dataTable.row(3).get(1));

        usuario.attemptsTo(
                UpdateCart.withInfo(cartId, userId, date, productId, quantity)
        );
    }



    @Then("the cart should contain the correct information")
    public void theCartShouldContainTheCorrectInformation() {
        usuario.should(
                seeThat(TheCartResponse.hasCorrectInformation(
                        cartId, userId, date, productId, quantity
                ))
        );
    }

    @Then("the response status code should be {int} for the cart request")
    public void theCartResponseStatusCodeShouldBe(Integer statusCode) {
        usuario.should(
                seeThatResponse("El código de estado de la actualización del carrito debería ser " + statusCode,
                        response -> response.statusCode(statusCode))
        );
    }
}
