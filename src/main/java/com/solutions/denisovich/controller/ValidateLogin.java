package com.solutions.denisovich.controller;

import com.solutions.denisovich.model.entity.User;

public class ValidateLogin {

    public static boolean validateUser(User user) {
        boolean isValidated = false;
        if (user.getLogin() == null) {
            return false;
        }
        if (user.getRole().getName().equals("user")) {
            isValidated = true;
        }
        return isValidated;
    }

    public static boolean validateAdmin(User user) {
        boolean isValidated = false;
        if (user.getLogin() == null) {
            return false;
        }
        if (user.getRole().getName().equals("admin")) {
            isValidated = true;
        }
        return isValidated;
    }
}
