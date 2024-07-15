package com.forohub.forohub.domain.topico.validaciones;


import com.forohub.forohub.domain.topico.DatosRegistroTopico;
import com.forohub.forohub.domain.topico.Topico;
import com.forohub.forohub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DuplicidadMensaje implements ValidadorTopicos{

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DatosRegistroTopico datosRegistroTopico) {
        Optional<Topico> topico = topicoRepository.findByMensaje(datosRegistroTopico.mensaje());
        if(topico.isPresent()){
            throw new IllegalArgumentException("Este Topico con el mensaje introducido ya existe en la DB");
        }
    }
}
