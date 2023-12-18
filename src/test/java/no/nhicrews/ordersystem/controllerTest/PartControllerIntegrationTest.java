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
public class PartControllerIntegrationTest {

    // Injecting MockMvc in order to simulate HTTP requests and responses
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllPartsTest() throws Exception {
        // Tests if the GET request to /parts returns all parts correctly
        mockMvc.perform(get("/parts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    public void addPartTest() throws Exception {
        // Tests the addition of a new part using the post method
        String jsonPart = "{\"partNumber\": \"3636\"}";
        mockMvc.perform(post("/parts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPart))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.partNumber").value("3636"));
    }

}
