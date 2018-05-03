package ninja.soumuito.projetofinal;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import ninja.soumuito.end.R;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private EditText loginPass;
    private EditText loginEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);



        mAuth = FirebaseAuth.getInstance();


        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();

            }
        });


        TextView lostPass = (TextView) findViewById(R.id.lostPassTx);
        lostPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lostPass = new Intent(LoginActivity.this, LostPassActivity.class);
                startActivity(lostPass);     }
        });


        TextView newAcount = (TextView) findViewById(R.id.newAcountTx);
        newAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newacc = new Intent(LoginActivity.this, SingUpActivity.class);
                startActivity(newacc);       }});
    }




    public void login() {

        EditText loginPass = findViewById(R.id.loginPass);
        EditText loginEmail = findViewById(R.id.loginEmail);


        mAuth.signInWithEmailAndPassword(loginEmail.getText().toString(), loginPass.getText().toString())
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Log.d("AUTH", "signInWithEmail:success");

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Log.w("AUTH", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });


    }





}



