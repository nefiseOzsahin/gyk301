package android.example.gyk301;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button camera;
    Button videoCek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camera=(Button)findViewById(R.id.camera);
        videoCek=(Button)findViewById(R.id.video_cek);



        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,CameraActivity.class);
                startActivity(i);
            }
        });


        videoCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,VideoActivity.class);
                startActivity(i);
            }
        });
    }

    public void sesKayit(View view) {
        Intent i=new Intent(MainActivity.this,SesActivity.class);
        startActivity(i);
    }

    public void haritayaGit(View view) {

        Intent i=new Intent(MainActivity.this,MapActivity.class);
        startActivity(i);
    }
}
