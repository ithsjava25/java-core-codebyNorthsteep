package com.example.warehouse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
    if (product == null) throw new IllegalArgumentException("Product cannot be null.");
    products.put(product.uuid(), product);

}
public List<Product> getProducts() {
return List.copyOf(products.values()); //Skickar tillbaka en omodifierad kopia av listan på produkter/to. List?
}
public Optional<Product> getProductById(UUID productID) {
    //Optional för att ge tillbaka true/false då id kan vara obefintlig
    return Optional.ofNullable(products.get(productID));
}
public void updateProductPrice(UUID productID, BigDecimal newPrice) {
    Product product = products.get(productID);
    if (product == null) throw new NoSuchElementException("Product not found with id: " + productID);

    product.price(newPrice);
    changedProducts.add(product);
}
public List<Product> getChangedProducts() {
    // returnerar en lista av produkter med ändrat pris
return  List.copyOf(changedProducts);
}
public List<Perishable> expiredProducts() {

    //.values används i detta fallet då map både har key och value, jag vill bara komma åt value
    //Går igenom och filtrerar ut products valörer, lokaliserar alla objekt som har en instans av Perishable-interfacet
// .map används för att gjuta om objekten till Perishable för att kunna använda dess metoder
    // filtrerar
    return products.values().stream().filter(product -> product instanceof Perishable)
            .map(product -> (Perishable) product).filter(Perishable::isExpired).collect(Collectors.toList());
}
public List<Shippable> shippableProducts() {

    return products.values().stream().filter(product -> product instanceof Shippable)
            .map(product -> (Shippable) product).collect(Collectors.toList());
}
public void remove(UUID productID) {
    /**
     * Products.remove(productID) - tar bort produkten i Listan products
     * changedProducts.remove(product) - tar bort produkten om den även finns i Listan changedProducts
     */
    Product product = products.remove(productID);
    changedProducts.remove(product);
}

    //Efterfrågat hos testerna (beforeEach, afterEach) setUp tearDown. Garanterar att varje test startar med ett "tomt" varuhus
    public void clearProducts() {
    products.clear();
    changedProducts.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {

    //.collect(Collectors.groupingBy gör om en ström av Objekt och fördelar dem i en Map där mappens Key blir kategorin för objektet.
       // product->product.category() säger - för varje produkt du hämtar , anropa metoden category - resutatet blir att category blir Key i mappen
    return products.values().stream()
                .collect(Collectors.groupingBy(product -> product.category()));

    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Warehouse warehouse)) return false;
        return Objects.equals(name, warehouse.name) && Objects.equals(products, warehouse.products) && Objects.equals(changedProducts, warehouse.changedProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, products, changedProducts);
    }

}
