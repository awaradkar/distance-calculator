package com.distance;

import com.distance.model.UserDetails;
import com.distance.controller.DistanceCalcController;
import com.distance.service.DistanceCalcService;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DistanceCalcContollerTest {

    @LocalServerPort
    private int port = 9090;
    public WireMockServer wireMockServer = new WireMockServer(port);

    private MockMvc mockMvc;

    private MockRestServiceServer server;

    @MockBean
    private DistanceCalcService distanceCalcService;

    @Autowired
    private DistanceCalcController distanceCalcController;

    @Before
    public void setUp() throws IOException {

        wireMockServer.stubFor(get(urlMatching("/v1/userDistances/.*")).willReturn(
                aResponse()
                        .withStatus(200)
        ));

        mockMvc = MockMvcBuilders.standaloneSetup(distanceCalcController)
                .addPlaceholderValue("cors.allowed.origin", "*")
                .build();

        UserDetails user1 = new UserDetails();
        user1.setUserId(1);
        user1.setDistance(150.0);
        user1.setUserName("Alan Kenny");
        user1.setLatitude(55.789);
        user1.setLongitude(-10.90);

        UserDetails user2 = new UserDetails();
        user2.setUserId(2);
        user2.setDistance(50.0);
        user2.setUserName("John Jacobs");
        user2.setLatitude(54.789);
        user2.setLongitude(-8.90);

        UserDetails user3 = new UserDetails();
        user3.setUserId(3);
        user3.setDistance(120.0);
        user3.setUserName("John Danny");
        user3.setLatitude(53.789);
        user3.setLongitude(-7.90);

        List<UserDetails> filteredList = Arrays.asList(user1, user2, user3);

        Mockito.when(distanceCalcService.getUserList()).thenReturn(filteredList);
    }

    @Test
    public void getUserListReturns200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/userDistances/")
                .header("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.status().is(200));

        Mockito.verify(distanceCalcService).getUserList();

    }
}
