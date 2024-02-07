package org.bedu.java.backend.veterinaria.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;





@AutoConfigureTestDatabase//replace = Replace.NONE)
@AutoConfigureMockMvc
@SpringBootTest
public class CitaControllerE2ETest {

 @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("GET /citas debe devolver una lista vacia")
  void emptyListTest() throws Exception {

    
    MvcResult result = mockMvc.perform(get("/citas"))
        .andExpect(status().isOk())
        .andReturn();

    String content = result.getResponse().getContentAsString();

    assertEquals("[]", content);
  }

  /**
 * @throws Exception
 */
@Test
  @DisplayName("POST /citas revisa http 200 ")
  void MissingInRequestBodyTest() throws Exception {
    MvcResult result = mockMvc.perform(post("/citas").contentType("application/json").content("{\"id\"100L}"))
        .andExpect(status().isOk())
        .andReturn();

    String content = result.getResponse().getContentAsString();

    String expectedResponse = "{\"code\":\"ERR_UNKNOWN\",\"message\":\"Ocurrio un error inesperado\",\"details\":\"none\"}";
    assertEquals(expectedResponse, content);


}












    
}
