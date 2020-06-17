package com.distance.service.impl;

import com.distance.model.UserDetails;
import com.distance.service.DistanceCalcService;
import com.distance.service.FileReadService;
import com.distance.service.GreatCircleDistanceCalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistanceCalcServiceImpl implements DistanceCalcService {

    @Autowired
    FileReadService fileReadService;

    @Autowired
    GreatCircleDistanceCalcService greatCircleDistanceCalcService;

    @Override
    public List<UserDetails> getUserList() throws IOException {
        List<UserDetails> userDetailsList = new ArrayList<>();

        userDetailsList = fileReadService.readUserFile("customers.txt");

        userDetailsList = greatCircleDistanceCalcService.calculateDistance(userDetailsList);

        userDetailsList = userDetailsList.stream().filter(e->e.getDistance()<100.0).collect(Collectors.toList());
        Collections.sort(userDetailsList);

        return userDetailsList;
    }
}
