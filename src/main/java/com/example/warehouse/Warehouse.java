package com.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a specific warehouse instance, acting as a registry for products.
 * This class implements a Singleton pattern, ensuring only one instance exists per name.
 */
public class Warehouse {


    private final String name;
    // Map used to implement the Singleton pattern (stores instances by name).
    private static final Map<String, Warehouse> warehouses = new HashMap<>();
    // Stores all products currently in the warehouse, keyed by UUID for fast lookup.
    private final Map<UUID, Product> products = new HashMap<>();
    // Tracks products whose price has been updated since being added/last check.
    private final Set<Product> changedProducts = new HashSet<>();

    /**
     * Private constructor to enforce the Multiton pattern.
     *
     * @param name The unique name of the warehouse.
     */
    private Warehouse(String name) {
        this.name = name;
    }

    /**
     * Retrieves the instance of the Warehouse with the specified name.
     * If an instance with that name does not yet exist, a new one is created and cached.
     *
     * @param name The unique name of the warehouse to get or create.
     * @return The existing or newly created Warehouse instance.
     */
    public static Warehouse getInstance(String name) {
        return warehouses.computeIfAbsent(name, Warehouse::new);
    }

    /**
     * Adds a product to the warehouse. Throws an exception if the product is null.
     *
     * @param product The product to add.
     * @throws IllegalArgumentException if the product is null.
     */
    public void addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null.");
        products.put(product.uuid(), product);

    }

    /**
     * Retrieves an unmodifiable copy of all products currently in the warehouse.
     * The returned list is immutable, preventing external modification of the warehouse contents.
     *
     * @return An unmodifiable List of all products.
     */
    public List<Product> getProducts() {

        return List.copyOf(products.values());
    }

    /**
     * Retrieves a product by its unique ID.
     * Uses Optional to clearly indicate whether a product was found or not, avoiding null checks.
     *
     * @param productID The unique identifier of the product.
     * @return An Optional containing the product if found, or an empty Optional otherwise.
     */
    public Optional<Product> getProductById(UUID productID) {

        return Optional.ofNullable(products.get(productID));
    }

    /**
     * Updates the price of an existing product and marks it as 'changed'.
     *
     * @param productID The ID of the product to update.
     * @param newPrice  The new price to set.
     * @throws NoSuchElementException if no product with the given ID is found.
     */
    public void updateProductPrice(UUID productID, BigDecimal newPrice) {
        Product product = products.get(productID);
        if (product == null) throw new NoSuchElementException("Product not found with id: " + productID);

        product.price(newPrice);
        changedProducts.add(product);
    }

    /**
     * Retrieves an unmodifiable list of products whose prices have been updated
     * since they were added or since the last clear of the changed set.
     *
     * @return An unmodifiable List of products that have been modified.
     */
    public List<Product> getChangedProducts() {
        // returnerar en lista av produkter med ändrat pris
        return List.copyOf(changedProducts);
    }

    /**
     * Retrieves a list of all products in the warehouse that implement the Perishable interface
     * AND are currently expired.
     *
     * @return A List of expired Perishable products.
     */
    public List<Perishable> expiredProducts() {
        // Stream filters for Perishable interface, casts, and then filters for the expired status.
        return products.values().stream().filter(product -> product instanceof Perishable)
                .map(product -> (Perishable) product).filter(Perishable::isExpired).collect(Collectors.toList());
    }

    /**
     * Retrieves a list of all products in the warehouse that implement the Shippable interface.
     *
     * @return A List of Shippable products.
     */
    public List<Shippable> shippableProducts() {
        // Stream filters for Shippable interface and safely casts them.
        return products.values().stream().filter(product -> product instanceof Shippable)
                .map(product -> (Shippable) product).collect(Collectors.toList());
    }

    /**
     * Removes a product by its ID from the main collection and the changed products set.
     *
     * @param productID The ID of the product to remove.
     */
    public void remove(UUID productID) {
        Product product = products.remove(productID);
        changedProducts.remove(product);
    }

    /**
     * Clears all products from the warehouse and resets the changed products tracker.
     */
    public void clearProducts() {
        products.clear();
        changedProducts.clear();
    }

    /**
     * Checks if the warehouse currently contains any products.
     *
     * @return true if the products map is empty, false otherwise.
     */
    public boolean isEmpty() {
        return products.isEmpty();
    }

    /**
     * Groups all products in the warehouse by their category.
     *
     * @return A Map where the Key is the Category and the Value is a List of Products belonging to that category.
     */
    public Map<Category, List<Product>> getProductsGroupedByCategories() {

        // Uses the functional groupingBy collector to efficiently create the map.
        return products.values().stream()
                .collect(Collectors.groupingBy(Product::category));

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

    @Override
    public String toString() {
        return "Warehouse{" +
                "changedProducts=" + changedProducts +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
