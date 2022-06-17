package com.mca.test;

import com.mca.test.service.ProductClient;
import com.mca.test.service.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;



import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductClientTests {


	@Autowired
	private ProductClient productClient;


	@Test
	void checkProductSimilar() throws InterruptedException {
		List<Long> ids = productClient.getSimilar("1");

		assertThat(ids.size()).isEqualTo(3);
	}


	@Test
	void checkProduct() throws InterruptedException {
		ProductDTO productDTO = productClient.getProduct("1");

		assertThat(productDTO.getId()).isEqualTo("1");
		assertThat(productDTO.getName()).isEqualTo("Shirt");
		assertThat(productDTO.getPrice()).isEqualTo(9.99);
		assertThat(productDTO.getAvailability()).isEqualTo(true);
	}


}
