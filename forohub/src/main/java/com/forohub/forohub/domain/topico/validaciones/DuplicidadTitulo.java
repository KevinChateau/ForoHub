package com.forohub.forohub.domain.topico.validaciones;

import com.forohub.forohub.domain.topico.DatosRegistroTopico;
import com.forohub.forohub.domain.topico.Topico;
import com.forohub.forohub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DuplicidadTitulo implements ValidadorTopicos{

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DatosRegistroTopico datosRegistroTopico) {

        Optional<Topico> topico = topicoRepository.findByTitulo(datosRegistroTopico.titulo());
        if(topico.isPresent()){
            throw new IllegalArgumentException("Este Topico con título '" + datosRegistroTopico.titulo() + "' ya existe en la DB");
        }

    }
}
