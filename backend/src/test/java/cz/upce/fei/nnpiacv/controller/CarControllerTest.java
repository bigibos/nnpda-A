package cz.upce.fei.nnpiacv.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.upce.fei.nnpiacv.dto.CarRequestDTO;
import cz.upce.fei.nnpiacv.dto.CarRespondDTO; // Make sure to import CarRespondDTO
import cz.upce.fei.nnpiacv.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import cz.upce.fei.nnpiacv.component.JwtFilter; // Assuming you kept this mock from previous suggestion

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; // For verifying the response body

@WebMvcTest(controllers = CarController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarService carService;

    @MockitoBean // Keep this to mock the JwtFilter
    private JwtFilter jwtFilter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createCarTest() throws Exception {
        CarRequestDTO requestCar = new CarRequestDTO("BMW", "M3");
        // Create the expected CarRespondDTO that the service would return
        CarRespondDTO expectedResponseCar = new CarRespondDTO(1L, requestCar.getBrand(), requestCar.getName(), null);

        given(carService.addCar(ArgumentMatchers.any(CarRequestDTO.class))) // Be more specific with ArgumentMatchers.any()
                .willReturn(expectedResponseCar); // Tell the mock to return the correct type

        ResultActions response = mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestCar)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(expectedResponseCar.getId()))
                .andExpect(jsonPath("$.brand").value(expectedResponseCar.getBrand()))
                .andExpect(jsonPath("$.name").value(expectedResponseCar.getName()));
    }
}