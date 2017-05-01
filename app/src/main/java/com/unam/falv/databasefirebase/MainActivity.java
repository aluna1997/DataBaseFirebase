package com.unam.falv.databasefirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView mensajeTextView;
    private EditText mensajeEditText;

    //Obtenemos una instancia de la referencia a la raíz del árbol de Firebase
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    //Obtenemos la referencia a una rama, usamos el método child para referirnos a una rama hija.
    DatabaseReference mensajeRef = ref.child("mensaje");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensajeTextView = (TextView) findViewById(R.id.mensajeTextView);
        mensajeEditText = (EditText) findViewById(R.id.mensajeEditText);

    }

    @Override
    protected void onStart(){
        super.onStart();

        //Para saber cuando se modifica un valor en nuestra base de datos le asignamos un
        //oyente
        mensajeRef.addValueEventListener(new ValueEventListener() {
            @Override
            //El método onDataChange se ejecuta cuando alguno de nuestros valores cambia
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                mensajeTextView.setText(value);

            }

            @Override
            //El método onCancelled se ejecuta cuando no podemos obtener actualizaciones por
            //algún problema
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }











    public void modificar (View view){
        String mensaje = mensajeEditText.getText().toString();
        //Para escribir en la base de datos usamos el método setValue el cual soporta
        //String,Long,Double,Boolean,Map<String,Object>,List<Object>,Un objeto personalizado
        mensajeRef.setValue(mensaje);

        mensajeEditText.setText("");

    }
}

