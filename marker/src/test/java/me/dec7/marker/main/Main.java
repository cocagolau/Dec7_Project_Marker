package me.dec7.marker.main;

import java.lang.reflect.Field;

public class Main {
	
	public Gen<String> gen;

	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		
		Main m = new Main();
		Field field = m.getClass().getField("gen");
		System.out.println(field.getGenericType());
		
	}
	
	class Gen<T> { }

}
