/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

/**
 *
 * @author Patricia
 */
public class PilaA<T> implements PilaADT<T> {
private T[] elementos;
private int tope;
private final int MAX=20;

public PilaA(){
    elementos = (T[]) new Object[MAX];
    tope=-1; //indica pila vacía;
}

public PilaA(int max){
    elementos= (T[]) new Object[max];
    tope=-1;
}

public boolean isEmpty(){
    return tope==-1;
}

public T peek(){
    if(isEmpty()){
        throw new ExcepcionColeccionVacia("Pila vacia. No se puede consultar");
    }
    else
        return elementos[tope];
}

public T pop(){
    if(isEmpty())
        throw new ExcepcionColeccionVacia("Pila vacia. No se puede consultar");
    else{
        T dato = elementos[tope];
        elementos[tope]=null;
        tope--;
        return dato;
    }
}

public void push(T dato){
    if(tope==elementos.length-1)
        aumentaCapacidad();
    tope++;
    elementos[tope]=dato;
}

private void aumentaCapacidad(){
    T[] nuevo = (T[]) new Object[elementos.length*2];

    for(int i=0; i<=tope; i++)
        nuevo[i]=elementos[i];
    elementos=nuevo;

}

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

    public int getTope() {
        return tope;
    }


    public static void main(String[] args) {
        // TODO code application logic here
        
        PilaA pila;
        pila= new PilaA(4);
        
        pila.push(1);
        pila.push(2);
        System.out.println(pila.pop());
        
    }
    
    
}

