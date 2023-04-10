package com.dba.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dba.demo.model.Promocion;


@Repository
public interface PromocionRepository extends MongoRepository<Promocion, String>{

	
}
