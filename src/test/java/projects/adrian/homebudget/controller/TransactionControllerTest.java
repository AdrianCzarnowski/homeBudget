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
import projects.adrian.homebudget.data.TestData;
import projects.adrian.homebudget.helper.MockMvcHelper;
import projects.adrian.homebudget.model.dto.TransactionDto;
import projects.adrian.homebudget.model.dto.UserDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static projects.adrian.homebudget.helper.TestUtils.getUrl;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestMockConfiguration.class)
@ActiveProfiles(value = "unittest")
public class TransactionControllerTest {
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
    @SneakyThrows
    void getById() {
        //given
        String url = getUrl(ApplicationConstants.TRANSACTION_API_URL, port) + "/{uuid}";
        //when
        MvcResult mvcResult = mockMvc.perform(get(url, TestData.TRANSACTION_ID))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        TransactionDto transactionDto = mockMvcHelper.mapResponse(TransactionDto.class, mvcResult);
        //then
        Assertions.assertNotNull(transactionDto);
//        Assertions.assertEquals(userDto.userId(), UserTestData.USER_ID);

    }
}
