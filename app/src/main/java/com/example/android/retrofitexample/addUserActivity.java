package com.example.android.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class addUserActivity extends AppCompatActivity {
    EditText userName, Password, Email, Address;
    Button addUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_user );

        userName = findViewById( R.id.userName );
        Password = findViewById( R.id.password );
        Email = findViewById( R.id.email );
        Address = findViewById( R.id.address );
        addUser = findViewById( R.id.adduserbutton );

        addUser.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl( "http://elsayedmohammed70.000webhostapp.com/" )
                        .build();
                Api api = retrofit.create( Api.class );

                // used to add new user
                Call <ResponseBody> adduserconnection =
                        api.adduser
                                ( userName.getText().toString(),
                                        Password.getText().toString(),
                                        Email.getText().toString(),
                                        Address.getText().toString() );

                adduserconnection.enqueue( new Callback <ResponseBody>() {
                    @Override
                    public void onResponse(Call <ResponseBody> call, Response <ResponseBody> response) {
                        try {
                            Toast.makeText( addUserActivity.this, response.body().string(), Toast.LENGTH_SHORT ).show();
                            userName.setText( "" );
                            Password.setText( "" );
                            Email.setText( "" );
                            Address.setText( "" );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call <ResponseBody> call, Throwable t) {

                        userName.setText( t.getMessage() );
                    }
                } );
            }
        } );


    }


    public interface Api {

        @FormUrlEncoded
        @POST("insertuserusing_post.php")
        Call <ResponseBody> adduser(@Field("username") String username,
                                    @Field("password") String password,
                                    @Field("email") String email,
                                    @Field("address") String adress);
    }


}



