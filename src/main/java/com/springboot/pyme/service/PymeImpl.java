package com.springboot.pyme.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.pyme.document.Pyme;
import com.springboot.pyme.repo.PymeRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PymeImpl implements PymeInterface {


  private static final Logger LOGGER = LoggerFactory.getLogger(PymeImpl.class);

  @Autowired
  PymeRepo repo;

//  @Autowired
//  UtilConvert convert;

  public Flux<Pyme> findAll() {

    return repo.findAll();
  }

  public Mono<Pyme> findById(String id) {

    return repo.findById(id);
  }

  public Mono<Pyme> save(Pyme pyme) {

	  pyme.setCreateDate(new Date());
    return repo.save(pyme);
  }

  public Mono<Pyme> update(Pyme personalVip,String id) {

    return repo.findById(id).flatMap(p -> {

      p.setNumDoc(personalVip.getNumDoc());
      p.setName(personalVip.getName());
      p.setAddress(personalVip.getAddress());
      p.setUpdateDate(new Date());
      p.setIdCuentas(personalVip.getIdCuentas());
      
      return repo.save(p);

    });
  }

  public Mono<Void> delete(Pyme pyme) {
    return repo.delete(pyme);
  }

//  public Mono<Personal> saveDto(PersonalDto personalDto) {
//
//    return save(convert.convertPersonal(personalDto));
//  }
//
//  @Override
//  public Mono<Personal> nameSearch(String name) {
//
//    return repo.findByName(name);
//  }
//
//  @Override
//  public Mono<Personal> findByNumDoc(String numDoc) {
//
//    return repo.findByNumDoc(numDoc);
//  }

}
