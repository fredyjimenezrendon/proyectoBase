package co.fredyjimenezrendon.proyectobase.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {
	
	private PasswordEncoderGenerator(){
		
	}
	
	public static void main(String[] args){
		
		String string = "123";
		
		for (int i = 0; i < 10; i++) {
			encodePassword(string);
		}
		
	}

	public static String encodePassword(String password) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			return passwordEncoder.encode(password);
	}
}
