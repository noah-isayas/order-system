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
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllMachinesTest() throws Exception {
        mockMvc.perform(get("/machines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    public void addMachineTest() throws Exception {
        String jsonMachine = "{\"model\": \"Vaporizor 3000\"}";
        mockMvc.perform(post("/machines").contentType(MediaType.APPLICATION_JSON).content(jsonMachine))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model").value("Vaporizor 3000"));
    }
}
