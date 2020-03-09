package org.isti.learning.springboot.end2end;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testEmptyDb_getReturnsWithEmptyList() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/db-create").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/db-get").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"numberOfPeople\":0,\"people\":[]}")));
        mvc.perform(MockMvcRequestBuilders.delete("/db-drop").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddingOneName_getReturnsWithListThatContainsOnePerson() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/db-create").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.post("/db-insert?id=1&name=Detti").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        String expectedAnswer = "{\"numberOfPeople\":1,\"people\":[{\"id\":1,\"name\":\"Detti\"}]}";
        mvc.perform(MockMvcRequestBuilders.get("/db-get").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expectedAnswer)));
        mvc.perform(MockMvcRequestBuilders.delete("/db-drop").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddingTwoNames_getReturnsWithListThatContainsTwoPeople() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/db-create").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.post("/db-insert?id=1&name=Detti").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.post("/db-insert?id=2&name=Isti").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        String expectedAnswer = "{\"numberOfPeople\":2,\"people\":[{\"id\":1,\"name\":\"Detti\"},{\"id\":2,\"name\":\"Isti\"}]}";
        mvc.perform(MockMvcRequestBuilders.get("/db-get").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expectedAnswer)));
        mvc.perform(MockMvcRequestBuilders.delete("/db-drop").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
