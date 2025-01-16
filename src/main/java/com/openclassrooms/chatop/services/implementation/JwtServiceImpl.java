package com.openclassrooms.chatop.services.implementation;

import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.services.JwtService;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtServiceImpl implements JwtService {

    private final JwtEncoder jwtEncoder;

    public JwtServiceImpl(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public String generateToken(User user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(user.getUsername())
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(SignatureAlgorithm.RS512).build(),
                claims
        );

        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }
}
