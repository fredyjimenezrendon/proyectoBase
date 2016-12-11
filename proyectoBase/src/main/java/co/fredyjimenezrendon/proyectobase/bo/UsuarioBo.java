package co.fredyjimenezrendon.proyectobase.bo;

import co.fredyjimenezrendon.proyectobase.dto.Usuario;

public interface UsuarioBo {
	
	Usuario obtenerUsuarioPorLogin(String login);

}
