package no.nhicrews.ordersystem.controllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIntegrationTest {

    // Injecting MockMvc in order to simulate HTTP requests and responses
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllOrdersTest() throws Exception {
        // Tests if the GET request to /orders returns all orders correctly
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.content").isArray());
    }

    @Test
    public void addOrderTest() throws Exception {
        // Tests the addition of a new order using the post method
        String jsonOrder = "{\"orderDate\": \"2023-12-12\"}";
        mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON).content(jsonOrder))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderDate").value("2023-12-12"));
    }
}
