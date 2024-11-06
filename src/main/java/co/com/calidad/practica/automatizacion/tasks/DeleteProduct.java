package co.com.calidad.practica.automatizacion.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DeleteProduct implements Task {
    private final String productId;

    public DeleteProduct(String productId) {
        this.productId = productId;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/products/" + productId)
                        .with(request -> request.relaxedHTTPSValidation())
        );
    }

    public static DeleteProduct withId(String productId) {
        return Tasks.instrumented(DeleteProduct.class, productId);
    }
}
