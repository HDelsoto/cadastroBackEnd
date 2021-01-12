package com.had.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import com.had.apirest.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    List<UsuarioModel> findByCpfIs(String nome);
    Optional<UsuarioModel> findById(Integer Id);
    List<UsuarioModel> findByNomeLike(String nome);
    List<UsuarioModel> findByPerfilLike(String perfil);
    List<UsuarioModel> findBySituacaoLike(String situacao);
    @Query("select u from usuario u where u.nome like :nome and u.situacao like :situacao and u.perfil like :perfil")
    List<UsuarioModel> findByFilter(@Param("nome") String nome, @Param("situacao") String situacao, @Param("perfil") String perfil);
}
