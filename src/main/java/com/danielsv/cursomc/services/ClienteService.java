package com.danielsv.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielsv.cursomc.domain.Cliente;
import com.danielsv.cursomc.repositories.ClienteRepository;
import com.danielsv.cursomc.services.exceptions.ObjectNotFoundExpection;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundExpection("Objeto não identificado! Id: " + id
					+ ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}
}
