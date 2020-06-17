package com.distance;

import com.distance.model.UserDetails;
import com.distance.service.impl.GreatCircleDistanceCalcServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GreatCircleDistanceCalcServiceTest {

    @InjectMocks
    private GreatCircleDistanceCalcServiceImpl greatCircleDistanceCalcService;

    private List<UserDetails> userDetailsList;

    @Before
    public void setUp(){
        UserDetails user1 = new UserDetails();
        user1.setUserId(1);
        user1.setUserName("Alan Kenny");
        user1.setLatitude(53.2451022);
        user1.setLongitude(-6.238335);

        UserDetails user2 = new UserDetails();
        user2.setUserId(2);
        user2.setUserName("John Jacobs");
        user2.setLatitude(53.1229599);
        user2.setLongitude(-6.2705202);

        UserDetails user3 = new UserDetails();
        user3.setUserId(3);
        user3.setUserName("John Danny");
        user3.setLatitude(53.038056);
        user3.setLongitude(-7.653889);

        userDetailsList = Arrays.asList(user1, user2, user3);
    }

    @Test
    public void testCalculateDistanceMethodToreturnDistanceNotZero(){
        List<UserDetails> list = greatCircleDistanceCalcService.calculateDistance(userDetailsList);

        for (UserDetails userDetails:userDetailsList){
            Assertions.assertNotEquals(0.0, userDetails.getDistance());
        }
    }

    @Test
    public void testCalculateDistanceMethodToReturnPositiveDistance(){
        List<UserDetails> list = greatCircleDistanceCalcService.calculateDistance(userDetailsList);

        for (UserDetails userDetails:userDetailsList){
            assert(!(userDetails.getDistance()<0.0)) ;
        }
    }

    @Test
    public void testCalculateDistanceMethodToCompareDistance(){
        List<UserDetails> list = greatCircleDistanceCalcService.calculateDistance(userDetailsList);

        for (UserDetails userDetails:userDetailsList){
            if(userDetails.getUserId()==1){
                Assertions.assertEquals(10.566929021597229, userDetails.getDistance());
            }

            if(userDetails.getUserId()==2){
                Assertions.assertEquals(24.085273963304363, userDetails.getDistance());
            }

            if(userDetails.getUserId()==3){
                Assertions.assertEquals(98.86864656951926, userDetails.getDistance());
            }
        }
    }
}
