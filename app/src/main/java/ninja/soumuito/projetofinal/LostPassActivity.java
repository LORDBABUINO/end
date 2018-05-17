package ninja.soumuito.projetofinal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LostPassActivity extends AppCompatActivity {

    private EditText inputEmail;
    private Button reset_buton;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_pass);

        inputEmail = (EditText) findViewById(R.id.email);
        reset_buton = (Button) findViewById(R.id.reset_btn);



        auth = FirebaseAuth.getInstance();

        reset_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Digite seu e-mail de ID cadastrado", Toast.LENGTH_SHORT).show();
                    return;
                }


                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LostPassActivity.this, "Nós lhe enviamos instruções para redefinir sua senha!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LostPassActivity.this, "Falha ao enviar e-mail redefinido!", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }
        });
    }

}