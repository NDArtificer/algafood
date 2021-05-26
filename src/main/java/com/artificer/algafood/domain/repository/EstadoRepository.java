package com.artificer.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artificer.algafood.domain.model.Estado;
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{
	


}
