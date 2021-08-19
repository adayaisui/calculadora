/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.util.ArrayList;

/**
 *
 * @author Patricia
 */
public class Pilas {

    public static <T> int cuentaElementos(PilaADT<T> pila){
        int cont=0;
        PilaADT<T> aux= new PilaA();
        while(!pila.isEmpty()){
            aux.push(pila.pop());
            cont++;
        }
        while(!aux.isEmpty()){
            pila.push(aux.pop());
        }
        return cont;
    }
    
    public static <T> PilaA invierteElementos(PilaADT<T> pila){
        PilaA<T> aux= new PilaA();
        
        while(!pila.isEmpty()){
            aux.push(pila.pop());
        }
        return aux;
        
    }
    
    public static <T> void eliminaRepetidos(PilaADT<T> pila){
        int n;
        n= cuentaElementos(pila);
        PilaA<T> aux= new PilaA();
        ArrayList<T> resp;
        resp= new ArrayList<T>();
        int i=0;
        
        while(!pila.isEmpty()){
            resp.add(pila.pop());
            //if(resp.indexOf(i).equals(pila.peek())){
                
            }
        }
       
        
        
    public static <T> boolean multiPop(PilaADT<T> pila, int n){
        boolean resp=false;
        if(n<=cuentaElementos(pila)){
            for(int i=0;i<n;i++){
                pila.pop();
            }
            resp=true;
        }
        else
            resp=false;
        return resp;
    }
    
    public static <T> boolean contencion(PilaA <T> pila1, PilaA <T> pila2){
        int i;
        int j;
        boolean encontre=false;
       PilaA <T> aux1= new PilaA();
       PilaA <T> aux2= new PilaA();
       T[] auxiliar1;
       auxiliar1=(T[])new Object[cuentaElementos(pila1)];
       
       for(i=0;i<cuentaElementos(pila1);i++){
           auxiliar1[i]=pila1.pop();
       }
       
       while(!encontre || !pila2.isEmpty()){
           for(j=0;i<auxiliar1.length;j++){
               if(pila2.peek().equals(auxiliar1[j]))
                   aux2.push(pila2.peek());
           
       }
           
       }
        return encontre;
    }
    
    
    
    
    
    public static void main(String[] args) {
        String cadena;
        cadena="[2+4]}";
        RevisorParentesis uno;
        boolean resp;
        
        uno=new RevisorParentesis(cadena);
        if(uno.revisa())
            System.out.println("Cierto");
        else
           System.out.println("Falso");
        
        
        
    }
    
    
    
}
