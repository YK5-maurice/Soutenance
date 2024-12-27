package com.YK5maurice.Inventory_management.Config;

import com.YK5maurice.Inventory_management.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UsersService userDetailsService;

    public JwtRequestFilter(JwtUtil jwtUtil, UsersService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            //extration du token qui commence apres Bearer (apres 7 caracteres)
            String token = authorizationHeader.substring(7);
            //extration du username du token a partir de la methode de extractUsername de la class jwtUtil
            String username = jwtUtil.extractUsername(token);

            //verifie si l'utilisateur est deja dans le contexte de securite de l'app c'est a dire qu'il est connectée
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //cette methodes appelle la methode redefinie dans UserService de l'interface userDetailsService de spring security (elle recupere les details de l'utilisateur a partir de son username)
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //verifie si le token est toujour valide (date expire)
                System.out.println("yyyyyyyyyyyyy:"+token);
                System.out.println("aaaaaaaaaaaaaaaaa:"+jwtUtil.validateToken(token,username));
                if (jwtUtil.validateToken(token,username)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null , userDetails.getAuthorities());
                    System.out.println("yyyyyyyyyyyyy:"+authToken);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);



                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
