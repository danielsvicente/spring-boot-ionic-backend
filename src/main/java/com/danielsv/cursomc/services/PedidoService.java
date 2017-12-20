package com.danielsv.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielsv.cursomc.domain.Pedido;
import com.danielsv.cursomc.repositories.PedidoRepository;
import com.danielsv.cursomc.services.exceptions.ObjectNotFoundExpection;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundExpection("Objeto não identificado! Id: " + id
					+ ", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}
}
