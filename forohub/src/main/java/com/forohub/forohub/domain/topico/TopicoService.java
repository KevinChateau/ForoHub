package com.forohub.forohub.domain.topico;

import com.forohub.forohub.domain.curso.Curso;
import com.forohub.forohub.domain.curso.CursoRepository;
import com.forohub.forohub.domain.topico.validaciones.ValidadorTopicos;
import com.forohub.forohub.domain.usuario.Usuario;
import com.forohub.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private List<ValidadorTopicos> validadorTopicos;

    public Topico saveTopicoInDB(DatosRegistroTopico datosRegistroTopico) {

        validadorTopicos.forEach(vp-> vp.validar(datosRegistroTopico));

        Topico topico = new Topico(datosRegistroTopico);
        topico.setUsuario(getUsuario(datosRegistroTopico.autor()));
        topico.setCurso(getCurso(datosRegistroTopico.curso()));

        Topico topicSaved = topicoRepository.save(topico);

        return topicSaved;

    }

    public Usuario getUsuario(String autor) {
        Optional<Usuario> usuario = usuarioRepository.findByNombreIgnoreCase(autor);

        if (usuario.isPresent()) {
            return usuario.get();
        }else {
            throw new IllegalArgumentException("No se encontró Usuario con nombre '" + autor + "' en la DB");
        }
    }

    public Curso getCurso(String cursoName) {
        Optional<Curso> curso = cursoRepository.findByNombreIgnoreCase(cursoName);

        if (curso.isPresent()) {
            return curso.get();
        }else {
            throw new IllegalArgumentException("No se encontró Curso con nombre '" + cursoName + "' en la DB");
        }

    }

    public Topico getTopicoById(Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return topico.get();
        } else {
            throw new ValidationException("El Topico con el id '" + id + "' no fue encontrado en la DB");
        }
    }

    public void validarActualizacionTopicos(DatosRegistroTopico datosRegistroTopico) {
        validadorTopicos.forEach(vp-> vp.validar(datosRegistroTopico));

    }

}
