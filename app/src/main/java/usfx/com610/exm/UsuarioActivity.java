package usfx.com610.exm;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import usfx.com610.exm.adapters.AdapterUser;
import usfx.com610.exm.modelos.User;

public class UsuarioActivity extends AppCompatActivity {
    FirebaseFirestore db;
    private ListView listView;
    private ArrayList<User> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        db = FirebaseFirestore.getInstance();
        listView = findViewById(R.id.listView);
        loadData();
    }

    private void loadData() {
        list = new ArrayList<>();

        Toast.makeText(this, "Cargando", Toast.LENGTH_SHORT).show();

        db.collection("users").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                System.out.println(document.getId());
                                user.setId(document.getId());
                                list.add(user);
                            }
                            showData();
                        } else {
                            Toast.makeText(UsuarioActivity.this, "ERROR AL OBTENER LOS DATOS", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
   /* private void realTime(){
        db.collection("users")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            // Log.w(TAG, "listen:error", e);
                            return;
                        }

                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            User user = dc.getDocument().toObject(User.class);
                            System.out.println(dc.getDocument().getId());
                            switch (dc.getType()) {
                                case ADDED:
                                    Toast.makeText(UsuarioActivity.this, "AGREGo " +user.getName(), Toast.LENGTH_LONG).show();
                                    //Log.d(TAG, "New city: " + dc.getDocument().getData());
                                    break;
                                case MODIFIED:
                                    Toast.makeText(UsuarioActivity.this, "CAMBIOS A " +user.getName(), Toast.LENGTH_LONG).show();
                                    // Log.d(TAG, "Modified city: " + dc.getDocument().getData());
                                    break;
                                case REMOVED:
                                    Toast.makeText(UsuarioActivity.this, "ELIMINO " +user.getName(), Toast.LENGTH_LONG).show();
                                    // Log.d(TAG, "Removed city: " + dc.getDocument().getData());
                                    break;
                            }
                        }

                    }
                });
    }*/
    private void showData() {
        AdapterUser adapterUser = new AdapterUser(this, list);
        listView.setAdapter(adapterUser);
        Toast.makeText(this, "Cargado", Toast.LENGTH_LONG).show();
    }
}
