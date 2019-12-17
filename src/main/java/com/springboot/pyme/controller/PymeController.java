package com.springboot.pyme.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pyme.document.Pyme;
import com.springboot.pyme.service.PymeInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/pyme")
public class PymeController {
	
  private static final Logger LOGGER = LoggerFactory.getLogger(PymeController.class);


  @Autowired
  PymeInterface service;

  @GetMapping
  public Mono<ResponseEntity<Flux<Pyme>>> toList() {

    return Mono.just(ResponseEntity.ok()
          .contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Pyme>> search(@PathVariable String id) {

    return service.findById(id).map(p -> ResponseEntity.ok()
      .contentType(MediaType.APPLICATION_JSON).body(p))
      .defaultIfEmpty(ResponseEntity.notFound().build());

  }

  @PostMapping
  public Mono<ResponseEntity<Pyme>> save(@RequestBody Pyme pyme) {

    return service.save(pyme).map(p -> ResponseEntity.created(URI.create("/api/pyme"))
                  .contentType(MediaType.APPLICATION_JSON).body(p));

  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<Pyme>> update(@RequestBody Pyme pyme,
                    @PathVariable String id) {

    return service.update(pyme, id)
             .map(p -> ResponseEntity.created(URI.create("/api/pyme".concat(p.getId())))
             .contentType(MediaType.APPLICATION_JSON).body(p))
             .defaultIfEmpty(ResponseEntity.notFound().build());

  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

    return service.findById(id).flatMap(p -> {
      return service.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
    }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

  }
  
  //OPERACIONES QUE EXPONEN SERVICIOS
  
//  @PostMapping("/save")
//  public Mono<ResponseEntity<PersonalVip>> save(@RequestBody PersonalDto personalDto) {
//
//    return service.saveDto(personalDto).map(p -> ResponseEntity.created(URI.create("/api/personal"))
//                  .contentType(MediaType.APPLICATION_JSON).body(p));
//
//  }
  
//  @GetMapping("/{id}")
//  public Mono<ResponseEntity<Personal>> searchDni(@PathVariable String dni) {
//
//    return service.findByNumDoc(dni).map(p -> ResponseEntity.ok()
//      .contentType(MediaType.APPLICATION_JSON).body(p))
//      .defaultIfEmpty(ResponseEntity.notFound().build());
//
//  }

}
