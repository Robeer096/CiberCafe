package colegio;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;

import bbdd.*;
import modelos.Alumno;
import modelos.Curso;
import modelos.Tutor;


public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Scanner sLeer=new Scanner(System.in);
		int opc=0, filas;
		Vector <String> cursos;
		Vector<String> alumnos;
		Vector<String> tutores;
		
		BD_Colegio bd=new BD_Colegio("mysql-properties.xml");
		do	
		{
			System.out.println("\n\nGESTIÓN COLEGIO");
			System.out.println("***************");
			System.out.println("1.Nuevo Alumno\n"
					+ "2.Nuevo Curso\n"
					+ "3.Borrar Alumno\n"
					+ "4.Listado alumnos por curso\n"
					+ "5.Listado de cursos\n"
					+ "6.Consultar alumno\n"
					+ "7.Consultar tutor de un curso\n"
					+ "8.Listado alumnos por tutor\n");
			System.out.print("\tTeclea opción: ");
			try{
				opc=sLeer.nextInt();
			}
			catch(NumberFormatException e ){
				System.out.println("Opcion incorrecta");
				opc=0;
			}
			catch(InputMismatchException e ){
				System.out.println("Debes introducir número 1-5");
				opc=0;
			}
			
			sLeer.nextLine();
			switch (opc){
			case 1:
				System.out.println("\n\nALTA ALUMNO");
				System.out.print("Introduce nombre completo\t");
				String nombre=sLeer.nextLine();				
				System.out.print("Introduce telefono\t");
				String telefono=sLeer.nextLine();
				System.out.print("Introduce DNI\t");
				String dni=sLeer.nextLine();
				System.out.print("Introduce número de matrícula\t");
				int matricula=sLeer.nextInt();	
				cursos=bd.listadoCursos();
				if (cursos==null){
						System.out.println("En este momento no podemos realizar la operación");
						break;
				}
				System.out.println("Lista de cursos");
				for (int i=0;i<cursos.size();i++)
					System.out.println((i+1)+ ".- "+cursos.get(i));				
				System.out.print("Teclea el curso\t");
				String curso=sLeer.next();
				Alumno al=new Alumno(dni,nombre,curso,matricula,telefono);			
				filas=bd.añadir_Alumno(al);				
				switch (filas){
				case 1:
					System.out.println("\nAlumno añadido");
					break;
				case 0:
					System.out.println("\nNo añadido");
					break;
				case -1:
					System.out.println("\nProblemas técnicos");
					break;
					
				}
			break;
			
			case 2:
				System.out.println("Curso?");
				String cur=sLeer.nextLine();
				System.out.println("Descripcion?");
				String desc=sLeer.nextLine();
				System.out.println("Aula?");
				String aula=sLeer.nextLine();
				
				Curso cursoNuevo=new Curso(desc, cur, aula);
				filas=bd.añadir_Curso(cursoNuevo);
				switch (filas){
				case 1:
					System.out.println("\nCurso añadido");
					break;
				case 0:
					System.out.println("\nNo añadido");
					break;
				case -1:
					System.out.println("\nProblemas técnicos");
					break;
				}
				break;
			
			case 3:
				//Mostrar alumnos
				alumnos=bd.listadoAlumnos();
				if (alumnos==null){
					System.out.println("En este momento no podemos realizar la operación");
					break;
				}
				for(int i=0; i<alumnos.size(); i++) {
					System.out.println(alumnos.get(i));
				}
				
				System.out.println("Introduce dni del alumno?");
				String dniBorrar=sLeer.nextLine();
				filas=bd.borrarAlumno(dniBorrar);
				switch (filas){
				case 0:
					System.out.println("No es alumno");
					break;
				case 1: 
					System.out.println("Alumno eliminado");
					break;
				default:
					System.out.println("En este momento no podemos eliminar. Inténtalo más tarde");
				}
				
				break;
				
			case 4:											
				cursos=bd.listadoCursos();
				if (cursos==null){
						System.out.println("En este momento no podemos realizar la operación");
						break;
				}
				System.out.println("Lista de cursos");
				for (int i=0;i<cursos.size();i++)
					System.out.println((i+1)+ ".- "+cursos.get(i));				
				System.out.print("Teclea el curso\t");
				curso=sLeer.next();
				Vector <Alumno> listado=bd.listadoAlumnosCurso(curso);
				System.out.println("\n\nLISTADO ALUMNOS "+ curso.toUpperCase()+"\n");
				for (int i=0;i<listado.size();i++)									
					System.out.println(listado.get(i).toString());
				break;
			
			case 5:
				cursos=bd.listadoCursos();
				if (cursos==null){
					System.out.println("En este momento no podemos realizar la operación");
					break;
				}
				for (int i=0; i<cursos.size(); i++)
					System.out.println((i+1)+ ".- "+cursos.get(i));	
				break;
				
			case 6:
				alumnos=bd.listadoAlumnos();
				if (alumnos==null){
					System.out.println("En este momento no podemos realizar la operación");
					break;
				}
				for(int i=0; i<alumnos.size(); i++) {
					System.out.println(alumnos.get(i));
				}
				System.out.println("Dni del alumno?");
				String dniConsultar=sLeer.nextLine();
				Vector<Alumno> alumno=bd.mostrarAlumno(dniConsultar);
				for(int i=0; i<alumno.size(); i++) {
					System.out.println(alumno.get(i).toString());
				}
				break;
			
			case 7:
				tutores=bd.listadoTutores();
				if (tutores==null){
					System.out.println("En este momento no podemos realizar la operación");
					break;
				}
				for(int i=0; i<tutores.size(); i++) {
					System.out.println(tutores.get(i));
				}
				System.out.println("Dni del tutor?");
				String tutorConsultar=sLeer.nextLine();
				Vector<Tutor> tutor=bd.mostrarTutor(tutorConsultar);
				for(int i=0; i<tutor.size(); i++) {
					System.out.println(tutor.get(i).toString());
				}
				break;
				
			case 8:
				tutores=bd.listadoTutores();
				if (tutores==null){
					System.out.println("En este momento no podemos realizar la operación");
					break;
				}
				for(int i=0; i<tutores.size(); i++) {
					System.out.println(tutores.get(i));
				}
				System.out.println("Dni del tutor?");
				String dniTutor=sLeer.nextLine();
				String curTutor=bd.cursoTutor(dniTutor);
				if(curTutor == null) {
					System.out.println("En este momento no podemos realizar la operación");
					break;
				}
				Vector<Alumno> alu=bd.listadoAlumnosCurso(curTutor);
				for(int i=0; i<alu.size(); i++) {
					System.out.println(alu.get(i).toString());
				}
				break;
			}
		}
		while (opc!=8);
			
	
		}
	
	
	

}
