package co.com.calidad.practica.automatizacion.models;

import java.util.List;

public class CartUpdateRequest {
    private int userId;
    private String date;
    private List<CartProduct> products;

    public CartUpdateRequest(int userId, String date, List<CartProduct> products) {
        this.userId = userId;
        this.date = date;
        this.products = products;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public List<CartProduct> getProducts() { return products; }
    public void setProducts(List<CartProduct> products) { this.products = products; }
}
