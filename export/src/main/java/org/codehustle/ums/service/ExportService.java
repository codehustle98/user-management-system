package org.codehustle.ums.service;

import org.codehustle.ums.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportService {

    @Autowired
    private UserService userService;

    public List<User> getUsers(){
        return userService.getUsers();
    }
}
