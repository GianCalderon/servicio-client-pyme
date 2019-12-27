package com.springboot.pyme.service;

import com.springboot.pyme.document.Pyme;
import com.springboot.pyme.dto.PymeDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PymeInterface  {

  public Flux<Pyme> findAll();

  public Mono<Pyme> findById(String id);
	
  public Mono<Pyme> save(Pyme Pyme);

  public Mono<Pyme> update(PymeDto PymeDto ,String id);

  public Mono<Void> delete(Pyme Pyme);
  
  public Mono<Pyme> saveDto(PymeDto PymeDto);
  
  public Mono<Pyme> nameSearch(String name);
  
  public Mono<Pyme> findByNumDoc(String numDoc);
  

	
}
