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
public class AddressControllerIntegrationTest {

    // Injecting MockMvc in order to simulate HTTP requests and responses
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllAddressesTest() throws Exception {
        // Tests if the GET request to /addresses returns all addresses correctly
        mockMvc.perform(get("/addresses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }
    @Test
    public void addressNotFoundTest() throws Exception {
        // Tests if the GET request to a non-existing address ID returns a 404 status
        Long falseId = 364L;
        mockMvc.perform(get("/addresses/" + falseId)).andExpect(status().isNotFound());
    }
    @Test
    public void addAddressTest () throws Exception {
        // Tests the addition of a new address using the post method
        String jsonAddress = "{\"street\": \"Test street\", \"city\": \"Hamar\"}";
        mockMvc.perform(post("/addresses").contentType(MediaType.APPLICATION_JSON)
                .content(jsonAddress))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.street").value("Test street"))
                .andExpect(jsonPath("$.city").value("Hamar"));
    }
}
