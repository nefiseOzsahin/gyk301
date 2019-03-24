package android.example.gyk301;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SesActivity extends AppCompatActivity {


    Button recordVoiceButton,stopVoiceButton,playVoiceButton,stopButton;

    String pathSave="";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    final int REQUEST_PERMISSION_CODE=1000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ses);

        if(!checkPermissionFromDevice())
            requestPermission();



        recordVoiceButton = (Button) findViewById(R.id.record_voice_button);
        stopVoiceButton = (Button) findViewById(R.id.stop_voice_button);
        playVoiceButton = (Button) findViewById(R.id.play_voice_button);
        stopButton=(Button)findViewById(R.id.stop_button);





            recordVoiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(checkPermissionFromDevice()){

                    pathSave=Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+ UUID.randomUUID().toString()+"_audio_record.3gp";
                    setUpMediaRecorder();
                    try{
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    Toast.makeText(SesActivity.this,"Kayıt yapılıyor",Toast.LENGTH_LONG).show();
                    } else{
                        requestPermission();
                    }
                }
            });

            stopVoiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaRecorder.stop();
                }
            });

            playVoiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer=new MediaPlayer();

                    try{
                        mediaPlayer.setDataSource(pathSave);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mediaPlayer.start();
                    Toast.makeText(SesActivity.this,"Çalıyor",Toast.LENGTH_LONG).show();
                }
            });

            stopButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mediaPlayer!=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        setUpMediaRecorder();
                    }
                }
            });




    }

    private void setUpMediaRecorder() {

        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathSave);
    }

    public boolean checkPermissionFromDevice(){
        int write_external_storage_result=ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result=ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO);
        return write_external_storage_result==PackageManager.PERMISSION_GRANTED && record_audio_result==PackageManager.PERMISSION_GRANTED;
    }



public  void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        },REQUEST_PERMISSION_CODE);
}


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       switch (requestCode){
           case REQUEST_PERMISSION_CODE:
           {
               if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                   Toast.makeText(this,"izinler verildi",Toast.LENGTH_LONG).show();
               else
                   Toast.makeText(this,"izinler verilmedi",Toast.LENGTH_LONG).show();
           }

               break;
       }
    }




}
