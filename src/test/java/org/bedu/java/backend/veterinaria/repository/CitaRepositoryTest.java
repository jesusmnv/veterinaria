package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



@DataJpaTest
@AutoConfigureTestDatabase//replace = Replace.NONE)
public class CitaRepositoryTest {
    
    @Autowired
    private CitaRepository citaRepository;
    
    @Test
    @DisplayName("Repository debe ser inyectado")
    void smokeTest() {
        assertNotNull(citaRepository);
  }

}

  
   
