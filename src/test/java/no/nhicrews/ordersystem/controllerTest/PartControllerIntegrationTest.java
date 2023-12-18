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

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllPartsTest() throws Exception {
        mockMvc.perform(get("/parts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    public void addPartTest() throws Exception {
        String jsonPart = "{\"partNumber\": \"3636\"}";
        mockMvc.perform(post("/parts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPart))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.partNumber").value("3636"));
    }

}
