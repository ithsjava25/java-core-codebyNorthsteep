package com.example.warehouse;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse {

//    getInstance(String name) returns the same instance per unique name.
//    addProduct(Product): throw IllegalArgumentException("Product cannot be null.") if null.
//    getProducts(): return an unmodifiable copy.
//            getProductById(UUID): return Optional.
//            updateProductPrice(UUID, BigDecimal): when not found, throw NoSuchElementException("Product not found with id: "). Also track changed products in getChangedProducts().
//    expiredProducts(): return List that are expired.
//    shippableProducts(): return List from stored products.
//    remove(UUID): remove the matching product if present.
    //clearProducts?
private final String name;
private static final Map<String, Warehouse> warehouses = new HashMap<>();
private final Map<UUID, Product> products = new HashMap<>();
private final Set<Product> changedProducts = new HashSet<>();


private Warehouse(String name) {
    this.name = name;
}

    public static Warehouse getInstance(String name) {

    if (warehouses.containsKey(name)) {
        return warehouses.get(name); //Återanvänd det som finns
    } else {
        Warehouse warehouse = new Warehouse(name);//skapa ny om ej funnen
        warehouses.put(name, warehouse);
        return warehouse;
    }
}
public void addProduct(Product product) {
    if (product == null) throw new IllegalArgumentException("Product can't be null");
    products.put(product.uuid(), product);

}
public List<Product> getProducts() {
return List.copyOf(products.values()); //Skickar tillbaka en omodifierad kopia av listan på produkter
}
public Optional<Product> getProductById(UUID productID) {
    //Optional för att ge tillbaka true/false då id kan vara obefintlig
    return Optional.ofNullable(products.get(productID));
}
public void updateProductPrice(UUID productID, BigDecimal newPrice) {
    Product product = products.get(newPrice);
    if (productID == null) throw new NoSuchElementException("Product not found with ID: " + productID);

    product.price(newPrice);
    changedProducts.add(product);
}
public List<Product> getChangedProducts() {
    // returnerar en lista av produkter med ändrat pris
return  List.copyOf(changedProducts);
}
public List<Perishable> expiredProducts() {
    //todo Ska returnera en lista av produkter som gått ur datum
}
public List<Shippable> shippableProducts() {
    //todo Ska returnera en lista av produkter som kan skickas
}
public void remove(UUID productID) {
//todo kod för att ta bort en produkt
}

    //efterfrågat hos testerna rad:
    public void clearProducts() {
    products.clear();
    changedProducts.clear();
    }
}
