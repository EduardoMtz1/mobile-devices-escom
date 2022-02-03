package com.example.myapplication;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    EditText jet;
    TextView jtv;
    String resultado;
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jet = (EditText)findViewById(R.id.xet);
        jtv = (TextView)findViewById(R.id.xtv);
        x = 17;
        jtv.setText("Numero elegido: " + x);
        resultado = (esPrimo(x))?"Es primo":"No es primo";
        String num = String.format("%d",x);
        resultado += (esPalindromo(num))?" Es palindromo":" No es palindromo";
        resultado += (esFibo(x))?" Es Fibonacci": " No es fibonacci";
        jet.setText(resultado);
    }
    public static boolean esPrimo(int numero){
        int contador = 2;
        boolean primo=true;
        while ((primo) && (contador!=numero)){
            if (numero % contador == 0)
                primo = false;
            contador++;
        }
        return primo;
    }
     public static boolean esPalindromo(String numero){
        int inicio = 0, fin = numero.length()-1;
        boolean palindromo = true;
        while((inicio<fin)&&(palindromo)){
            if(numero.charAt(inicio) == numero.charAt(fin)){
                inicio++;
                fin--;
            } else {
                palindromo = false;
            }
        }
        return palindromo;
     }
     public static boolean esFibo(int numero){
        int a = 0, b = 1, c = 0;
        boolean fibo = false;
        while (numero>c){
            c = a+b;
            a = b;
            b = c;
            if(numero == c){
                fibo = true;
            }
        }
        return fibo;
     }
}