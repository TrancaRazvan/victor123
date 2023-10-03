package com.app.projectVictor.Services;

import com.app.projectVictor.Customisation.UserDto;
import com.app.projectVictor.Entities.User;

import java.util.List;

public interface UserServiceforLogin {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
