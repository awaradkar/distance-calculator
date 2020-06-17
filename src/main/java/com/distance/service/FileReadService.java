package com.distance.service;

import com.distance.model.UserDetails;

import java.io.IOException;
import java.util.List;

public interface FileReadService {
    List<UserDetails> readUserFile(String path) throws IOException;
}
