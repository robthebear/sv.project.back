package fr.laposte.sv.project.back.security;

import fr.laposte.sv.project.back.exception.InvalidJWTException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletReponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentification(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (InvalidJWTException ex) {
            // permet de garantir que le AppUser n'est pas authentifié
            SecurityContextHolder.clearContext();
            httpServletReponse.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid JWT provided");
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletReponse);
    }
}
