/*
	Utilice esta clase para guardar la informacion de su
	AFD. NO DEBE CAMBIAR LOS NOMBRES DE LA CLASE NI DE LOS 
	METODOS que ya existen, sin embargo, usted es libre de 
	agregar los campos y metodos que desee.
*/
import java.io.*;
import java.util.*;
import java.util.Scanner.*;

public class AFD{
	private ArrayList<String> instancias = new ArrayList<String>();
	private int estados = 0;
	private String estadosFinales = "";
	private String alfabeto = "";
	/*
		Implemente el constructor de la clase AFD
		que recibe como argumento un string que 
		representa el path del archivo que contiene
		la informacion del afd (i.e. "Documentos/archivo.afd").
		Puede utilizar la estructura de datos que desee
	*/
	public AFD(String path) throws FileNotFoundException{
		int count = 1;
		String aux;

		//BufferedReader reader = new BufferedReader(System.in);
        //String line = reader.readLine();

		File archivo = new File(path);
        Scanner scanner = new Scanner(archivo);
        while (scanner.hasNextLine()) {
			aux = scanner.nextLine();
			if (count == 1){
				estados = Integer.parseInt(aux);
			}
			if (count == 2){
				estadosFinales = aux.replace(",","");	
			}
			if (count ==  3){
				alfabeto = aux.replace(",","");	
			
			}
			if (count > 3) {
				instancias.add(aux);
			}
			 count += 1;
	    }

		//System.out.println(estados);
		//System.out.println(estadosFinales);
		//System.out.println(alfabeto);
		//System.out.println(instancias);
	}

	/*
		Implemente el metodo transition, que recibe de argumento
		un entero que representa el estado actual del AFD y un
		caracter que representa el simbolo a consumir, y devuelve 
		un entero que representa el siguiente estado
	*/
	public int getTransition(int currentState, char symbol){
		int aux = 0; 
		for (int i = 0; i < this.alfabeto.length(); i++) {
    		char c = alfabeto.charAt(i);
    		if (c == symbol) {
        		aux = i;
        	break; // Si solo quieres encontrar la primera ocurrencia del caracter, puedes salir del bucle con la sentencia break
    	}
		}

		String s = this.instancias.get(aux);
		String m = s.replace(",","");
		char d = m.charAt(currentState);
		//System.out.println(d + " " + symbol);
		int newState = Character.getNumericValue(d);
		//System.out.println(newState);
	
		return newState;
	}

	/*
		Implemente el metodo evaluate, que recibe como argumento
		un String que representa la cuerda a evaluar, y devuelve
		un boolean dependiendo de si la cuerda es aceptada o no 
		por el afd
	*/
	public boolean evaluate(String string){
		int state = 1;
		int ultimo = string.length() - 1;
		boolean s = false;
		for(int j = 0 ; j < string.length(); j++){
			char c = string.charAt(j);
			state = getTransition(state, c);
			//System.out.println(j);
			//System.out.println(ultimo);
			if (j == ultimo){
				if (isFinal(state)){
				//System.out.println("a");
				return true;
				}
			}
		}
		//System.out.println();
		return false;
	}
	/*
		Implemente el metodo evaluate_many, que recibe como argumento
		un arreglo de Strings que representa las cuerda a evaluar, y devuelve
		un arreglo booleans dependiendo de si cada cuerda es aceptada o no 
		por el afd
	*/
	public boolean[] evaluateMany(String[] strings){
		String cuerdas;
		int aux = strings.length;
		boolean[] comprobaciones = new boolean[aux];
		boolean s;
		for (int i = 0; i < aux; i++){
			cuerdas = strings[i];
			comprobaciones[i] = evaluate(cuerdas);
				//System.out.println(c);
			}
				//System.out.println();
				
		return comprobaciones;
		}


	/*
		Implemente el metodo isFinal, que devuelve true si el estado enviado
		es un estado final, y false si no lo es
	*/
	public boolean isFinal(int currentState){
		for (int i = 0; i < this.estadosFinales.length(); i++) {
    		char c = estadosFinales.charAt(i);
			int s = Character.getNumericValue(c);
			//System.out.println(c);
			//System.out.println(s);
    		if (s == currentState) {
				System.out.println("a");
        		return true;
				}
			}
		return false;
		}
}