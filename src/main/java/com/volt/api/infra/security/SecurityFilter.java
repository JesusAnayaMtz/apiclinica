package com.volt.api.infra.security;

import com.volt.api.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //obtener el token  del header
        var token = request.getHeader("Authorization");
        //PERMITIMOS EL ACCESO PARA QUE SE GENERE EL TOKEN
        if(token != null){
            token = token.replace("Bearer", "");
            System.out.println(token);
            System.out.println(tokenService.getSubject(token));  //este usuario tiene sesion
        }
        filterChain.doFilter(request,response);
    }
}