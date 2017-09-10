package com.example.harshit.finalyear;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button cliant,server,viewer;
    final int VIDEO_REQ_CODE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init()
    {
        cliant=(Button)findViewById(R.id.btn1);
        server=(Button)findViewById(R.id.btn2);
        viewer=(Button)findViewById(R.id.btn3);
    }

    public void submit(View v)
    {
        if(v.getId()==R.id.btn1)
        {
            Intent in=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            File vdo=getfile();
            Uri vdo_uri=Uri.fromFile(vdo);
            in.putExtra(MediaStore.EXTRA_OUTPUT,vdo_uri);
            in.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,0);
            startActivityForResult(in,VIDEO_REQ_CODE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==VIDEO_REQ_CODE)
        {
            if(resultCode==RESULT_OK)
            {
                Toast.makeText(getApplicationContext(), "VIDEO CAPTURED", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "ERROR CAPTURING VIDEO", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public File getfile()
    {
        File folder=new File("sdcard/video_client");
        if(!folder.exists())
        {
            folder.mkdir();
        }
        File videofile=new File(folder,"sample.mp4");

        return videofile;
    }
}
