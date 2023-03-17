package com.socmed.socmed.modules.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    /**
     * @param request
     * @return status 200 authentication response
     * @Responsibility Register User
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ok(authenticationService.register(request));
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        log.info("inside authenticate endpoint ");
        return ok(authenticationService.authenticate(request));
    }


    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody AuthenticationRefreshTokenRequest request) {
        log.info("inside Refresh end point");
        return ok(authenticationService.refreshToken(request));
    }

    @GetMapping("/testing")
    public String tst(){
        return "successfully deployed";
    }

}
