package com.distance;

import com.distance.model.UserDetails;
import com.distance.service.impl.FileReadServiceImpl;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FileReadServiceTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @InjectMocks
    public FileReadServiceImpl fileReadService;

    @Before
    public void setUp() throws IOException {
        File tempFile = testFolder.newFile("tempFile.txt");

        String users = "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", \"longitude\": \"-6.043701\"}\n" +
                "{\"latitude\": \"51.92893\", \"user_id\": 1, \"name\": \"Alice Cahill\", \"longitude\": \"-10.27699\"}\n" +
                "{\"latitude\": \"51.8856167\", \"user_id\": 2, \"name\": \"Ian McArdle\", \"longitude\": \"-10.4240951\"}";

        FileUtils.writeStringToFile(tempFile, users);
    }

    @Test
    public void testsForFileRead() throws IOException {
        String path = testFolder.getRoot()+"/tempFile.txt";
        List<UserDetails> userDetailsList = fileReadService.readUserFile(path);

        Assertions.assertEquals(3, userDetailsList.size());

        for (UserDetails user: userDetailsList){
            assert (user.getUserId()>0);
            assert (user.getLatitude()!=0.0);
            assert (user.getLongitude()!=0.0);
            assert (user.getUserName()!=null && !"".equals(user.getUserName()));
        }

        for (UserDetails user: userDetailsList){
            assert (user.getUserId()==12||user.getUserId()==1||user.getUserId()==2);
            assert ("Christina McArdle".equals(user.getUserName()) ||
                    "Alice Cahill".equals(user.getUserName()) || "Ian McArdle".equals(user.getUserName()));
        }
    }
}
