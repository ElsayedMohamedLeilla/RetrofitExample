package com.example.android.retrofitexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button getPost;
    Button AddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        getPost = findViewById( R.id.getPost );
        AddUser = findViewById( R.id.addUser );

        final Intent intent1 = new Intent( this, getPostActivity.class );
        final Intent intent2 = new Intent( this, addUserActivity.class );


        getPost.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( intent1 );
            }
        } );
        AddUser.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( intent2 );
            }
        } );


    }

}
