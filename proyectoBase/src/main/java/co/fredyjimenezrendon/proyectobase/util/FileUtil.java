package co.fredyjimenezrendon.proyectobase.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	private static final String CATALINA_HOME = "user.home";
	
	private FileUtil(){
		
	}
	
	public static void save(MultipartFile file, String fileName, String subfolder) throws IOException{
		
		String rootPath = System.getProperty(CATALINA_HOME);
		
		File dir = new File(rootPath + File.separator + subfolder);
		
		if (!dir.exists()){
			dir.mkdirs();
		}
		
		String[] fileNameExt = file.getOriginalFilename().split("\\.");
		
		File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName + "." + fileNameExt[1]);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(file.getBytes());
		stream.close();
		
	}
	
	public static InputStream get(String id, String ext, String subfolder) throws IOException{
		
		String rootPath = System.getProperty(CATALINA_HOME);
		
		return new FileInputStream(rootPath + File.separator + subfolder + File.separator + id + "." + ext);
		
	}

	public static void delete(String name, String subfolder) {
		
		String rootPath = System.getProperty(CATALINA_HOME);
		
		File file = new File(rootPath + File.separator + subfolder + File.separator + name);
		
		file.delete();
		
		
	}
	

}
