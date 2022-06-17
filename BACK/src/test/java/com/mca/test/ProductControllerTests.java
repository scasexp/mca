package com.mca.test;

import com.mca.test.controller.ProductController;
import com.mca.test.exceptions.APIException;
import com.mca.test.exceptions.ResourceNotFoundException;
import com.mca.test.service.ProductService;
import com.mca.test.service.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@WebMvcTest(ProductController.class)
class ProductControllerTests {

	@MockBean
	private ProductService producService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void checkContentTypeStatusVerbose() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/product/{productId}/similar", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(getJson("products-response.json")))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void checkStatusNotFound() throws Exception {

		Mockito.when(producService.getSimilar(Mockito.any())).thenThrow(ResourceNotFoundException.class);

		mockMvc.perform(MockMvcRequestBuilders.get("/product/{productId}/similar", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(""))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());

	}

	@Test
	public void checkStatusBadRequestFound() throws Exception {

		Mockito.when(producService.getSimilar(Mockito.any())).thenThrow(APIException.class);

		mockMvc.perform(MockMvcRequestBuilders.get("/product/{productId}/similar", -1L))
				.andExpect(MockMvcResultMatchers.status().is5xxServerError());

	}

	@Test
	public void checkDataDeserilize() throws Exception {

		String nameProduct = "Name product 2";
		Double amountProduct = 20.99;

		Mockito.when(producService.getSimilar(Mockito.any())).thenReturn(getInitListProduct(nameProduct,amountProduct));

		mockMvc.perform(MockMvcRequestBuilders.get("/product/{productId}/similar", 1L))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(nameProduct))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(amountProduct));

	}

	private List<ProductDTO> getInitListProduct(String nameProduct, Double amountProduct){
		List<ProductDTO> listProducts = new ArrayList<>();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(Mockito.anyString());
		productDTO.setName(nameProduct);
		productDTO.setPrice(amountProduct);
		productDTO.setAvailability(true);
		listProducts.add(productDTO);

		return listProducts;
	}

	private String getJson(String path) {
		try {
			InputStream jsonStream = this.getClass().getClassLoader().getResourceAsStream(path);
			assert jsonStream != null;
			return new String(jsonStream.readAllBytes());
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}



}
