package com.marry.productsDeal;

import com.marry.productsDeal.entities.*;

import java.util.*;

public class Program {

    private Deal deal;
    private List<Product> allproducts = new LinkedList<Product>();

    public static void main(String[] args) {
        Program program = new Program();
        program.run();
    }

    public void run() {
        deal = inputDeal();
        printDeal();
    }

    private Deal inputDeal() {

        System.out.println("input buyer -> ");
        User buyer = inputUser();

        System.out.println("input seller -> ");
        User seller = inputUser();

        Deal deal = new Deal(seller, buyer);

        boolean doYouWantToContinueInputProducts = true;
        do {
            Product product = inputProduct();
            int quantity = Integer.parseInt(keyboard("quantity for product "));
            deal.getProducts().put(product, quantity);

            System.out.println("do you want to continue adding products?");
            String choice = keyboard("Yes - y, No - n");
            if (choice.equals("y")) {
                doYouWantToContinueInputProducts = true;
            } else {
                doYouWantToContinueInputProducts = false;
            }
        }
        while (doYouWantToContinueInputProducts);

        return deal;
}

    public Product inputProduct() {

        Product product = null;

        System.out.println("Choose a product from the list?");
        String choice = keyboard("Yes - y, No - n");
        if (choice.equals("n")) {
            product = createProductYourself();
        }
        else if (choice.equals("y")) {

            String chooseProduct = keyboard("1 - CameraProduct, 2 - ShoesProduct");
            String productTitle = keyboard("title ");
            double productPrice = Double.parseDouble(keyboard("price "));
            if (chooseProduct.equals("1")) {
                boolean digital = Boolean.parseBoolean(keyboard("true - digital, false - non-digital"));
                double megapixel = Double.parseDouble(keyboard("megapixels "));
                CameraProduct cameraProduct = new CameraProduct();
                cameraProduct.setDigital(digital);
                cameraProduct.setMegapixel(megapixel);
                product = cameraProduct;
            } else if (chooseProduct.equals("2")) {
                int size = Integer.parseInt(keyboard("size "));
                String colour = keyboard("colour ");
                ShoesProduct shoesProduct = new ShoesProduct();
                shoesProduct.setColour(colour);
                shoesProduct.setSize(size);
                product = shoesProduct;
            }
//            else {
//                System.err.print("nonexistent product");
//            }

            product.setTitle(productTitle);
            product.setPrice(productPrice);

            allproducts.add(product);
        }

        return product;
    }

    public Product createProductYourself(){
        String title = keyboard("input product title ");
        double price = Double.parseDouble(keyboard("price for product "));
        Product yourProduct = new Product(title, price);
        return yourProduct;
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
}

