package com.dba.demo.service;

import java.util.List;
import java.util.Optional;

import org.bson.json.JsonObject;
import org.springframework.stereotype.Service;

import com.dba.demo.model.Promocion;
import com.dba.demo.repository.PromocionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromocionService {
	
	private final PromocionRepository promocionRespository;
	
	public Promocion add(Promocion promocion) {
		System.out.println("esta es la campaña "+ promocion);
		return promocionRespository.save(promocion);
	}
	
	
	public List<Promocion>findAll(){
		return promocionRespository.findAll();
	}
	
	public Optional<Promocion> findById(String id) {
		return promocionRespository.findById(id);
	}
	
	public void deleteById(String id) {
		promocionRespository.deleteById(id);
	}

	
	
	
}
