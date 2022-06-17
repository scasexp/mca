package com.mca.test.service;


import com.mca.test.service.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getSimilar(String productId);

}