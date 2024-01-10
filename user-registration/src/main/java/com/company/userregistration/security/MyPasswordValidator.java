package com.company.userregistration.security;

import com.company.userregistration.entity.User;
import io.jmix.securityflowui.password.PasswordValidationContext;
import io.jmix.securityflowui.password.PasswordValidationException;
import io.jmix.securityflowui.password.PasswordValidator;
import org.springframework.stereotype.Component;

@Component
public class MyPasswordValidator implements PasswordValidator<User> {

    @Override
    public void validate(PasswordValidationContext<User> context) throws PasswordValidationException {
        if (context.getPassword().length() < 3)
            throw new PasswordValidationException("Password is too short, must be >= 3 characters");
    }
}
