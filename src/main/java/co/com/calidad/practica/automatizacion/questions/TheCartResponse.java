package co.com.calidad.practica.automatizacion.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;
import org.json.JSONObject;
import org.json.JSONArray;

public class TheCartResponse {

    public static Question<Boolean> hasCorrectInformation(String cartId, int userId, String date, int productId, int quantity) {
        return actor -> {
            String response = SerenityRest.lastResponse().asString();
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray products = jsonResponse.getJSONArray("products");

            return jsonResponse.getInt("id") == Integer.parseInt(cartId) &&
                    jsonResponse.getInt("userId") == userId &&
                    jsonResponse.getString("date").equals(date) &&
                    products.getJSONObject(0).getInt("productId") == productId &&
                    products.getJSONObject(0).getInt("quantity") == quantity;
        };
    }
}