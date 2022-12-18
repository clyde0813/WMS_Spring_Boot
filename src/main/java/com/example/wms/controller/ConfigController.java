package com.example.wms.controller;

import com.example.wms.form.UserCreateForm;
import com.example.wms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class ConfigController {

    private final UserService userService;

    @GetMapping("/login")
    public String Login() {
        return "config/login";
    }

    @GetMapping("/join")
    public String Join(UserCreateForm userCreateForm) {
        return "config/join";
    }

    @PostMapping("/join")
    public String Join(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "config/join";
        }
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "Incorrect Password!");
            return "config/join";
        }
        if (userCreateForm.getPassword1().length() < 8) {
            bindingResult.rejectValue("password1", "passwordShort", "Too short Password!");
            return "config/join";
        }
        try {
            userService.create(userCreateForm.getUsername(),
                    userCreateForm.getEmail(), userCreateForm.getPassword1());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "Already registered");
            return "config/join";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "config/join";
        }

        return "redirect:/login";
    }
}
