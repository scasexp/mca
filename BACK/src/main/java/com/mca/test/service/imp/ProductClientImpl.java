package com.mca.test.service.imp;

import com.mca.test.exceptions.APIException;
import com.mca.test.exceptions.ResourceNotFoundException;
import com.mca.test.service.ProductClient;
import com.mca.test.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@Service
public class ProductClientImpl implements ProductClient {

    @Value( "${product-api}" )
    private String API_URL;

    private HttpHeaders getHeaders () {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }


    /**
     * Returns an array of product ids that are similar to the source product.
     *
     * @param  String  The product id refererence for getting similar products
     * @return  List<Long>  The list id of similar products
     */
    @Override
    public List<Long> getSimilar(String productId) {

        try{
            HttpEntity<String> entity = new HttpEntity<String>(getHeaders());
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Long>> response = restTemplate.exchange(API_URL + "/product/" + productId + "/similarids",
                    HttpMethod.GET, entity, new ParameterizedTypeReference<List<Long>>() {});

            return response.getBody();

        } catch (HttpClientErrorException e){
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                throw new ResourceNotFoundException("Product with productId " +productId +" not found ");
            }
            throw new APIException("The following error occurred in the call for productId " +productId +": " +e.getMessage());
        }

    }

    /**
     * Returns information of the product.
     *
     * @param  String  The product id
     * @return  ProductDTO  The object with information related of the product
     */
    @Override
    public ProductDTO getProduct(String productId) {
        try{
            HttpEntity<String> entity = new HttpEntity<String>(getHeaders());
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ProductDTO> response = restTemplate.exchange(API_URL + "/product/" + productId,
                    HttpMethod.GET, entity, new ParameterizedTypeReference<ProductDTO>() {});

            return response.getBody();

        } catch (HttpClientErrorException e){
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                throw new ResourceNotFoundException("Product with productId " +productId +" not found ");
            }
            throw new APIException("The following error occurred in the call for productId " +productId +": "+e.getMessage());
        }

    }


}
