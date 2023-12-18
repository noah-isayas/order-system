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

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllAddressesTest() throws Exception {
        mockMvc.perform(get("/addresses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }
    @Test
    public void addressNotFoundTest() throws Exception {
        Long falseId = 364L;
        mockMvc.perform(get("/addresses/" + falseId)).andExpect(status().isNotFound());
    }
    @Test
    public void addAddressTest () throws Exception {
        String jsonAddress = "{\"street\": \"Test street\", \"city\": \"Hamar\"}";
        mockMvc.perform(post("/addresses").contentType(MediaType.APPLICATION_JSON)
                .content(jsonAddress))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.street").value("Test street"))
                .andExpect(jsonPath("$.city").value("Hamar"));
    }
}
