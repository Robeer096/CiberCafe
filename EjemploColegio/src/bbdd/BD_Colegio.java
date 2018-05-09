package bbdd;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.*;
import colegio.*;
import modelos.Alumno;
import modelos.Curso;
import modelos.Tutor;

public class BD_Colegio extends BD_Conector{

	private static Statement s;	
	private static ResultSet reg;
	
	public BD_Colegio(String file){
		super(file);
	}
	
	public int añadir_Alumno( Alumno al){	
		String cadenaSQL="INSERT INTO alumnos VALUES('" + al.getNombre() + "','" +
		al.getDni()+"','"+ al.getTelenono() +"',"+ al.getMatricula()+",'"+
				al.getCurso()+"','"+al.getFechaMatricula()+"')"; 	
		
		try{
		this.abrir();
		s=c.createStatement();
		int filas=s.executeUpdate(cadenaSQL);
		s.close();
		this.cerrar();
		return filas;
		}
		catch ( SQLException e){			
			return -1;
		}
	}
	
	public int añadir_Curso( Curso cur){	
		String cadenaSQL="INSERT INTO cursos VALUES('" + cur.getCurso() + "','" +
		cur.getDescripcion()+"','"+ cur.getAula() +"')"; 	
		
		try{
		this.abrir();
		s=c.createStatement();
		int filas=s.executeUpdate(cadenaSQL);
		s.close();
		this.cerrar();
		return filas;
		}
		catch ( SQLException e){			
			return -1;
		}
	}
	
	public Vector<Alumno> listadoAlumnosCurso(String curso){
		String cadenaSQL="SELECT * from alumnos WHERE curso='"+curso+"'";
		Vector<Alumno> listaCursos=new Vector<Alumno>();
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while ( reg.next()){
				// La fecha que se extrae de la bbdd es sql.Date, hay que transformarla a LocalDate
				java.sql.Date f=reg.getDate("fechaMatricula");
				LocalDate fBuena=f.toLocalDate();
				listaCursos.add(new Alumno(reg.getString("dni"),reg.getString("nombre"),reg.getString("curso"),reg.getInt("matricula"),reg.getString("telefono"),fBuena));
				
			}
			s.close();
			this.cerrar();
			return listaCursos;
		}
		catch ( SQLException e){		
			return null;			
		}
	}
	
	
	public Vector<String> listadoCursos(){
		String cadenaSQL="SELECT curso from cursos";
		Vector<String> listaCursos=new Vector<String>();
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while ( reg.next()){
				listaCursos.add(reg.getString(1));
			}			
			s.close();
			this.cerrar();
			return listaCursos;
		}
		catch ( SQLException e){
		//	System.out.println(e.getMessage());
			return null;
		}
	}
	
	public Vector<String> listadoAlumnos(){
		String cadenaSQL="SELECT nombre, dni from alumnos";
		Vector<String> listadoAlumnos=new Vector<String>();
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while ( reg.next()){
				listadoAlumnos.add("Nombre: "+reg.getString(1)+"\t"+"DNI: "+reg.getString(2));
			}			
			s.close();
			this.cerrar();
			return listadoAlumnos;
		}
		catch ( SQLException e){
		//	System.out.println(e.getMessage());
			return null;
		}
	}
	
	public Vector<Alumno> mostrarAlumno(String dni){
		Vector <Alumno> alum=new Vector<Alumno>();
		String cadena="SELECT * FROM alumnos WHERE dni LIKE '"+dni+"'";
		try{
			
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			while ( reg.next()){						
				 alum.add(new Alumno(reg.getString("dni"), reg.getString("nombre"), reg.getString("curso"), reg.getInt("matricula"), reg.getString("telefono")));
			}
			s.close();
			this.cerrar();
			return alum;
		}
		catch ( SQLException e){
	
			return null;
			
		}
	}
	
	public int borrarAlumno(String dni){
		String cadenaSQL="DELETE from alumnos WHERE dni LIKE '"+dni+"'";
		
		try{
			this.abrir();
			s=c.createStatement();
			int filas=s.executeUpdate(cadenaSQL);	
			s.close();
			this.cerrar();
			return filas;
			
			}
			catch ( SQLException e){
				this.cerrar();
				return -1;
			}
	}
	
	public Vector<String> listadoTutores(){
		String cadenaSQL="SELECT nombre, dni from tutores";
		Vector<String> listadoTutores=new Vector<String>();
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while ( reg.next()){
				listadoTutores.add("Nombre: "+reg.getString(1)+"\t"+"DNI: "+reg.getString(2));
			}			
			s.close();
			this.cerrar();
			return listadoTutores;
		}
		catch ( SQLException e){
		//	System.out.println(e.getMessage());
			return null;
		}
	}
	
	public Vector<Tutor> mostrarTutor(String dni){
		Vector <Tutor> tut=new Vector<Tutor>();
		String cadena="SELECT * FROM tutores WHERE dni LIKE '"+dni+"'";
		try{
			
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			while ( reg.next()){						
				tut.add(new Tutor(reg.getString("dni"), reg.getString("nombre"), reg.getString("curso")));
			}
			s.close();
			this.cerrar();
			return tut;
		}
		catch ( SQLException e){
	
			return null;
			
		}
	}
	
	public String cursoTutor(String dni){
		String cadenaSQL="SELECT curso from tutores WHERE dni LIKE '"+dni+"'";
		String cursoTutor=null;
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			if ( reg.next()){
				cursoTutor=reg.getString(1);
			}			
			s.close();
			this.cerrar();
			return cursoTutor;
		}
		catch ( SQLException e){
		//	System.out.println(e.getMessage());
			return null;
		}
	}
	
}
