package com.forohub.forohub.domain.respuesta;

import com.forohub.forohub.domain.topico.Topico;
import com.forohub.forohub.domain.topico.TopicoRepository;
import com.forohub.forohub.domain.usuario.Usuario;
import com.forohub.forohub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RespuestaService {
    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public Respuesta saveRespuestaInDb(DatosRegistroRespuesta datosRegistroRespuesta) {


        Respuesta respuesta = new Respuesta(datosRegistroRespuesta);
        respuesta.setUsuario(getUsuario(datosRegistroRespuesta.autor()));
        respuesta.setTopico(getTopico(datosRegistroRespuesta.topico()));

        Respuesta respuestaSaved = respuestaRepository.save(respuesta);

        return respuestaSaved;
    }

    public Usuario getUsuario(String autor) {
        Optional<Usuario> usuario = usuarioRepository.findByNombreIgnoreCase(autor);

        if (usuario.isPresent()) {
            return usuario.get();
        }else {
            throw new IllegalArgumentException("No se encontró Usuario con nombre '" + autor + "' en la DB");
        }
    }

    public Topico getTopico(String topicoName) {
        Optional<Topico> topico = topicoRepository.findByTituloIgnoreCase(topicoName);

        if (topico.isPresent()) {
            return topico.get();
        }else {
            throw new IllegalArgumentException("No se encontró Tópico con nombre '" + topicoName + "' en la DB");
        }
    }


}
