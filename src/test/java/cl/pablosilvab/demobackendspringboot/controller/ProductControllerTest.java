package cl.pablosilvab.demobackendspringboot.controller;

import cl.pablosilvab.demobackendspringboot.model.Product;
import cl.pablosilvab.demobackendspringboot.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductControllerTest {
    static final String URL = "/api/v1/products/";

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ProductService productService;

    @Test
    @Order(1)
    public void testSave() throws Exception {

        BDDMockito.given(productService.create(Mockito.any(Product.class))).willReturn(getMockProduct());

        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload("Soundbar Samsung", "Soundbar 2.1",
                                150000L, 500L))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("Soundbar Samsung"))
                .andExpect(jsonPath("$.data.description").value("Soundbar 2.1"))
                .andExpect(jsonPath("$.data.price").value(150000))
                .andExpect(jsonPath("$.data.stock").value(500));
    }

    private Product getMockProduct() {
        Product product = new Product(1L,
                "Soundbar Samsung",
                "Soundbar 2.1",
                150000,
                500);
        return product;
    }

    private String getJsonPayload(String name, String description, Long price, Long stock) throws JsonProcessingException {
        Product dto = new Product(0, name, description, price, stock);
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(dto);
    }

}
