package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	//Optional<Pedido> findByUsuario(Usuario user);

}