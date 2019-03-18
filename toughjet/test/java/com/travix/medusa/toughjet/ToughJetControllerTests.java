package com.travix.medusa.toughjet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.domain.common.service.MockService;
import com.travix.medusa.domain.toughjet.ToughJetRequest;
import com.travix.medusa.domain.toughjet.ToughJetResponse;
import com.travix.medusa.toughjet.service.ToughJetService;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by parisfreire on 13/03/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ToughJetControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ToughJetService toughJetService;
    @Autowired
    private MockService mockService;
    @Mock
    ToughJetResponse toughJetResponse;
    ToughJetRequest toughJetRequest;

    @Before
    public void init(){
        initMocks(this);
        toughJetResponse = mockService.TOUGHJET_FLIGHTS_LIST.get(0);
        toughJetRequest = mockService.mockToughJetRequest();
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/toughjet/helloToughJet")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("hello from Tough Jet")));
    }

    @Test
    public void shouldReturnFlightsFromService() throws Exception{
        MockService mockServiceMockBean = Mockito.mock(MockService.class);

        when(mockServiceMockBean.mockToughJetRequest()).thenReturn(toughJetRequest);
        when(toughJetService.getFlights(toughJetRequest)).thenReturn(toughJetResponse);
    }

    @Test
    public void shouldReturnFlightsFromPostCall() throws Exception{
        String jsonRequest = new ObjectMapper().writeValueAsString(toughJetRequest);

        mockMvc.perform(post("/toughjet/getFlights")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isOk());
    }
}







