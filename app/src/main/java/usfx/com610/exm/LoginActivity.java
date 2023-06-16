package usfx.com610.exm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    // ./gradlew signinReport
    // http://tomeko.net/online_tools/hex_to_base64.php

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // FacebookSdk.sdkInitialize(getApplicationContext());
        // AppEventsLogger.activateApp(this);
        Login();

    }
    private void Login() {
        FirebaseUser usuario =  FirebaseAuth.getInstance().getCurrentUser();
        if(usuario != null) {
            Toast.makeText(LoginActivity.this, "Inicia session" + usuario.getDisplayName() + " " + usuario.getEmail() + " "+ usuario.getProviderId(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }else{
            Intent intent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(Arrays.asList(
                        new AuthUI.IdpConfig.EmailBuilder().build(),
                        new AuthUI.IdpConfig.GoogleBuilder().build(),
                        new AuthUI.IdpConfig.FacebookBuilder() // ERROR: https://stackoverflow.com/questions/76345578/one-or-more-invalid-package-names-make-sure-package-names-are-associated-with
                                .setPermissions(Arrays.asList("email", "public_profile", "user_photos"))
                                .build()
                            // new AuthUI.IdpConfig.TwitterBuilder().build()
                        ))
                    .setIsSmartLockEnabled(false).build();
            startActivityForResult(intent, RC_SIGN_IN);
            /*List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                    new AuthUI.IdpConfig.FacebookBuilder()
                            .setPermissions(Arrays.asList("email", "public_profile"))
                            .build(),
                    new AuthUI.IdpConfig.PhoneBuilder().build(),
                     new AuthUI.IdpConfig.TwitterBuilder().build()
                    );


            Intent signInIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build();
            signInLauncher.launch(signInIntent);*/
        }
    }
    /*private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                   onSignInResult(result);
                }
            }
    );
    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Login();
        } else {
            if (response == null) {
                Toast.makeText(LoginActivity.this, "El inicio de sesión ha sido cancelado", Toast.LENGTH_SHORT).show();
                return;
            }
            if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                Toast.makeText(LoginActivity.this, "No hay conexión a internet", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR){
                Toast.makeText(this, "Error desconosido", Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(LoginActivity.this, "Error al iniciar sesión: " + response.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            Toast.makeText(LoginActivity.this, "Respondio", Toast.LENGTH_SHORT).show();
            if (resultCode == RESULT_OK) {
                // El usuario ha iniciado sesión exitosamente
                Login();
                finish();
            } else {
                // El inicio de sesión ha fallado
                if (response == null) {
                    Toast.makeText(LoginActivity.this, "El inicio de sesión ha sido cancelado", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(LoginActivity.this, "No hay conexión a internet", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR){
                    Toast.makeText(this, "Error desconosido", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(LoginActivity.this, "Error al iniciar sesión: " + response.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
