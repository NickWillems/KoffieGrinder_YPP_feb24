package nl.nickwillems.koffiegrinder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class KoffieGrinderControllerTest {
    @MockBean
    private KoffieGrinderService koffieGrinderService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGrindKoffie() throws Exception {
        // Arrange
        Mockito.when(koffieGrinderService.grindKoffie(Mockito.any())).thenReturn(true);
        final String inputBody = "{\"amount\":1, \"grindSize\":1, \"strength\":1}";

        // Act
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/grind")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputBody))

        // Assert
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
