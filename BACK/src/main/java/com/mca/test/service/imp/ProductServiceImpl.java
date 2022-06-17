package com.mca.test.service.imp;


import com.mca.test.service.ProductService;
import com.mca.test.service.ProductClient;
import com.mca.test.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductClient productClient;

    /**
     * Returns an array of products that are similar to the source product.
     *
     * @param  String  The product id refererence for getting similar products
     * @return  List<ProductDTO>  The list of similar products with their information
     */
    @Override
    public List<ProductDTO> getSimilar(String productId) {

        List<Long> productIds = productClient.getSimilar(productId);
        List<ProductDTO> products = productIds.stream().map(id->productClient.getProduct(id.toString())).collect(Collectors.toList());

        return products;
    }


}
