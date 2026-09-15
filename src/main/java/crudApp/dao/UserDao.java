package crudApp.dao;

import crudApp.model.User;
import java.util.List;

public interface UserDao {

    void saveUser(User user);    // Create

    List<User> getAllUsers();    // Read

    void updateUser(User user);  // Update

    void removeUserById(long id); //Delete



}
