package be.ehb.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, Register;
    private EditText editTextFullName, editTextAge,editTextEmail,editTextPassword;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        banner =(TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        Register =(Button) findViewById(R.id.Register);
        Register.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.naam);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextAge =(EditText) findViewById(R.id.Leeftijd);
        editTextPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.Register:
                Register();
                break;

        }
    }
    private void Register(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String naam = editTextFullName.getText().toString().trim();
        String Leeftijd = editTextAge.getText().toString().trim();

        if (naam.isEmpty()){
            editTextFullName.setError("Full name is required!");
            editTextFullName.requestFocus();
            return;
        }
        if(Leeftijd.isEmpty()){
            editTextAge.setError("Leeftijd is nodig!");
            editTextAge.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextAge.setError("email is nodig!");
            editTextAge.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextAge.setError("not valid email adres !");
            editTextAge.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextAge.setError("password is nodig!");
            editTextAge.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextAge.setError("password mag niet groter zijn dan 6");
            editTextAge.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(naam ,Leeftijd, email);

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()){
                                                Toast.makeText(Register.this, "user is geregistreren ", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.VISIBLE);
                                            }else {
                                                Toast.makeText(Register.this, "failt  registreren probeer opnieuw ", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);

                                            }
                                        }
                                    });
                        }else {
                            Toast.makeText(Register.this, "failt  registreren ", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });



    }
}