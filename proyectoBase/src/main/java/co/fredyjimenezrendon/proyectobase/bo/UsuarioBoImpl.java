package co.fredyjimenezrendon.proyectobase.bo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.fredyjimenezrendon.proyectobase.dao.GenericDao;
import co.fredyjimenezrendon.proyectobase.dto.Usuario;

@Component
public class UsuarioBoImpl implements UsuarioBo {
	
	@Autowired
	GenericDao<Usuario> genericDaoUsuario;

	@Override
	public Usuario obtenerUsuarioPorLogin(String login) {
		
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq("login", login));
		List<Usuario> usuarios = genericDaoUsuario.findBy(Usuario.class, criterions);
		
		Usuario usuario = null;
		
		if(!usuarios.isEmpty()){
			usuario = usuarios.get(0);
		}
		
		return usuario;
	}

}
