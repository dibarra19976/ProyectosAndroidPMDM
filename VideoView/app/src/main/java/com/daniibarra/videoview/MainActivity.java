package com.daniibarra.videoview;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
    private VideoView mVideoView;
    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoView =(VideoView)findViewById(R.id.video_view);
        String videoPath = "android.resource://" +
                getPackageName() + "/" + R.raw.video;
        //video online
        // Uri videoUri = Uri.parse("https://file-examples.com/storage/fea9681e2b675ee6f9440a0/2017/04/file_example_MP4_1280_10MG.mp4");
        Uri videoUri = Uri.parse(videoPath);
        mVideoView.setVideoURI(videoUri);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.start();
        mVideoView.requestFocus();
    }
}