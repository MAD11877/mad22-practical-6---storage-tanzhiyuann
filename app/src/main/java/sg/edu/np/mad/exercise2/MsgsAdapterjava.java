package sg.edu.np.mad.exercise2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MsgsAdapterjava
        extends RecyclerView.Adapter<MsgViewHolder> {
    ArrayList<user> data;


    public MsgsAdapterjava(ArrayList<user> data) {
        this.data = data; }
    @NonNull
    @Override

    public MsgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        if(viewType == 1) {
            item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_list1, null, false);
        }
        else {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list2, parent, false);
        }
        return new MsgViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgViewHolder holder, int position){
        String name = data.get(position).name;
        holder.name.setText(name);

        String description = data.get(position).description;
        holder.description.setText(description);

        Boolean followed = data.get(position).followed;

        holder.icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder ad = new AlertDialog.Builder(view.getContext());
                ad.setTitle("Profile").setMessage(name).setCancelable(true)
                        .setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Bundle extras = new Bundle();
                                extras.putString("name", name);
                                extras.putString("description", description);
                                extras.putInt("id", holder.getAdapterPosition());
                                extras.putBoolean("followed", followed);
                                Intent page = new Intent(view.getContext(), MainActivity.class);
                                page.putExtras(extras);


                                view.getContext().startActivity(page);

                            }
                        })

                        .setNegativeButton("ClOSE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                ad.show();

            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        String name = data.get(position).name;
        return (name.charAt(name.length()-1) == '7') ? 0 : 1;
    }

    @Override
    public int getItemCount(){

        return data.size();
    }
}
