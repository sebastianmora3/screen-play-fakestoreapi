package co.com.calidad.practica.automatizacion.tasks;

import co.com.calidad.practica.automatizacion.models.CartProduct;
import co.com.calidad.practica.automatizacion.models.CartUpdateRequest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import java.util.Collections;

public class UpdateCart implements Task {
    private final String cartId;
    private final CartUpdateRequest updateRequest;

    public UpdateCart(String cartId, int userId, String date, int productId, int quantity) {
        this.cartId = cartId;
        CartProduct product = new CartProduct(productId, quantity);
        this.updateRequest = new CartUpdateRequest(
                userId,
                date,
                Collections.singletonList(product)
        );
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/carts/" + cartId)
                        .with(request -> request
                                .header("Content-Type", "application/json")
                                .body(updateRequest)
                        )
        );
    }

    public static UpdateCart withInfo(String cartId, int userId, String date, int productId, int quantity) {
        return instrumented(UpdateCart.class, cartId, userId, date, productId, quantity);
    }
}
