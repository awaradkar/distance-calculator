package com.distance.service;

import com.distance.model.UserDetails;

import java.io.IOException;
import java.util.List;

public interface DistanceCalcService {
    List<UserDetails> getUserList() throws IOException;
}
