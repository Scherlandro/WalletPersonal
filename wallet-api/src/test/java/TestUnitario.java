import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestUnitario {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGeral() throws Exception {
      /*        https://youtu.be/AGDcPgXj8BU?t=159     */
        mockMvc.perform(get("/api/status"))
           .andExpect(status().is('/'));


    }

}
