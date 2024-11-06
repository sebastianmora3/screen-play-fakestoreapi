package co.com.calidad.practica.automatizacion.tasks;

import co.com.calidad.practica.automatizacion.models.LoginRequest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class Login implements Task {
    private final String username;
    private final String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        LoginRequest loginRequest = new LoginRequest(username, password);

        actor.attemptsTo(
                Post.to("/auth/login")
                        .with(request -> request
                                .relaxedHTTPSValidation()
                                .header("Content-Type", "application/json")
                                .body(loginRequest)
                        )
        );
    }

    public static Login withCredentials(String username, String password) {
        return Tasks.instrumented(Login.class, username, password);
    }
}
