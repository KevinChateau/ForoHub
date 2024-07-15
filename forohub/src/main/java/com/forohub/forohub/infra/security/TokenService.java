package com.forohub.forohub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.forohub.forohub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret; //Se obtiene del archivo application-prod.properties

    public String generarToken(CustomUserDetails customUserDetails) {
        System.out.println("Generando token");
        try {
            //Es mala práctica colocar la contraseña en el código
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); //La contraseña para validar la firma
            System.out.println(algorithm);
            return  JWT.create() //Se crea el token
                    .withIssuer("ForoHub") //El que emite el JWT, en este caso, la empresa
                    .withSubject(customUserDetails.getUsername()) //Destinatario
                    .withClaim("id", customUserDetails.getUsuario().getId()) //Devuelve el id del usuario
                    .withExpiresAt(generarFechaExpiracion())//Fecha de validez
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("error al generar token jwt", exception);
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        try {
            System.out.println("Token: " + token);
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); //Validando firma
            System.out.println("Aloritmo: " + algorithm);
            return  JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("ForoHub")
                    // reusable verifier instance
                    .build()
                    .verify(token)
                    .getSubject(); //Se verifica el token

        } catch (JWTVerificationException exception){
            // Invalid signature/claims
            throw new RuntimeException("Token JWT inválido o expirado!" + exception.toString());
//            System.out.println(exception.toString());
        }
    }



    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(5).toInstant(ZoneOffset.of("-05:00"));
    }
}
