package com.example.Product_Service.Services;

import com.example.Product_Service.Dao.ProductRepository;
import com.example.Product_Service.Dto.ProductRequest;
import com.example.Product_Service.Dto.ProductResponse;
import com.example.Product_Service.Entity.Product;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;



    // for add a product
    @Override
    public ProductResponse addProduct(ProductRequest productrequest) {
        Product product = new Product();
        product.setName(productrequest.getName());
        product.setWeight(productrequest.getWeight());
        productRepository.save(product);
        return new ProductResponse(product);
    }

    public ProductResponse getProduct(long id) {
        Optional<Product> prod = productRepository.findById(id);
        ProductResponse productResponse = new ProductResponse();
        if (prod.isPresent()) {
                Product product1 = prod.get();
         return new ProductResponse(product1);
        }else{
            //il faut regeler le  retoure d'erreur
            productResponse.setId(-1);
                }
        return productResponse;

        }


    public List<ProductResponse> getAllProducts() {
        List <Product> prod = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : prod) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setWeight(product.getWeight());
            productResponses.add(productResponse);
        }
        return productResponses;
    }

    @Override
    public void updateProduct(long id, ProductRequest product) {
        Optional<Product> prod = productRepository.findById(id);
        if(prod.isPresent()) {
            Product product1 = prod.get();
            product1.setName(product.getName());
            product1.setWeight(product.getWeight());
            productRepository.save(product1);

        }
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> prod = productRepository.findById(id);
        if (prod.isPresent()) {
            productRepository.delete(prod.get());
        }

    }
    public ProductResponse findProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        ProductResponse productRes = new ProductResponse();
        if(product.isPresent()){
            Product product1 = product.get();
            productRes.setId(product1.getId());
            productRes.setName(product1.getName());
            productRes.setWeight(product1.getWeight());
        }
        return productRes;
    }



}
