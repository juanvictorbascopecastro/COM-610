package usfx.com610.exm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.admin.DevicePolicyManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.login.DeviceLoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Date;

import usfx.com610.exm.adapters.AdaptadorArticulos;
import usfx.com610.exm.modelos.Dispositivos;
import usfx.com610.exm.modelos.mensaje;
import usfx.com610.exm.modelos.mensajelist;

public class DataBaseActivity extends AppCompatActivity {
    private AdaptadorArticulos adaptador;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    ImageButton btnSend;
    EditText editText;
    private String OBJECT_MENSAJES = "mensajes";
    private mensajelist msj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        btnSend = findViewById(R.id.btnSend);
        editText = findViewById(R.id.ediEdit);
        // Write a message to the database
        mDatabase = FirebaseDatabase.getInstance().getReference();
       realTime();
       btnSend.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               saveData();
           }
       });
       registrarDispositivo();
    }
    private ArrayList<mensajelist> listMensaje;
    private void realTime() {
        mDatabase.child(OBJECT_MENSAJES).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listMensaje = new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    listMensaje.add(new mensajelist(
                            postSnapshot.child("mensaje").getValue().toString(),
                            postSnapshot.child("nombre").getValue().toString(),
                            postSnapshot.child("id").getValue().toString(),
                            postSnapshot.getKey()
                    ));
                }
                mostrarDatos();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }
    private void saveData() {
        if(editText.getText().toString().trim().equals("")) {
            Toast.makeText(this, "El texto es requerido!", Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(msj == null) {
            mDatabase.child(OBJECT_MENSAJES).child(new Date().toString())
                .setValue(new mensaje(editText.getText().toString().trim(), user.getDisplayName(), user.getUid()))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        editText.setText("");
                    }
                });
            DeviceManager.hacerNotificacion(editText.getText().toString().trim(), this);
        }else {
            mDatabase.child(OBJECT_MENSAJES).child(msj.getKey())
                .setValue(new mensaje(editText.getText().toString().trim(), user.getDisplayName(), user.getUid()))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        editText.setText("");
                        msj = null;
                    }
                });
            DeviceManager.hacerNotificacion(editText.getText().toString().trim(), this);
        }
    }
    private void mostrarDatos(){
        adaptador = new AdaptadorArticulos(getApplicationContext(), listMensaje);
        adaptador.setOnItemClickListener(new AdaptadorArticulos.OnItemClickListener() {
            @Override
            public void onItemClick(mensajelist item) {
                msj = item;
                editText.setText(msj.getMensaje());
            }
        });
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
    private static final String TAG = "SERVICIOS DE FIREBASE";
    // Guardamos el dispositivo
    private void registrarDispositivo() {
        FirebaseMessaging.getInstance().getToken()
            .addOnCompleteListener(new OnCompleteListener<String>() {
                @Override
                public void onComplete(@NonNull Task<String> task) {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    // Get new FCM registration token
                    String token = task.getResult();
                    String tokenGuardado = getSharedPreferences(Contants.SP_FILE, 0).getString(Contants.DEVICE_ID, null);
                    if(token != null) {
                        if(tokenGuardado == null || !token.equals(tokenGuardado)) {
                            // nos suscribimos al topic
                            FirebaseMessaging.getInstance().subscribeToTopic("com-610");
                            // guardamos el id del dispositivo
                            getSharedPreferences(Contants.SP_FILE, 0).edit().putString(Contants.DEVICE_ID, token).commit();
                            DeviceManager.guardarIdDispositivo(token, DataBaseActivity.this);
                        }
                    }
                }
            });
    }
    private ArrayList<Dispositivos> listDispositivos;

    private void obtenerDispositivos() {
        DeviceManager.obtenerDispositivos().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                listDispositivos = new ArrayList<>();
                for (DocumentSnapshot documentSnapshot: task.getResult()) {
                    listDispositivos.add(new Dispositivos(
                            documentSnapshot.get("nombre").toString(),
                            documentSnapshot.get("token").toString(),
                            documentSnapshot.get("email").toString(),
                            documentSnapshot.getId()
                    ));
                }
            }
        });
    }
}
