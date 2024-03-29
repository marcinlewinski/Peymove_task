package com.example.server.initializer;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.server.model.order.Product;
import com.example.server.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Component responsible for initializing data in the application.
 */
@Component
public class DataInitializer {
    private Cloudinary cloudinary;
    @Value("${cloudNameValue}")
    private String cloudNameValue;
    @Value("${apiKeyValue}")
    private String apiKeyValue;
    @Value("${apiSecretValue}")
    private String apiSecretValue;
    private final String PATH = "C:/Users/marci/OneDrive/Pulpit/Recruitment_task_paymove/server/src/main/resources/static/images/";

    private final ProductRepository productRepository;

    /**
     * Constructor to initialize the DataInitializer with required dependencies.
     *
     * @param productRepository ProductRepository instance.
     * @param cloudNameValue    Cloudinary cloud name.
     * @param apiKeyValue       Cloudinary API key.
     * @param apiSecretValue    Cloudinary API secret.
     */
    @Autowired
    public DataInitializer(ProductRepository productRepository,
                           @Value("${cloudNameValue}") String cloudNameValue,
                           @Value("${apiKeyValue}") String apiKeyValue,
                           @Value("${apiSecretValue}") String apiSecretValue) {
        this.productRepository = productRepository;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudNameValue,
                "api_key", apiKeyValue,
                "api_secret", apiSecretValue));
    }

    /**
     * Initialize data by saving mock products into the repository.
     */
    public void init() {
        List<Product> mockProducts = createMockProducts();
        productRepository.saveAll(mockProducts);
    }

    /**
     * Create a list of mock products.If the images are not present in Cloudinary,
     * uncomment the first list and comment the second one.
     *
     * @return List of mock products.
     */
    private List<Product> createMockProducts() {
//        return List.of(
//                createProduct("IPhone", 999.0, "img.png"),
//                createProduct("Macbook Pro 2022 (M1)", 1999.0, "img_1.png"),
//                createProduct("Cannon M50 Camera", 699.0, "img_2.png"),
//                createProduct("WLS Van Gogh Denim Jacket", 228.0, "img_3.png"),
//                createProduct("LED Light Strips", 19.99, "img_4.png"),
//                createProduct("SPECTRUM LS TEE", 68.0, "img_5.png"),
//                createProduct("AUTO SERVICE SHIRT by GOLF WANG", 120.0, "img_6.png"),
//                createProduct("DON'T TRIP UNSTRUCTURED HAT", 40.0, "img_7.png")
//        );
        return List.of(
                createProduct("IPhone", 999.0, "https://res.cloudinary.com/dom6b8yjb/image/upload/v1706739805/flmjpcvgqsacd4tepce2.png"),
                createProduct("Macbook Pro 2022 (M1)", 1999.0, "https://res.cloudinary.com/dom6b8yjb/image/upload/v1706739828/thbdv9pideuierckaebl.png"),
                createProduct("Cannon M50 Camera", 699.0, "https://res.cloudinary.com/dom6b8yjb/image/upload/v1706739834/hwqnuymcjflo5yge2ko6.png"),
                createProduct("WLS Van Gogh Denim Jacket", 228.0, "https://res.cloudinary.com/dom6b8yjb/image/upload/v1706739880/vid13vc9how57b1vbhp7.png"),
                createProduct("LED Light Strips", 19.99, "https://res.cloudinary.com/dom6b8yjb/image/upload/v1706739888/io3fsul78rozmeq5moji.png"),
                createProduct("SPECTRUM LS TEE", 68.0, "https://res.cloudinary.com/dom6b8yjb/image/upload/v1706739891/ocqhquvsdwckdq94ncdz.png"),
                createProduct("AUTO SERVICE SHIRT by GOLF WANG", 120.0, "https://res.cloudinary.com/dom6b8yjb/image/upload/v1706739893/zkmjginvvjimb13eyc51.png"),
                createProduct("DON'T TRIP UNSTRUCTURED HAT", 40.0, "https://res.cloudinary.com/dom6b8yjb/image/upload/v1706739904/pki9x42pnhtgd7fbu7jo.png")
        );
    }

    /**
     * Create a mock product with specified details.
     *
     * @param productName Name of the product.
     * @param price       Price of the product.
     * @param imageName   Name of the image associated with the product.
     * @return Product instance.
     */
    private Product createProduct(String productName, double price, String imageName) {
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setImage(imageName);

//        When the images are not found in Cloudinary
//        uploadImageToCloudinary(imageName, product);

        return product;
    }

    /**
     * Upload an image to Cloudinary and update the product's image URL.
     *
     * @param imageName Name of the image file.
     * @param product   Product instance.
     */
    private void uploadImageToCloudinary(String imageName, Product product) {
        Map uploadResult;
        File imageFile = new File(PATH + imageName);
        try {
            uploadResult = cloudinary.uploader().upload(imageFile, ObjectUtils.emptyMap());
            product.setImage(uploadResult.get("url").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
