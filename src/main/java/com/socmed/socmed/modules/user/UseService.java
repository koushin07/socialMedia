package com.socmed.socmed.modules.user;

import com.socmed.socmed.exception.PasswordConfirmationException;
import com.socmed.socmed.exception.ResourceNotFoundException;
import com.socmed.socmed.modules.auth.AuthenticationUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public record UseService(
        UserRepository userRepository
) {

    public User user(Long id, AuthenticationUpdateRequest request) {
        if (request.getPassword().equals(request.getConfirm_password())) {
            throw new PasswordConfirmationException();
        }
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no user to update"));
        user.setPassword(request.getPassword());
        return userRepository.save(user);

    }
}
