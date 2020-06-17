package com.distance.service.impl;

import com.distance.model.UserDetails;
import com.distance.service.FileReadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileReadServiceImpl implements FileReadService {

    @Override
    public List<UserDetails> readUserFile(String path) throws IOException {
        List<UserDetails> userDetailsList = new ArrayList<>();
        File file =
                new File(path);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            UserDetails user = new ObjectMapper().readValue(s, UserDetails.class);
            userDetailsList.add(user);
        }
        return userDetailsList;
    }
}
