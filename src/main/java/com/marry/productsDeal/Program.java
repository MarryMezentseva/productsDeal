package com.marry.productsDeal;

import com.marry.productsDeal.readerFactory.FileType;
import com.marry.productsDeal.readerFactory.ReaderFactory;
import com.marry.productsDeal.entities.*;
import com.marry.productsDeal.exceptions.NonExistingProductException;
import com.marry.productsDeal.repository.ProductRepository;
import com.marry.productsDeal.utils.*;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.*;
import java.util.Comparator;

public class Program {

    private Deal deal;
    private ProductRepository productRepository;

    public Program(ProductsReader reader) {
        this.productRepository = new ProductRepository(reader);
    }

    public static void main(String[] args) {
        ProductsReader reader = null;
        String filePath = args[0];
        if (filePath != null) {
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                String extension = FilenameUtils.getExtension(filePath);
                reader = ReaderFactory.getReader(extension, filePath);
            } else {
                List<File> listFiles = Arrays.asList(file.listFiles());
                SortedSet<File> filterFiles = new TreeSet<>((new Comparator<File>() {
                    @Override
                    public int compare(File f1, File f2) {
                        return f1.getAbsolutePath().trim().compareTo(f2.getAbsolutePath().trim());
                    }
                }));
                if (!listFiles.isEmpty()) {
                    for (File listFile : listFiles) {
                        if (
                                listFile.getAbsolutePath().endsWith(FileType.CSV)
                                        || listFile.getAbsolutePath().endsWith(FileType.DB)
                                        || listFile.getAbsolutePath().endsWith(FileType.JSON)
                                        || listFile.getAbsolutePath().endsWith(FileType.XML)) {
                            filterFiles.add(listFile);
                        }
                    }
                }
                reader = ReaderFactory.getReader(FilenameUtils.getExtension(filterFiles.first().getAbsolutePath()),
                        filterFiles.first().getAbsolutePath());
            }
        }
        Program program = new Program(reader);
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
            //productRepository.createBase();

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
        if (checkCondition("do you want to choose products from data base?")) {
            Product fromBase = null;
            try {
                fromBase = productRepository.findByName(keyboard("product name is: "));
            } catch (NonExistingProductException e) {
                e.printStackTrace();
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

