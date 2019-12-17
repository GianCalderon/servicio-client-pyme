package com.springboot.pyme.service;

import com.springboot.pyme.document.Pyme;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PymeInterface {

  public Flux<Pyme> findAll();
  
  public Mono<Pyme> findById(String id);
  
  public Mono<Pyme> save(Pyme pyme);
  
  public Mono<Pyme> update(Pyme pyme,String id);
  
  public Mono<Void> delete(Pyme pyme);
  
//  public Mono<Personal> saveDto(PersonalDto personalDto);
//  
//  public Mono<Personal> nameSearch(String name);
//  
//  public Mono<Personal> findByNumDoc(String numDoc);


}
