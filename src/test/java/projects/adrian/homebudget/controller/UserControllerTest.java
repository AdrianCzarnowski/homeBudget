package projects.adrian.homebudget.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import projects.adrian.homebudget.configuration.TestMockConfiguration;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.data.UserTestData;
import projects.adrian.homebudget.helper.MockMvcHelper;
import projects.adrian.homebudget.model.dto.UserDto;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestMockConfiguration.class)
@ActiveProfiles(value = "unittest")
class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvcHelper mockMvcHelper;

    @PostConstruct
    public void postConstruct() {
        mockMvcHelper = new MockMvcHelper(objectMapper);
    }

    @Test
    void getAllUsers() {
    }

    @Test
    @SneakyThrows
    void getById() {
        //given
        String url = getUrl(ApplicationConstants.USER_API_URL)+"/get-by-id/{uuid}";
        //when
        MvcResult mvcResult = mockMvc.perform(get(url, UserTestData.USER_ID))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        UserDto userDto = mockMvcHelper.mapResponse(UserDto.class, mvcResult);
        //then
        Assertions.assertNotNull(userDto);
//        Assertions.assertEquals(userDto.userId(), UserTestData.USER_ID);

    }

    private String getUrl(String path) {
        return "http://localhost:" + port + path;
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}