package sg.edu.np.mad.exercise2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Random rand = new Random();
        //int number = rand.nextInt();

        //ImageView icon = findViewById(R.id.icon);//find image id//

        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile").setMessage("MADness").setCancelable(true)
                .setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent ListActivity = new Intent (ListActivity.this, MainActivity.class);
                        ListActivity.putExtra("number", number);


                        startActivity(ListActivity);

                    }
                })

                .setNegativeButton("ClOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog alert = builder.create();

        icon.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        alert.show();
                                    }
        });*/

        DBHandler dbHandler = new DBHandler(this);
        for (int i = 0; i < 20; i++){
            int r1 = rand.nextInt();
            int r2 = rand.nextInt();

            boolean followed = rand.nextBoolean();


            user u = new user("Name" + r1, "description" + r2, r1, followed );
            dbHandler.addUser(u);

        }

        ArrayList<user> userList = dbHandler.getUsers();
        RecyclerView rv = findViewById(R.id.recyclerView);
        MsgsAdapterjava adapter = new MsgsAdapterjava(userList);
        LinearLayoutManager layout = new LinearLayoutManager(this);

        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);


    }

}