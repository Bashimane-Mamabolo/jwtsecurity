package org.bash.jwtsecurity.services;

import lombok.RequiredArgsConstructor;
import org.bash.jwtsecurity.models.Role;
import org.bash.jwtsecurity.models.User;
import org.bash.jwtsecurity.models.request.AuthenticationRequest;
import org.bash.jwtsecurity.models.request.RegisterRequest;
import org.bash.jwtsecurity.models.response.AuthenticationResponse;
import org.bash.jwtsecurity.repository.UserRepository;
import org.bash.jwtsecurity.security.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var jwtToken = jwtUtils.generateJwtToken(user);
        return AuthenticationResponse
                .builder()
                .jwtToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtUtils.generateJwtToken(user);

        return AuthenticationResponse
                .builder()
                .jwtToken(jwtToken)
                .build();
    }
}
