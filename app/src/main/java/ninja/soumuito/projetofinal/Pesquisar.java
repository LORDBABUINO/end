package ninja.soumuito.projetofinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Pesquisar extends AppCompatActivity {

    private EditText editPalavra;
    private ListView listVPesquisa;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private List<Perfil> listPerfil = new ArrayList<Perfil>();
    private ArrayAdapter<Perfil> arrayAdapterPerfil;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        inicializarComponentes();
        inicializarFirebase();
        eventoEdit();

    }

    private void eventoEdit() {
        editPalavra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String palavra = editPalavra.getText().toString().trim();
                pesquisarPalavra(palavra);

            }
        });
    }

    private void pesquisarPalavra(String palavra) {
        Query query;
        if (palavra.equals("")) {
            query = databaseReference.child("iesb/alunos/").orderByChild("nome");
        }else{
            query = databaseReference.child("iesb/alunos/").orderByChild("nome").startAt(palavra)
                    .endAt(palavra+"\uf8ff");
        } //Aqui, se estiver certo é para aparecer tbm palavras parecidas
        // E aparecer também na ordem pelos nomes.

        listPerfil.clear();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                    Perfil p = objSnapshot.getValue(Perfil.class);
                    listPerfil.add(p);

                }

                arrayAdapterPerfil = new ArrayAdapter<Perfil>(Pesquisar.this,
                        android.R.layout.simple_list_item_1,listPerfil);
                listVPesquisa.setAdapter(arrayAdapterPerfil);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(Pesquisar.this);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void inicializarComponentes() {
        editPalavra = (EditText) findViewById(R.id.editPalavra);
        listVPesquisa = (ListView) findViewById(R.id.listVPesquisa);
    }


    @Override
    protected void onResume() {
        super.onResume();
        pesquisarPalavra("");
    }


}
