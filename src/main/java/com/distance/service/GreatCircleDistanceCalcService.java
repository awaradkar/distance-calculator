package com.distance.service;

import com.distance.model.UserDetails;

import java.util.List;

public interface GreatCircleDistanceCalcService {
    List<UserDetails> calculateDistance(List<UserDetails> userDetailsList);
}
