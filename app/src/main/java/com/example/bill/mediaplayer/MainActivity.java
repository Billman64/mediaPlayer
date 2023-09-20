package com.example.bill.mediaplayer;

//@author: Bill Lugo

// Sound sample: "Adventure Meme" Kevin MacLeod (incompetech.com)
//        Licensed under Creative Commons: By Attribution 4.0 License
//        http://creativecommons.org/licenses/by/4.0/


import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MediaPlayer_demo";
    public int position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton play = findViewById(R.id.play);
        ImageButton ff = findViewById(R.id.ff);
        final TextView tv = findViewById(R.id.out);



//        final MediaPlayer mediaPlayer = new MediaPlayer();
//        mediaPlayer.setDataSource(getResources().openRawResourceFd(R.raw.adventure_meme));
        final MediaPlayer mediaPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.adventure_meme );
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
//                mediaPlayer.reset();
                tv.setText(R.string.media_completed);
//                Toast.makeText(MainActivity.this, "End of song!", Toast.LENGTH_SHORT).show();
                ImageButton ff = findViewById(R.id.ff);
                ff.setEnabled(false);
            }
        });


        // implement Play button functionality
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    Log.d(TAG, "Play button clicked. Position: " + mediaPlayer.getCurrentPosition());
                    tv.setText("playing");

                    // ensure buttons enabled (for replays)
                    ff.setEnabled(true);

                    play.setImageDrawable(getResources().getDrawable(R.drawable.baseline_pause_24));
                }
                else {
                    Log.d(TAG, "Pause button clicked");
                    if(mediaPlayer != null) {
                        Log.d(TAG, "position: " + mediaPlayer.getCurrentPosition() + " duration: " + mediaPlayer.getDuration());
                        mediaPlayer.pause();
                        position = mediaPlayer.getCurrentPosition();

                        tv.setText("paused at " + Integer.toString(position));
                    }

                    play.setImageDrawable(getResources().getDrawable(R.drawable.baseline_play_arrow_24));
                }
            }
        });

        // implement FF button functionality
        ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = mediaPlayer.getCurrentPosition();
                int FfAdvance = 10000;

                if(position + FfAdvance <= mediaPlayer.getDuration()) {
                    mediaPlayer.seekTo(position + FfAdvance);
                    tv.setText("FF to position: " + position);
                } else {
                    Log.d(TAG, "Fast forward cancelled, otherwise it would seek past the end");
                }

            }
        });

    }
}
