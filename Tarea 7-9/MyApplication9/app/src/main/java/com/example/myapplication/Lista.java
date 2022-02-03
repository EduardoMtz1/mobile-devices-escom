package com.example.myapplication;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.*;
public class Lista extends Activity {
    private ListView lv;

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.lista);
        ArrayList<ListaEntrada> al = new ArrayList<ListaEntrada>();
        al.add(new ListaEntrada(R.drawable.mercurio,
                "MERCURIO", "Mercurio es el planeta del sistema solar más próximo al Sol y el más pequeño. Forma parte de los denominados planetas interiores o terrestres y carece de satélites naturales al igual que Venus."));
        al.add(new ListaEntrada(R.drawable.venus,
                "VENUS", "Venus es el segundo planeta del sistema solar en orden de distancia desde el Sol, el sexto en cuanto a tamaño, ordenados de mayor a menor. Al igual que Mercurio, carece de satélites naturales. Recibe su nombre en honor a Venus, la diosa romana del amor Afrodita."));
        al.add(new ListaEntrada(R.drawable.tierra,
                "TIERRA", "La Tierra es un planeta del sistema solar que gira alrededor de su estrella, el Sol, en la tercera órbita más interna. Es el más denso y el quinto mayor de los ocho planetas del sistema solar."));
        al.add(new ListaEntrada(R.drawable.marte,
                "MARTE", "Marte es el cuarto planeta en orden de distancia al Sol y el segundo más pequeño del sistema solar, después de Mercurio. Recibió su nombre en homenaje al dios de la guerra de la mitología romana, Ares."));
        al.add(new ListaEntrada(R.drawable.jupiter,
                "JUPITER", "Júpiter es el quinto planeta del sistema solar. Forma parte de los denominados planetas exteriores o gaseosos. Recibe su nombre del dios romano Júpiter (Zeus en la mitología griega). Se trata del planeta que ofrece un mayor brillo a lo largo del año dependiendo de su fase."));
        al.add(new ListaEntrada(R.drawable.saturno,
                "SATURNO", "Saturno es el sexto planeta del sistema solar contando desde el Sol, el segundo en tamaño y masa después de Júpiter y el único con un sistema de anillos visible desde la Tierra. Su nombre proviene del dios romano Saturno."));
        al.add(new ListaEntrada(R.drawable.urano,
                "URANO", "Urano es el séptimo planeta del sistema solar, el tercero de mayor tamaño, y el cuarto más masivo. Se llama así en honor de la divinidad griega del cielo Urano, el padre de Crono (Saturno) y el abuelo de Zeus (Júpiter)."));
        al.add(new ListaEntrada(R.drawable.neptuno,
                "NEPTUNO", "Neptuno es el octavo planeta en distancia respecto al Sol y el más lejano del sistema solar. Forma parte de los denominados planetas exteriores, y dentro de estos, es uno de los gigantes helados, y es el primero que fue descubierto gracias a predicciones matemáticas."));
        lv = (ListView) findViewById(R.id.ListView_listado);
        lv.setAdapter(new ListaAdapter(this, R.layout.activity_segunda, al) {
            public void onEntrada(Object o, View v) {
                if (o != null) {
                    TextView texto_superior_entrada = (TextView) v.findViewById(R.id.textView_superior);
                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((ListaEntrada) o).get_textoEncima());
                    TextView texto_inferior_entrada = (TextView) v.findViewById(R.id.textView_inferior);
                    if (texto_inferior_entrada != null)
                        texto_inferior_entrada.setText(((ListaEntrada) o).get_textoDebajo());
                    ImageView imagen_entrada = (ImageView) v.findViewById(R.id.imageView_imagen);
                    if (imagen_entrada != null)
                        imagen_entrada.setImageResource(((ListaEntrada) o).get_idImagen());
                }
            }
        });
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                ListaEntrada le = (ListaEntrada) av.getItemAtPosition(i);
                CharSequence cs = "Seleccionado: " + le.get_textoDebajo();
                Toast t = Toast.makeText(Lista.this, cs, Toast.LENGTH_SHORT);
                t.show();
            }
        });
    }
}


