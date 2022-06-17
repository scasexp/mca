package com.mca.test.service;


import com.mca.test.service.dto.ProductDTO;
import java.util.List;

public interface ProductClient {

    List<Long> getSimilar(String productId);

    ProductDTO getProduct(String productId);

}