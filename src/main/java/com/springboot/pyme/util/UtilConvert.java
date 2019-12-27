package com.springboot.pyme.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.pyme.document.Account;
import com.springboot.pyme.document.Pyme;
import com.springboot.pyme.dto.PymeDto;

@Component
public class UtilConvert {

	public Pyme convertPyme(PymeDto PymeDto) {
		
		
		Account account = new Account();
		
		account.setIdAccount(PymeDto.getIdAccount());
        account.setNameAccount(PymeDto.getNameAccount());
        account.setNumberAccount(PymeDto.getNumberAccount());
        
        List<Account> lista = new ArrayList<>();
        lista.add(account);
        
        Pyme Pyme = new Pyme();

	    Pyme.setTipoDoc(PymeDto.getTipoDoc());
	    Pyme.setNumDoc(PymeDto.getNumDoc());
	    Pyme.setName(PymeDto.getName()); 
	    Pyme.setAddress(PymeDto.getAddress());
	    Pyme.setCreateDate(new Date());
	    Pyme.setUpdateDate(new Date());
	    Pyme.setListAccount(lista);

	    return 	Pyme;
	  }
}
