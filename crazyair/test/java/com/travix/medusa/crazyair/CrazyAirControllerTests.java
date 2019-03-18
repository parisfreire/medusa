package com.travix.medusa.crazyair;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.crazyair.service.CrazyAirService;
import com.travix.medusa.domain.common.service.MockService;
import com.travix.medusa.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.domain.crazyair.CrazyAirResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by parisfreire on 13/03/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CrazyAirControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CrazyAirService crazyAirService;
    @Autowired
    private MockService mockService;
    @Mock
    CrazyAirResponse crazyAirResponse;
    CrazyAirRequest crazyAirRequest;

    @Before
    public void init(){
        initMocks(this);
        crazyAirResponse = mockService.CRAZYAIR_FLIGHTS_LIST.get(0);
        crazyAirRequest = mockService.mockCrazyAirRequest();
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/crazyair/helloCrazyAir")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("hello from Crazy Air")));
    }

    @Test
    public void shouldReturnFlightsFromService() throws Exception{
        MockService mockServiceMockBean = Mockito.mock(MockService.class);

        when(mockServiceMockBean.mockCrazyAirRequest()).thenReturn(crazyAirRequest);
        when(crazyAirService.getFlights(crazyAirRequest)).thenReturn(crazyAirResponse);
    }

    @Test
    public void shouldReturnFlightsFromPostCall() throws Exception{
        String jsonRequest = new ObjectMapper().writeValueAsString(crazyAirRequest);

        mockMvc.perform(post("/crazyair/getFlights")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isOk());
    }
}







