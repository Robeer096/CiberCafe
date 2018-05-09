package ejemplosFiles;

import java.nio.file.*;
import java.io.*;
import static java.nio.file.StandardCopyOption.*; //Necesario para REPLACE_EXISTING

public class EjemplosFiles {
/*primero*/
	

	public static void main(String[] args) {
		
		// Comprueba si el arhivo existe 
		Path p1 = Paths.get("entrada.txt");
		if (!Files.exists(p1))
			System.out.println("ERROR RUTA");

		//Comprueba si existe, tengo permiso para leerlo y es ejecutable		
		boolean isRegularExecutableFile = Files.isReadable(p1) & Files.isExecutable(p1);
		System.out.println("estamos aqui "+isRegularExecutableFile);
		
		// Intentamos borrar el archivo o directorio
		try {
			Files.delete(p1);
		} 
		catch (NoSuchFileException x) {					
			System.err.format("%s: no existe el archivo o directorio\n", p1);
		} 
		catch (DirectoryNotEmptyException x) {	
			System.err.format("%s No está vacío\n", p1);
		} 
		catch (IOException x) {
					// Posibles problemas con permisos 
			System.err.println(x);
		}
			
		
		/* Borra el fihero pero si no existe no lanza excepción. Si tienes varios hilos borrando ficheros 
		 no debemos lanzar una excepción pq otro hilo ya lo borró*/		
		try{
			Files.deleteIfExists(p1);
		}catch (IOException x) {
					// Posibles problemas con permisos 
			System.err.println(x);
		}
				
	
		Path p5 = Paths.get("destino.txt");
		Path p6 = Paths.get("origen.txt");
		
		// Copia origen.txt en destino.txt, si el fichero existe salta la excepción FileAlreadyExists
				// Si queremos que realice la copia aunque exista, usamos ,Files.copy(p6,p5,REPLACE_EXISTING)
		try {
			Files.copy(p6, p5);			
		} 
		catch (NoSuchFileException x)
		{
			System.err.format("%s no existe ",x.getMessage());
		}
		catch (FileAlreadyExistsException x) {
			System.err.format("%s ya existe", x.getMessage());
		} 
		catch (IOException x) {
					// Posibles problemas con permisos 
			System.err.println(x);
		}
				
				
		// Mover un archivo o un directorio a otro a otro
		Path p7 = Paths.get("../origen.txt");
		Path p8 = Paths.get("origen.txt");
		try {
			Files.move(p8, p7,REPLACE_EXISTING);
		
		}
		catch (IOException x) {
					// Posibles problemas con permisos se recogerï¿½an aquï¿½.
			System.err.println(x);
		} 
		                
		// Mostrar los archivos de un directorio    
		if (Files.isDirectory(Paths.get(".."))) {
		try {
		    DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(".."));
		    for (Path path : stream) {
		                System.out.println(path.getFileName());
		            }
		} 
		catch (IOException e) {
		    System.err.println(e);
	   }


		}
	}

}
