package com.example.bill.mediaplayer;

//@author: Bill Lugo

// Sound sample: "Adventure Meme" Kevin MacLeod (incompetech.com)
//        Licensed under Creative Commons: By Attribution 4.0 License
//        http://creativecommons.org/licenses/by/4.0/


import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play = (Button)findViewById(R.id.play);
        Button pause = (Button)findViewById(R.id.pause);
        Button ff = (Button)findViewById(R.id.ff);



//        final MediaPlayer mediaPlayer = new MediaPlayer();
//        mediaPlayer.setDataSource(getResources().openRawResourceFd(R.raw.adventure_meme));
        final MediaPlayer mediaPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.adventure_meme );
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
                mediaPlayer = null;
                Toast.makeText(MainActivity.this, "End of song!", Toast.LENGTH_SHORT).show();
                Button ff = (Button)findViewById(R.id.ff);
                ff.setEnabled(false);
            }
        });



        final TextView tv = (TextView) findViewById(R.id.out);

        // implement play button functionality
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
//                String title = mediaPlayer.
//                tv.setText("playing");
            }
        });

        // implement pause button functionality
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    //TODO: fix bug where hitting pause crashes app (might need a status check before pausing/re-pausing)
                mediaPlayer.pause();
                position = mediaPlayer.getCurrentPosition();

                tv.setText("paused at " + Integer.toString(position));


            }
        });

        // implement FF button functionality
        ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = mediaPlayer.getCurrentPosition();
                mediaPlayer.seekTo(position+10000);
                tv.setText("FF to position: " + position);



            }
        });

    }
}
