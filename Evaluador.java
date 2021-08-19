/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author santi
 */
public class Evaluador {
     public Evaluador() {
        
    }
    
    /**
     * Toma la cadena en notacion infija recibida, la convierte a posfija y despues la evalua para dar el resultado
     * @param infija cadena en notacion infija
     * @see convertir, evaluarPosfija
     * @return 
     */ 
     
     public static double evalua (String infija) {

        ArrayList<String> posfija = convierteInfija(infija);
//        System.out.println("La expresión posfija es: " + posfija);
        return evaluaEnPosfija(posfija);
    }
     
    /**
     * 
     * @param infija
     * @return 
     */ 
    private static  ArrayList<String> convierteInfija (String infija) { //----------1er algoritmo
         ArrayList<String> posfija = new ArrayList<String>(); //ahi guardo la cadena pero ya en posfija
      if(revisorParentesis(infija)){
        
        String letra = "";

        PilaA  pila = new PilaA();
        ArrayList<String> list = new ArrayList();

        for (int i = 0; i < infija.length(); i++) {
            String SoloLetras = "" + infija.charAt(i);//string al que le voy pegando los caracteres de infija
            if(SoloLetras.equals("0")||SoloLetras.equals("1")||SoloLetras.equals("2")||SoloLetras.equals("3")||SoloLetras.equals("4")
                    ||SoloLetras.equals("5")||SoloLetras.equals("6")||SoloLetras.equals("7")
                      ||SoloLetras.equals("8")||SoloLetras.equals("9")||SoloLetras.equals(".")||SoloLetras.equals("_")){
                     //si es un numero del 0-9, entonces lo guarda en el string letraUnic

                letra += SoloLetras;

            } else if (esUnOperador(SoloLetras)) { //si no fue un numero pero es un operador, lo agrega al arraylist List
                list.add(letra);
                list.add(SoloLetras);
                letra = "";
            }
        }

        list.add(letra);   //guardo notacion infija en un arraylist

//        System.out.println(list.toString());
        for (int i = 0; i < list.size(); i++) {  //recorre todo mi arraylist de infija
            letra = list.get(i);

            if (esUnOperador(letra)) {  //empieza a usar pila para guardar solo los operadores, no operandos

                if (pila.isEmpty()) {

                    pila.push(letra);
                } else {

                    int ExpPriori = ExpresionPrioridad(letra);
                    int PilaPriori = PilaPrioridad((String) pila.peek());

                    if (ExpPriori > PilaPriori) { //cambiar a mayor o igual luego

                        pila.push(letra); //si lo que esta adentro es mayor o igual a lo que vas a meter, saca todo lo de la pila

                    } else {
                        if (letra.equals(")")) {

                            while (!pila.peek().equals("(")) {
                                posfija.add((String) pila.pop());
                            }
                            pila.pop();

                        } else {
                            posfija.add((String) pila.pop());
                            pila.push(letra);
                        }
                    }
                }
            } else {
                posfija.add(letra);  //en posfija va agregando todos los numeros
                
            }
        }

        while (!pila.isEmpty()) {
            posfija.add((String) pila.pop());
        }
      }
         
        return posfija;
    }

    private static double evaluaEnPosfija(ArrayList<String> posfija) { //--2do algoritmo

        PilaA  pila = new PilaA();

        for (int i = 0; i < posfija.size(); i++) {

            if (!posfija.get(i).equals("")) { //mientras no sea vacio lo que mandes 
                String letra = "" + posfija.get(i); //stringBuilder en lugar de String
                if (!esUnOperador(letra)) { //meto numeros a la pila mientras no sea operaador
                    if(letra.charAt(0)=='_'){
                        letra="-"+letra.charAt(1);
                    }
                    double numero = new Double(letra + "");  
                    pila.push(numero); //agregar if de _   -----aqui va lo del punto y lo del _---???

                } else {

                    double numeroDos = (double) pila.pop();
                     /* if(pila.peek().equals("_")){
                        pila.pop();
                        num2=num2*-1;
                    }*/

                    double numeroUno = (double) pila.pop();
                    /* if(pila.peek().equals("_")){
                        pila.pop();
                        num1=num1*-1;
                    }*/

                    double numeroTres = operacion(letra, numeroUno, numeroDos);

                    pila.push(numeroTres);
                }
            }

        }

        return (double) pila.peek();
    }

    private static boolean esUnOperador(String letra) {

        if (letra.equals("*") || letra.equals("/") || letra.equals("+")
                || letra.equals("-") || letra.equals("(") || letra.equals(")")) {
            return true;
        } else {
            return false;
        }
    }

    private static int ExpresionPrioridad(String operador) {

        if (operador.equals("*") || operador.equals("/")) {
            return 2;
        }
        if (operador.equals("+") || operador.equals("-")) {
            return 1;
        }
        if (operador.equals("(")) {
            return 5;
        }
        return 0;
    }

    private static int PilaPrioridad(String operador) {

        if (operador.equals("*") || operador.equals("/")) {
            return 2;
        }
        if (operador.equals("+") || operador.equals("-")) {
            return 1;
        }
        if (operador.equals("(")) {
            return 0;
        }
        return 0;
    }

    private static double operacion(String letra, double n1, double n2) {

        if (letra.equals("*")) {
            return n1 * n2;
        }
        if (letra.equals("/")) {
            return n1 / n2;
        }
        if (letra.equals("+")) {
            return n1 + n2;
        }
        if (letra.equals("-")) {
            return n1 - n2;
        }
        return 0;

    }
     
     public static boolean revisorParentesis(String cadena){
        boolean resp=true;
        Stack pila= new Stack();
        int cont=0;
       
        try {
            int i=0;
            while(  i<cadena.length() && resp){
                if(cadena.charAt(1)== '('){
                    pila.push(cadena.charAt(i));
                }else{
                    if(cadena.charAt(i)==')'){
                        try{
                        pila.pop();
                        }catch(Exception e){
                            System.out.println("Erronea");
                            resp=false;
                        }
                    }
                }
               
                i++;
            }
        
        }catch(Exception e){
            System.out.println("Cadena Vacia");
        }
        
        if(resp!=false && pila.empty()){
            resp=false;
        }
          
        return resp;
    }

}