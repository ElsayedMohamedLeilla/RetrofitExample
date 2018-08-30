package com.example.android.retrofitexample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class getPostActivity extends AppCompatActivity {

    TextView getResults;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_get_post );


        getResults = findViewById( R.id.getPostsResults );
        button = findViewById( R.id.getPostbutton );

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl( "http://elsayedmohammed70.000webhostapp.com/" )
                        .addConverterFactory( GsonConverterFactory.create() )   // used to get posts from url
                        .build();
                Api api = retrofit.create( Api.class );

                Call <ResultModel> connection = api.getPosts();

                connection.enqueue( new Callback <ResultModel>() {
                    @Override
                    public void onResponse(@NonNull Call <ResultModel> call, @NonNull Response <ResultModel> response) {
                        List <Post> posts = response.body().getPosts();
                        for (int i = 0; i < posts.size(); i++) {
                            getResults.append( posts.get( i ).getPost_content() + "\n" );
                            getResults.append( "****************************" + "\n" );
                        }
                    }

                    @Override
                    public void onFailure(Call <ResultModel> call, Throwable t) {
                        Log.d( "Fail!", t.getMessage() );

                    }
                } );

            }
        } );


    }


    public interface Api {

        @GET("getposts.php")
        Call <ResultModel> getPosts();

    }

    public class ResultModel {
        private List <Post> posts;

        public List <Post> getPosts() {
            return posts;
        }

        public void setPosts(List <Post> posts) {
            this.posts = posts;
        }
    }

    public class Post {
        String post_content;

        public void setPost_content(String post_content) {
            this.post_content = post_content;
        }

        public String getPost_content() {
            return post_content;
        }
    }


}
