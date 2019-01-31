package com.marry.productsDeal;

import com.marry.productsDeal.entities.*;

import java.util.*;

public class Program {

    private Deal deal;
    private List<Product> allProducts = new LinkedList<>();

    public static void main(String[] args) {
        Program program = new Program();
        program.run();
    }

    private void run() {
        deal = inputDeal();
        printDeal();
    }

    private boolean checkCondition(String msg) {//rename: checkCondition(String msg)
        String answer = keyboard(msg + "(y - Yes, n - No)");
        return "y".compareToIgnoreCase(answer) == 0;// make swap of arguments
    }

    private Deal inputDeal() {

        System.out.println("input buyer -> ");
        User buyer = inputUser();

        System.out.println("input seller -> ");
        User seller = inputUser();

        Deal deal = new Deal(seller, buyer);

        //add loop (while or do-while) here
        while (checkCondition("input products?")) {
            Product product = inputProduct();
            //add to list your product
            allProducts.add(product);
            int quantity = Integer.parseInt(keyboard("quantity for product "));
            deal.getProducts().put(product, quantity);
        }
        //check if the deal contains more than 1 product: deal.getProducts().size()>0
        if (deal.getProducts().size() < 1) {
            System.out.println("You should choose or create 1 product at least");
            System.exit(-1);
        }
        return deal;
    }

    private Product inputProduct() {
        Product product = null;
        if (checkCondition("do you want to choose products from list?")) {//correct message
            String productModeInput = keyboard("1 - CameraProduct, 2 - ShoesProduct");
            String productTitle = keyboard("title ");
            double productPrice = Double.parseDouble(keyboard("price "));

            switch (productModeInput) {//rename
                case "1":
                    //move it to the standalone private method
                    product = createCameraProduct();
                    break;
                case "2":
                    //move it to the standalone private method
                    product = createShoesProduct();
                    break;
                default:
                    System.err.print("nonexistent product");
                    //handle error here to prevent the npe
                    break;
            }
            product.setTitle(productTitle);
            product.setPrice(productPrice);
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
}

