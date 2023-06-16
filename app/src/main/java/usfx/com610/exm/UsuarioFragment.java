package usfx.com610.exm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsuarioFragment extends Fragment {
    private CircleImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuario, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        TextView nombre = view.findViewById(R.id.txtNombre);
        imageView = view.findViewById(R.id.imageView);

        nombre.setText(user.getDisplayName() + "\n" + user.getEmail());
        if(user.getPhotoUrl() != null) {
            System.out.println(user.getPhotoUrl());
            RequestOptions options = new RequestOptions()
                    //.placeholder(R.drawable.prosgress)
                    .override(400, 400)
                    .centerCrop();
                    //.error(R.drawable.ic_alert_large);
            Glide.with(getContext()).load(user.getPhotoUrl() )
                    .apply(options).into(imageView);
        }
        return view;
    }
}
