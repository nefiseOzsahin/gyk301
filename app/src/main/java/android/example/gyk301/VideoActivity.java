package android.example.gyk301;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView=(VideoView)findViewById(R.id.video_onizleme);

    }

    public void videoCek(View view) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(takePictureIntent, 101);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        videoView.setVideoURI(data.getData());
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();

    }
}
