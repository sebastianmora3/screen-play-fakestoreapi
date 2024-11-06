package co.com.calidad.practica.automatizacion.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;
import org.json.JSONException;
import org.json.JSONObject;

public class TheLoginResponse {

    public static Question<Boolean> hasValidToken() {
        return actor -> {
            String response = SerenityRest.lastResponse().asString();
            JSONObject jsonResponse = new JSONObject(response);
            return jsonResponse.has("token") &&
                    !jsonResponse.getString("token").isEmpty();
        };
    }

    public static Question<Boolean> hasErrorMessage() {
        return actor -> {
            String response = SerenityRest.lastResponse().asString();
            return response.contains("error") ||
                    response.contains("username or password is incorrect");
        };
    }

    public static Question<String> token() {
        return actor -> {
            String response = SerenityRest.lastResponse().asString();
            JSONObject jsonResponse = new JSONObject(response);
            return jsonResponse.getString("token");
        };
    }

    public static void printResponse() {
        String response = SerenityRest.lastResponse().asString();
        int statusCode = SerenityRest.lastResponse().getStatusCode();
        System.out.println("Response Body: " + response);
        System.out.println("Status Code: " + statusCode);
        System.out.println("Headers: " + SerenityRest.lastResponse().getHeaders());
    }
}