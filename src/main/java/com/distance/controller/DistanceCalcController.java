package com.distance.controller;

import com.distance.model.UserDetails;
import com.distance.service.DistanceCalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/v1/userDistances/")
@RestController
public class DistanceCalcController {

    @Autowired
    DistanceCalcService distanceCalcService;

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<List<UserDetails>> userList() throws IOException {
        List<UserDetails> userDetailsList = new ArrayList<>();

        userDetailsList = distanceCalcService.getUserList();

        return new ResponseEntity<>(userDetailsList, HttpStatus.OK);
    }
}
