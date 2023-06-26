package usfx.com610.exm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import usfx.com610.exm.R;
import usfx.com610.exm.modelos.User;

public class AdapterUser extends BaseAdapter {
    private ArrayList<User> list;
    private LayoutInflater inflater;
    private Context context;
    public AdapterUser(Context context, ArrayList<User> list) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        AdapterUser.ViewHolder holder = new AdapterUser.ViewHolder();
        if(inflater == null) inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null) {
            view = inflater.inflate(R.layout.item_user, null);
        }
        holder.txtPhone = view.findViewById(R.id.txtPhone);
        holder.txtName = view.findViewById(R.id.txtName);

        User sala = list.get(position);
        holder.txtName.setText(sala.getName());
        holder.txtPhone.setText(sala.getPhone());
        return view;
    }

    public class ViewHolder {
        private TextView txtName, txtPhone;
    }

}
