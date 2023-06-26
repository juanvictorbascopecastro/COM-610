package usfx.com610.exm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn1, btnDatoUsuario, obtenerDatos;

    //  ./gradlew signinReport
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        btn1 = findViewById(R.id.button);
        btnDatoUsuario = findViewById(R.id.btnDatoUsuario);
        obtenerDatos = findViewById(R.id.obtenerDatos);

        btn1.setOnClickListener(this);
        btnDatoUsuario.setOnClickListener(this);
        obtenerDatos.setOnClickListener(this);
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        if(usuario != null) {
            textView.setText("Nombre: " + usuario.getDisplayName() + " \nEmail:" + usuario.getEmail() + "\n"+ usuario.getProviderId());
            // Toast.makeText(MainActivity.this, "Inicia session" + usuario.getDisplayName() + " " + usuario.getEmail() + " "+ usuario.getProviderId(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                AuthUI.getInstance().signOut(MainActivity.this).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Cession cerrada...", Toast.LENGTH_LONG).show();
                        MainActivity.this.finish();
                    }
                });
                break;
            case R.id.btnDatoUsuario:
                lanzarDatosDeUsuario();
                break;
            case R.id.obtenerDatos:
                Intent intent = new Intent(this, DataBaseActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void lanzarDatosDeUsuario(){
        Intent intent = new Intent( this, UsuarioActivity.class);
        startActivity(intent);
    }


}