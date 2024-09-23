package team.fuyuki23.mate.common.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.stereotype.Component;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.vo.Tokens;

import java.io.StringReader;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class JwtService {

    private final JwtConfig jwtConfig;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @PostConstruct
    void init() {
        log.info("JwtService initialized");
        try {
            PEMParser privateParser = new PEMParser(new StringReader(jwtConfig.getPrivateKey()));
            PEMParser publicParser = new PEMParser(new StringReader(jwtConfig.getPublicKey()));
            this.privateKey = new JcaPEMKeyConverter().getPrivateKey((PrivateKeyInfo) privateParser.readObject());
            this.publicKey = new JcaPEMKeyConverter().getPublicKey((SubjectPublicKeyInfo) publicParser.readObject());
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize keys", e);
        }
    }

    public Tokens generateTokens(User user) {
        return new Tokens(
                generateAccessToken(user),
                generateRefreshToken(user)
        );
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .expiration(Date.from(LocalDateTime.now().plusMinutes(15).atZone(ZoneId.systemDefault()).toInstant()))
                .issuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .claims(makeClaims("access", user))
                .signWith(this.privateKey)
                .compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .expiration(Date.from(LocalDateTime.now().plusHours(4).atZone(ZoneId.systemDefault()).toInstant()))
                .issuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .claims(makeClaims("refresh", user))
                .signWith(this.privateKey)
                .compact();
    }

    public UUID parseAccessToken(String token) {
        return parseAccessToken(token, false);
    }

    public UUID parseAccessToken(String token, boolean ignoreError) {
        String userId;
        try {
            var jwt = Jwts.parser()
                    .verifyWith(this.publicKey)
                    .build()
                    .parseSignedClaims(token);

            if (!"access".equals(jwt.getPayload().get("type"))) {
                log.info("invalid access token. token type is not access");
                return null;
            }

            userId = (String) jwt.getPayload().get("userId");
        } catch (ExpiredJwtException e) {
            if (ignoreError) {
                userId = (String) e.getClaims().get("userId");
            } else {
                throw e;
            }
        }

        if (userId == null) {
            log.info("invalid access token. userId not found");
            return null;
        }

        return UUID.fromString(userId);
    }

    public UUID parseRefreshToken(String token) {
        String userId;
        var jwt = Jwts.parser()
                .verifyWith(this.publicKey)
                .build()
                .parseSignedClaims(token);

        if (!"refresh".equals(jwt.getPayload().get("type"))) {
            log.info("invalid refresh token. token type is not refresh");
            return null;
        }

        userId = (String) jwt.getPayload().get("userId");
        if (userId == null) {
            log.info("invalid refresh token. userId not found");
            return null;
        }

        return UUID.fromString(userId);
    }

    private Map<String, String> makeClaims(String type, User user) {
        Map<String, String> claims = new HashMap<>();
        claims.put("type", type);
        claims.put("userId", user.id().toString());
        return claims;
    }

}
