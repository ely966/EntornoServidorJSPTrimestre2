package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Pedido;
import com.example.demo.model.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	//Optional<Pedido> findByUsuario(Usuario user);

}
