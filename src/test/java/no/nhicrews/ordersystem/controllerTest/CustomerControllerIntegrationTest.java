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
public class CustomerControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllCustomersTest () throws Exception {
        mockMvc.perform(get("/customers")).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }
    @Test
    public void addCustomerTest() throws Exception {
        String jsonCustomer = "{\"name\": \"ola nordmann\", \"email\": \"ola@nordmann.com\"}";
        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonCustomer))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("ola nordmann"))
                .andExpect(jsonPath("$.email").value("ola@nordmann.com"));
    }

}
