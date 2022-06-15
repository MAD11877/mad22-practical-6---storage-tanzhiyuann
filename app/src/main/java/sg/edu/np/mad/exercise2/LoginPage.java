package sg.edu.np.mad.exercise2;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    public static String correctUsername;
    public static String correctPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        FirebaseDatabase fireDb = FirebaseDatabase.getInstance();
        DatabaseReference myRef = fireDb.getReference("Users");

        EditText username = findViewById(R.id.editTextUsername);
        EditText password = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogin);


                //read from the database
        myRef.child("mad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                correctUsername = dataSnapshot.child("username").getValue().toString();
                correctPassword = dataSnapshot.child("password").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userUsername = username.getText().toString();
                String userPassword = password.getText().toString();
                Intent login = new Intent(LoginPage.this,ListActivity.class);

                if ((userUsername == correctUsername) && (userPassword == correctPassword)){
                    startActivity(login);
                }

            }
        });
    }

}