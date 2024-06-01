package org.example.backend.Controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.Auth.AuthenticationRequest;
import org.example.backend.Auth.AuthenticationResponse;
import org.example.backend.Auth.AuthenticationService;
import org.example.backend.Auth.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173",maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/authenticate/")
@RequiredArgsConstructor
public class authenticationController {

    private  final AuthenticationService service;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> authentication(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }


}
