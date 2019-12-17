package com.springboot.pyme.repo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.pyme.document.Pyme;

import reactor.core.publisher.Mono;

public interface PymeRepo extends ReactiveMongoRepository<Pyme,String> {

  public Mono<Pyme> findByName(String name);

  @Query("{'nombre': ?0 }")
  public Mono<Pyme> nameSearch(String name);
  
  
  public Mono<Pyme> findByNumDoc(String numDoc);


  
}
