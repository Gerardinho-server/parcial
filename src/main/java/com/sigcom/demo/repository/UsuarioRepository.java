package com.sigcom.demo.repository;

import com.sigcom.demo.model.Usuario;
import com.sigcom.demo.model.Rol;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    List<Usuario> findByRol(Rol rol);
}
