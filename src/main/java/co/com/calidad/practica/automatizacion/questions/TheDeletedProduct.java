package co.com.calidad.practica.automatizacion.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.rest.SerenityRest;

public class TheDeletedProduct {

    public static Question<Boolean> hasExpectedFields() {
        return actor -> {
            String response = SerenityRest.lastResponse().asString();
            return response.contains("id") &&
                    response.contains("title") &&
                    response.contains("price") &&
                    response.contains("category") &&
                    response.contains("description") &&
                    response.contains("image");
        };
    }

    public static Question<Boolean> isNull() {
        return actor -> {
            String response = SerenityRest.lastResponse().asString();
            return response.equals("null");
        };
    }
}
