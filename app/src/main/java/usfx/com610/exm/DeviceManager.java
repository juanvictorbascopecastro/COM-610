package usfx.com610.exm;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DeviceManager {
    private static String COLLECTION = "employees";
    private static FirebaseFirestore db;
    private static FirebaseUser user;

    public static void guardarIdDispositivo(String token, Context context) {
        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        HashMap<String, Object> object = new HashMap<>();
        object.put("token", token);
        object.put("nombre", user.getDisplayName());
        object.put("email", user.getEmail());

        db.collection(COLLECTION).document(user.getUid()).set(object).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(context, "Dispositivo registrado correctamente" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void obtenerDispositivo() {
        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection(COLLECTION).document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    String nombre = task.getResult().get("nombre").toString();
                    String token = task.getResult().get("token").toString();
                    String email = task.getResult().get("email").toString();
                }
            }
        });
    }
    /*public static  void postRegistrarDispositivoEnServidor(String token, Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "url_servidor";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject resObj = new JSONObject(response);
                    String code = resObj.getString("code");
                    String message = resObj.getString("message");
                    Integer id = resObj.getInt("id");
                    if ("OK".equals("code")) {
                        if (id != 0) {
                            context.getSharedPreferences(Contants.SP_FILE, 0).edit().putInt("ID", id).commit();
                        }
                    } else {
                        System.out.println("OCURRIO UN ERROR AL ENVIAR EL MENSAJE");
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error registrando token de servidor: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Contants.DEVICE_ID, context.getSharedPreferences(Contants.SP_FILE, 0).getString(Contants.DEVICE_ID, null));
                if(context.getSharedPreferences(Contants.SP_FILE, 0).getInt("ID", 0) != 0) {
                    Integer val = context.getSharedPreferences(Contants.SP_FILE, 0).getInt("ID",0);
                    params.put("ID", val.toString());
                }
                return params;
            }
        };
        queue.add(stringRequest);
    }*/
}
