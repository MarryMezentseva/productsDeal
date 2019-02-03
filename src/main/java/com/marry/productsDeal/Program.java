package com.marry.productsDeal;

import com.marry.productsDeal.entities.*;

import java.util.*;

public class Program {

    private Deal deal;
    private List<Product> allProducts = new LinkedList<>();

    public static void main(String[] args) {
        Program program = new Program();
        program.productsBase();
        program.run();
    }

    private void run() {
        deal = inputDeal();
        printDeal();
    }

    private boolean checkCondition(String msg) {
        String answer = keyboard(msg + "(y - Yes, n - No)");
        return "y".compareToIgnoreCase(answer) == 0;
    }

    private Deal inputDeal() {

        System.out.println("input buyer -> ");
        User buyer = inputUser();

        System.out.println("input seller -> ");
        User seller = inputUser();

        Deal deal = new Deal(seller, buyer);

        while (checkCondition("input products?")) {
            Product product = inputProduct();
            allProducts.add(product);
            int quantity = Integer.parseInt(keyboard("quantity of product "));
            deal.getProducts().put(product, quantity);
        }
        if (deal.getProducts().size() < 1) {
            System.out.println("You should choose or create 1 product at least");
            System.exit(-1);
        }
        return deal;
    }

    private Product inputProduct() {
        while (checkCondition("do you want to choose products from data base?")) {
            Product fromBase = null;
            try {
                fromBase = findFromProductsBase();
            } catch (NonexistentProduct nonexistentProduct) {
                nonexistentProduct.printStackTrace();
            }
            return fromBase;
        }
        Product product = null;
        if (checkCondition("do you want to choose products from list?")) {
            String productModeInput = keyboard("1 - CameraProduct, 2 - ShoesProduct");

            switch (productModeInput) {
                case "1":
                    product = createCameraProduct();
                    break;
                case "2":
                    product = createShoesProduct();
                    break;
                default:
                    System.err.print("nonexistent product");
                    System.exit(-1);
            }

            product.setTitle(keyboard("title "));
            product.setPrice(Double.parseDouble(keyboard("price ")));
            return product;
        } else {
            return createProduct();
        }
    }

    private CameraProduct createCameraProduct() {
        boolean digital = Boolean.parseBoolean(keyboard("true - digital, false - non-digital"));
        double megapixel = Double.parseDouble(keyboard("megapixels "));
        CameraProduct cameraProduct = new CameraProduct();
        cameraProduct.setDigital(digital);
        cameraProduct.setMegapixel(megapixel);
        return cameraProduct;
    }

    private ShoesProduct createShoesProduct() {
        int size = Integer.parseInt(keyboard("size "));
        String colour = keyboard("colour ");
        ShoesProduct shoesProduct = new ShoesProduct();
        shoesProduct.setColour(colour);
        shoesProduct.setSize(size);
        return shoesProduct;
    }

    private Product findFromProductsBase() throws NonexistentProduct {
        String name = keyboard("product name (from database) ");
        for (Product productFromBase : allProducts) {
            String result = productFromBase.getTitle();
            if (name.equals(result)) {
                return productFromBase;
            }
        }
        throw new NonexistentProduct("product with name: " +  name + " not found!");
    }


    private Product createProduct() {
        System.out.println("please, create product yourself");
        String title = keyboard("input product title ");
        double price = Double.parseDouble(keyboard("price for product "));
        return new Product(title, price);
    }

    private void printDeal() {

        System.out.println("Deal -> " + deal.getDate());
        System.out.println("    " + deal.getBuyer().getName()
                + " buys from " + deal.getSeller().getName());

        for (Map.Entry<Product, Integer> entry : deal.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("    " + product.getTitle() + " " + quantity
                    + " " + " * " + " " + product.getPrice());
        }
        System.out.println("    Sum: " + deal.getSum());
        System.out.println("----------------------------");
    }

    private User inputUser() {
        User user = new User();
        String name = keyboard("name");
        user.setName(name);
        String address = keyboard("address");
        user.setAddress(address);
        return user;
    }

    private String keyboard(String message) {
        System.out.print(message + ": ");
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }

    private void productsBase() {
        Product product1 = new Product("nut", 450.90);
        Product product2 = new Product("orange", 253.50);
        Product product3 = new Product("apple", 99.90);
        Product product4 = new Product("beans", 59.0);
        Product product5 = new Product("soy", 185.55);
        allProducts.addAll(Arrays.asList(product1, product2, product3, product4, product5));
    }
}

