/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

/**
 *
 * @author adaya
 * 
 */
public class RevisorParentesis {
    private String cadena;
    
    public RevisorParentesis(){
        
    }
    
    public RevisorParentesis(String cadena){
        this.cadena=cadena;
    }
    
    public boolean revisorParentesis(){
        boolean resp=true;
        PilaA pila= new PilaA();
        int cont=0;
        
        if(cadena!=null){
            int i=0;
            while(i<cadena.length() && resp){
                if(cadena.charAt(i)=='(')
                    pila.push(cadena.charAt(i));
                else if(cadena.charAt(i)==')')
                    try{
                        pila.pop();
                    } catch(Exception e){
                        resp=false;
                    }
                i++;
            }
        }
        
        return resp && pila.isEmpty();
        
    }
    
    public boolean revisa(){
        boolean resp=true;
        PilaA pilaPar= new PilaA();
        PilaA pilaCor= new PilaA();
        PilaA pilaLla= new PilaA();
        int cont=0;
        
        if(cadena!=null){
            int i=0;
            while(i<cadena.length() && resp){
                if(cadena.charAt(i)=='(' || cadena.charAt(i)=='[' || cadena.charAt(i)=='{')
                    switch(cadena.charAt(i)) {
                        case('('):
                            pilaPar.push(cadena.charAt(i));
                            break;
                        case('['):
                            pilaCor.push(cadena.charAt(i));
                            break;
                        case('{'):
                            pilaLla.push(cadena.charAt(i));
                            break;
                    }
                else if(cadena.charAt(i)==')'|| cadena.charAt(i)==']' || cadena.charAt(i)=='}')
                    switch(cadena.charAt(i)){
                        case(')'):
                            try{
                                pilaPar.pop();
                            } catch(Exception e){
                                resp=false;
                            }
                            break;
                        case(']'):
                            try{
                                pilaCor.pop();
                            } catch(Exception e){
                                resp=false;
                            }
                            break;
                        case('}'):
                            try{
                                pilaLla.pop();
                            } catch(Exception e){
                                resp=false;
                            }
                            break;   
                    }   
                i++;
            }
            
        }
        
        return resp && pilaPar.isEmpty() && pilaCor.isEmpty() && pilaLla.isEmpty();
        
        
    }
}
