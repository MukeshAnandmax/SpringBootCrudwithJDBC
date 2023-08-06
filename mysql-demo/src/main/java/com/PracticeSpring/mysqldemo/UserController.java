package com.PracticeSpring.mysqldemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    DbManager dbManager;

    @GetMapping(value = "/get_users")
    public List<User> getUsers() throws SQLException {

        return dbManager.getUsers();

    }

    @GetMapping(value = "/get_user/{id}")
    public  User getUser(@PathVariable("id") int id) throws SQLException {

        return dbManager.getUser(id);
    }

    @PostMapping(value = "/insert_user")
    public void insertUser(@RequestBody User user) throws SQLException {
        dbManager.insertUser(user);
        return;
    }

    @DeleteMapping(value = "/delete_user/{id}")
    public void deleteUser(@PathVariable("id") int id) throws SQLException {
        dbManager.deleteUser(id);

    }


    @PutMapping(value = "update_user/{id}")
    public  void updateUser(@PathVariable("id") int id,
                            @RequestBody User user) throws SQLException {
        dbManager.updateUser(user);

    }


}
