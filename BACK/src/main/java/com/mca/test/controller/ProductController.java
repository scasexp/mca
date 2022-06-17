package com.mca.test.controller;

import com.mca.test.service.ProductService;
import com.mca.test.service.dto.ProductDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Similar products", notes = "List of similar products to a given one ordered by similarity")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Product Not found"),
            @ApiResponse(code = 500, message = "Internal error service")
    })
    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<ProductDTO>> getSimilar(@PathVariable String productId){
        logger.info("Request similar products for {productId} "+ productId);
        return ResponseEntity.ok(productService.getSimilar(productId));
    }

}




