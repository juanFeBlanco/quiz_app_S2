package co.domi.quiz_app;

public class Pregunta {
    //attributes
    private int opA;
    private int opB;
    private char operador;
    private char[] operadores = {'+','-','x','/'};

    public Pregunta(){
        this.opA = (int)(Math.random()*21);
        this.opB = (int)((Math.random()* 21)+1);
        operador = operadores[(int)(Math.random()*4)];
    }

    public String getPregunta(){
        return opA + " " + operador + " " + opB;
    }

    public int getRespuesta(){
        int respuesta = 0;
        switch(operador){
            case '+':
                respuesta = this.opA+this.opB;
                break;
            case '-':
                respuesta = this.opA-this.opB;
                break;
            case 'x':
                respuesta = this.opA*this.opB;
                break;
            case '/':
                respuesta = this.opA/this.opB;
                break;
        }
        return respuesta;
    }
}
