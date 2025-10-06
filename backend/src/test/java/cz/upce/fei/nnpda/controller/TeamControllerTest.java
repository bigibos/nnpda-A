package cz.upce.fei.nnpda.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import cz.upce.fei.nnpda.component.JwtFilter; // Assuming you kept this mock from previous suggestion

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; // For verifying the response body

@WebMvcTest(controllers = TeamController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class TeamControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TeamService teamService;

    @MockitoBean // Keep this to mock the JwtFilter
    private JwtFilter jwtFilter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createTeamTest() throws Exception {
        TeamRequestDTO requestTeam = new TeamRequestDTO("Super Team");
        // Create the expected CarRespondDTO that the service would return
        TeamRespondDTO expectedResponseTeam = new TeamRespondDTO(1L, requestTeam.getName(), null);

        given(teamService.addTeam(ArgumentMatchers.any(TeamRequestDTO.class))) // Be more specific with ArgumentMatchers.any()
                .willReturn(expectedResponseTeam); // Tell the mock to return the correct type

        ResultActions response = mockMvc.perform(post("/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestTeam)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(expectedResponseTeam.getId()))
                .andExpect(jsonPath("$.name").value(expectedResponseTeam.getName()));
    }
}