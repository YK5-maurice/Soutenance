package com.YK5maurice.Inventory_management.Config;

import com.YK5maurice.Inventory_management.Models.Roles;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.Set;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Clé secrète utilisée pour signer les tokens (doit être une chaîne longue et sécurisée)
    private final String SECRET_KEY = "1234567890123456789012345678901212345678901234567890123456789012";

    /**
     * Génération d'un token JWT avec le nom d'utilisateur et les rôles.
     */
//    public String generateToken(String username, Set<String> roles) { //si on avait une collection de role par user
    public String generateToken(String username, String roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles) // Ajout des rôles en tant que claim
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 heures
                //.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60)) //1 minute
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * Extraction du nom d'utilisateur à partir du token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extraction de la date d'expiration du token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extraction des rôles à partir du token.
     */
    @SuppressWarnings("unchecked")
    public Set<String> extractRoles(String token) {
        return extractClaim(token, claims -> claims.get("roles", Set.class));
    }

    /**
     * Extraction d'un claim générique à partir du token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Validation du token JWT : vérifie si le nom d'utilisateur correspond et si le token n'est pas expiré.
     */
//    public Boolean validateToken(String token, String username) {
//        final String tokenUsername = extractUsername(token);
//        return (tokenUsername.equals(username) && !isTokenExpired(token));
//    }


    public Boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    /**
     * Vérification d'un rôle spécifique dans le token.
     */
    public boolean hasRole(String token, String role) {
        Set<String> roles = extractRoles(token);
        return roles != null && roles.contains(role);
    }

    /**
     * Vérifie si un token est expiré.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    /**
     * Extraction de toutes les informations (claims) contenues dans le token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}

///********************************************************/
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.Set;
//import java.util.function.Function;
//
//@Component
//public class JwtUtil {
//
//    // Clé secrète utilisée pour signer les tokens
//    private final String SECRET_KEY = "1234567890123456789012345678901212345678901234567890123456789012";
//
//    /**
//     * Génération d'un token JWT avec username, password, et roles.
//     */
//    public String generateToken(String username, String password, Set<String> roles) {
//        return Jwts.builder()
//                .setSubject(username) // Nom d'utilisateur
//                .claim("password", password) // Mot de passe (NON recommandé)
//                .claim("roles", roles) // Rôles
//                .setIssuedAt(new Date(System.currentTimeMillis())) // Date de création
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 heures
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Signature
//                .compact();
//    }
//
//    /**
//     * Validation du token JWT : vérifie si le nom d'utilisateur, mot de passe et rôles correspondent.
//     */
//    public Boolean validateToken(String token, String username, String password) {
//        final String tokenUsername = extractUsername(token);
//        final String tokenPassword = extractPassword(token);
//        return (tokenUsername.equals(username) && tokenPassword.equals(password) && !isTokenExpired(token));
//    }
//
//    /**
//     * Extraction du nom d'utilisateur à partir du token.
//     */
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    /**
//     * Extraction du mot de passe à partir du token.
//     */
//    public String extractPassword(String token) {
//        return extractClaim(token, claims -> claims.get("password", String.class));
//    }
//
//    /**
//     * Extraction des rôles à partir du token.
//     */
//    @SuppressWarnings("unchecked")
//    public Set<String> extractRoles(String token) {
//        return extractClaim(token, claims -> claims.get("roles", Set.class));
//    }
//
//    /**
//     * Extraction de la date d'expiration du token.
//     */
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    /**
//     * Extraction d'un claim générique à partir du token.
//     */
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    /**
//     * Vérifie si un token est expiré.
//     */
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    /**
//     * Extraction de toutes les informations (claims) contenues dans le token.
//     */
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}
//
//
///*******************************************************/