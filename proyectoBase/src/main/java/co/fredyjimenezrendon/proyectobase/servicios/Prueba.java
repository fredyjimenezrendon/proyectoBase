package co.fredyjimenezrendon.proyectobase.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.fredyjimenezrendon.proyectobase.dao.GenericDao;
import co.fredyjimenezrendon.proyectobase.dto.Rol;

@RestController
public class Prueba {

	@Autowired
	private GenericDao<Rol> genericDao;
	
	@RequestMapping(value ="/get",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Rol> getUsuarios(){
		return genericDao.findAll(Rol.class);
	}
	
}
