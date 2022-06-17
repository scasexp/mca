package com.mca.test;

import com.mca.test.service.ProductClient;
import com.mca.test.service.ProductService;
import com.mca.test.service.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceTests {

	@MockBean
	private ProductClient productClient;

	@Autowired
	private ProductService producService;


	@Test
	public void checkDataCreation() throws Exception {

		String id = "2";
		String nameProduct = "Name product 2";
		Double amountProduct = 20.99;

		Mockito.when(productClient.getSimilar(Mockito.anyString())).thenReturn(getInitListIds(1));

		Mockito.when(productClient.getProduct(Mockito.anyString())).thenReturn(getProduct(id, nameProduct, amountProduct));

		List<ProductDTO> list = producService.getSimilar("1");

		assertThat(list.size()).isEqualTo(getInitListProduct(id, nameProduct, amountProduct).size());

		assertThat(list.get(0).getName()).isEqualTo(nameProduct);

	}

	private List<Long> getInitListIds(Integer size){
		List<Long> ids = new ArrayList<>();
		for (Long i = 0L; i < size; i++) { ids.add(i); }
		return ids;
	}

	private ProductDTO getProduct(String id, String nameProduct, Double amountProduct){
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(id);
		productDTO.setName(nameProduct);
		productDTO.setPrice(amountProduct);
		productDTO.setAvailability(true);
		return productDTO;
	}

	private List<ProductDTO> getInitListProduct(String id, String nameProduct, Double amountProduct){
		List<ProductDTO> listProducts = new ArrayList<>();
		ProductDTO productDTO = getProduct(id, nameProduct, amountProduct);
		listProducts.add(productDTO);

		return listProducts;
	}

}
