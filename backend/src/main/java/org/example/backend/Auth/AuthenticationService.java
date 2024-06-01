package org.example.backend.Auth;
import jakarta.persistence.EntityManager;
import org.example.backend.Entity.Company;
import org.example.backend.Entity.Role;
import org.example.backend.Entity.User;
import lombok.RequiredArgsConstructor;
import org.example.backend.Repository.User_Repo;
import org.example.backend.config.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final EntityManager entityManager;
    private final User_Repo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Company company = null;
        if (request.getCompanyId() != null && request.getCompanyId() != 0) {
            company = entityManager.find(Company.class, request.getCompanyId());
        }

        if (company != null && !entityManager.contains(company)) {
            company = entityManager.merge(company); // Reattach the detached Company entity
        }
        var user= User.builder()

                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .employeeNumber(request.getEmployeeNumber())
                .company(company)
                .image(request.getImage())
                .position(request.getPosition())
                .status(request.getStatus())
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
}
