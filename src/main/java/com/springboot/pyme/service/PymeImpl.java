package com.springboot.pyme.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.pyme.controller.PymeController;
import com.springboot.pyme.document.Account;
import com.springboot.pyme.document.Pyme;
import com.springboot.pyme.dto.PymeDto;
import com.springboot.pyme.repo.PymeRepo;
import com.springboot.pyme.util.UtilConvert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PymeImpl implements PymeInterface {

	
  private static final Logger LOGGER = LoggerFactory.getLogger(PymeController.class);
	 
	@Autowired
	PymeRepo repo;
	
	@Autowired
	UtilConvert convert;
	
	
	public Flux<Pyme> findAll() {
		
		return repo.findAll();
	}

	public Mono<Pyme> findById(String id) {
		
		return repo.findById(id);
	}
	
	@Override
	public Mono<Pyme> save(Pyme Pyme) {

		Pyme.setCreateDate(new Date());
		Pyme.setUpdateDate(new Date());
		Pyme.setListAccount(new ArrayList<Account>());
		
		return repo.save(Pyme);

	}

	@Override
	public Mono<Pyme> update(PymeDto PymeDto, String ruc) {
		
	    return repo.findByNumDoc(ruc).flatMap(Pyme -> {
	      	
	        List<Account> list = Pyme.getListAccount();
	        
	        Account account = new Account();
	        
	        account.setIdAccount(PymeDto.getIdAccount());
	        account.setNumberAccount(PymeDto.getNumberAccount());
	        account.setNameAccount(PymeDto.getNameAccount());

	        list.add(account);

	        Pyme.setTipoDoc(PymeDto.getTipoDoc());
	        Pyme.setNumDoc(PymeDto.getNumDoc());
	        Pyme.setName(PymeDto.getName());
	        Pyme.setAddress(PymeDto.getAddress());
	        Pyme.setUpdateDate(new Date());
	        Pyme.setListAccount(list);
	        
	        return repo.save(Pyme);
	    
	      });
		
	
	}

	@Override
	public Mono<Void> delete(Pyme Pyme) {
		return repo.delete(Pyme);
	}
	
	//OPERACION QUE EXPONEN SERVICIOS
	
	@Override
	public Mono<Pyme> saveDto(PymeDto PymeDto) {
		
		return save(convert.convertPyme(PymeDto));
	}

	@Override
	public Mono<Pyme> nameSearch(String name) {
		
		return repo.findByName(name);
	}
	
	 @Override
	  public Mono<Pyme> findByNumDoc(String numDoc) {

	    return repo.findByNumDoc(numDoc);
	  }



}
