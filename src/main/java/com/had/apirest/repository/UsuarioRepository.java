package com.had.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import com.had.apirest.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    List<UsuarioModel> findByCpfIs(String nome);
    Optional<UsuarioModel> findById(Integer Id);
    List<UsuarioModel> findByNomeLike(String nome);
    List<UsuarioModel> findByPerfilLike(String perfil);
    List<UsuarioModel> findBySituacaoLike(String situacao);
   // @Query(value = "SELECT * FROM Users u WHERE u.status = ?1", nativeQuery = true)
   // List<UsuarioModel> findUserByStatusNative(Integer status);
}
