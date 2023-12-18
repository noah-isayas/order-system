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
public class MachineControllerIntegrationTest {

    // Injecting MockMvc in order to simulate HTTP requests and responses
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllMachinesTest() throws Exception {
        // Tests if the GET request to /machines returns all machines correctly
        mockMvc.perform(get("/machines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    public void addMachineTest() throws Exception {
        // Tests the addition of a new machine using the post method
        String jsonMachine = "{\"model\": \"Vaporizor 3000\"}";
        mockMvc.perform(post("/machines").contentType(MediaType.APPLICATION_JSON).content(jsonMachine))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model").value("Vaporizor 3000"));
    }
}
