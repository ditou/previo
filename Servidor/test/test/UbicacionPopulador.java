package test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dao.UbicacionDAO;
import model.Ubicacion;

public class UbicacionPopulador {

	private static List<String> calle = Stream.of("A", "B", "C", "D", "E", "F", "G").collect(Collectors.toList());
	private static List<String> bloque = Stream.of("01", "02", "03", "04", "05").collect(Collectors.toList());
	private static List<String> estante = Stream.of("01", "02", "03", "04", "05", "06").collect(Collectors.toList());
	private static List<String> posicion = Stream.of("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21").collect(Collectors.toList());

	
	public static void main(String [] args) {
		for(String c : calle) {
			for(String b : bloque) {
				for(String e : estante) {
					for(String p : posicion) {
						String codigoUbicacion = c+b+e+p;
						saveUbicacion(codigoUbicacion);
					}
				}
			}
		}
		System.out.println("termine");
		return;
		
	}
	
	private static void saveUbicacion(String codigoUbicacion) {
		Ubicacion ubicacion = new Ubicacion();
		ubicacion.setCantidad(0);
		ubicacion.setLibre(true);
		ubicacion.setCodigoUbicacion(codigoUbicacion);
		UbicacionDAO.getInstancia().save(ubicacion);
	}
}
