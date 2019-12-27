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

import com.springboot.pyme.document.Account;
import com.springboot.pyme.document.Pyme;
import com.springboot.pyme.dto.PymeDto;
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
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.findAll()));

	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<Pyme>> search(@PathVariable String id) {

		return service.findById(id).map(e->ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(e))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@PostMapping
	public Mono<ResponseEntity<Pyme>> save(@RequestBody Pyme Pyme) {

		return service.save(Pyme).map(e->ResponseEntity.created(URI.create("/api/Pyme"))
				.contentType(MediaType.APPLICATION_JSON).body(e));

	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<Pyme>> update(@RequestBody PymeDto PymeDto, @PathVariable String id) {

		LOGGER.info("Empresa Recibida para Actualizar :--->"+PymeDto.toString());
		
		return service.update(PymeDto, id).map(e->ResponseEntity
						.created(URI.create("/api/Pyme".concat(e.getId())))
						.contentType(MediaType.APPLICATION_JSON)
						.body(e))
				.defaultIfEmpty(ResponseEntity.notFound().build());
			
	
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
		
		return service.findById(id).flatMap(e->{
			return service.delete(e).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

		
	}
	
	@PostMapping("/savePyme")
	public Mono<ResponseEntity<Pyme>> saveDto(@RequestBody PymeDto PymeDto) {
	
		LOGGER.info("Empresa Recibida :--->"+PymeDto.toString());

		return service.saveDto(PymeDto).map(e->ResponseEntity.created(URI.create("/api/Pyme"))
				.contentType(MediaType.APPLICATION_JSON).body(e));

	}
	
	  @GetMapping("/doc/{ruc}")
	  public Mono<ResponseEntity<Pyme>> searchRuc(@PathVariable String ruc) {

	    return service.findByNumDoc(ruc).map(p -> ResponseEntity.ok()
	      .contentType(MediaType.APPLICATION_JSON).body(p))
	      .defaultIfEmpty(ResponseEntity.notFound().build());

	  }
	
	  @GetMapping("/valid/{ruc}")
	  public Flux<Account> valid(@PathVariable String dni) {
	   
	    return service.findByNumDoc(dni).flatMapMany(cuentas ->{ 

	    	return Flux.fromIterable(cuentas.getListAccount());
	    		
	    });	
	    	
	  }


}
