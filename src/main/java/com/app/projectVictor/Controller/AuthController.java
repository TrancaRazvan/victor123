package com.app.projectVictor.Controller;


import com.app.projectVictor.Customisation.UserDto;
import com.app.projectVictor.Entities.User;
import com.app.projectVictor.Services.UserServiceforLogin;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//Clasa asta este penru login
@Controller
public class AuthController {

//Asta ar trebui teoretic sa apeleze la home, login, registrarea si lista atunci cand cererea e facuta
private UserServiceforLogin userServiceforLogin;

    public AuthController(UserServiceforLogin userServiceforLogin) {
        this.userServiceforLogin = userServiceforLogin;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = (User) userServiceforLogin.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userServiceforLogin.saveUser(userDto);
        return "redirect:/register?success";
    }
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userServiceforLogin.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}



