package com.distance;

import com.distance.model.UserDetails;
import com.distance.service.impl.FileReadServiceImpl;
import com.distance.service.impl.DistanceCalcServiceImpl;
import com.distance.service.impl.GreatCircleDistanceCalcServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DistanceCalcServiceTest {

    @Mock
    private FileReadServiceImpl fileReadService;

    @Mock
    private GreatCircleDistanceCalcServiceImpl greatCircleDistanceCalcService;

    @InjectMocks
    private DistanceCalcServiceImpl distanceCalcService;

    @Before
    public void setUp() throws IOException {

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

        UserDetails user4 = new UserDetails();
        user4.setUserId(4);
        user4.setDistance(90.0);
        user4.setUserName("Kiara Danny");
        user4.setLatitude(55.789);
        user4.setLongitude(-8.90);

        UserDetails user5 = new UserDetails();
        user5.setUserId(5);
        user5.setDistance(220.0);
        user5.setUserName("John Dujon");
        user5.setLatitude(58.789);
        user5.setLongitude(-7.90);

        List<UserDetails> fileUserList = Arrays.asList(user1, user2, user3, user4, user5);

        Mockito.when(fileReadService.readUserFile(Mockito.anyString())).thenReturn(fileUserList);

        user1.setDistance(120.0);
        user2.setDistance(79.0);
        user3.setDistance(150.0);
        user4.setDistance(50.0);
        user5.setDistance(250.0);

        List<UserDetails> userWithDistanceList = Arrays.asList(user1, user2, user3, user4, user5);

        Mockito.when(greatCircleDistanceCalcService.calculateDistance(Mockito.anyList())).thenReturn(userWithDistanceList);
    }

    @Test
    public void testGetCountOfUserList() throws IOException {

        List<UserDetails> list = distanceCalcService.getUserList();

        assertEquals(2, list.size());
    }

    @Test
    public void testGetListOfUsersWithDistanceLessThan100() throws IOException {

        List<UserDetails> list = distanceCalcService.getUserList();

        for (UserDetails user: list){
            assert(user.getDistance()<100);
        }
    }

    @Test
    public void testGetListOfUsersAreSorted() throws IOException {

        List<UserDetails> list = distanceCalcService.getUserList();

        for (int i=0 ; i< list.size()-1; i++){

            assert(list.get(i).getUserId()<list.get(i+1).getUserId());
        }
    }

    @Test
    public void verifyIfMethodsAreCalled() throws IOException {
        List<UserDetails> list = distanceCalcService.getUserList();

        Mockito.verify(greatCircleDistanceCalcService, Mockito.atLeastOnce()).calculateDistance(Mockito.anyList());
        Mockito.verify(fileReadService, Mockito.atLeastOnce()).readUserFile(Mockito.anyString());
    }
}
