package co.fredyjimenezrendon.proyectobase.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Acceso {
	
	@RequestMapping("/login")
	public ModelAndView mostrarLogin(){
		return new ModelAndView("login");
	}
	
	@RequestMapping("/inicio")
	public ModelAndView mostrarInicio(){
		return new ModelAndView("inicio");
	}
	
}
