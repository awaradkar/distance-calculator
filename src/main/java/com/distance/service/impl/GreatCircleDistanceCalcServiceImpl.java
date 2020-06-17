package com.distance.service.impl;

import com.distance.model.UserDetails;
import com.distance.service.GreatCircleDistanceCalcService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreatCircleDistanceCalcServiceImpl implements GreatCircleDistanceCalcService {
    @Override
    public List<UserDetails> calculateDistance(List<UserDetails> userDetailsList) {
        Double destinationLatitude = Math.toRadians(53.339428);
        Double destinationLongitude = Math.toRadians(-6.257664);

        for (UserDetails user: userDetailsList) {
            Double srcLatitude = Math.toRadians(user.getLatitude());
            Double srcLongitude = Math.toRadians(user.getLongitude());

            Double deltaLongitude = Math.abs(srcLongitude-destinationLongitude);

            Double numerator = Math.pow((Math.cos(destinationLatitude)*Math.sin(deltaLongitude)),2)+
                    Math.pow(((Math.cos(srcLatitude)*Math.sin(destinationLatitude))-(Math.sin(srcLatitude)*Math.cos(destinationLatitude)*Math.cos(deltaLongitude))),2);

            Double denominator = (Math.sin(srcLatitude)*Math.sin(destinationLatitude))+(Math.cos(srcLatitude)*Math.cos(destinationLatitude)*Math.cos(deltaLongitude));

            Double deltaAngle = Math.atan(Math.sqrt(numerator/denominator));

            Double distance = 6371*deltaAngle;

            user.setDistance(distance);
        }
        return userDetailsList;
    }
}
