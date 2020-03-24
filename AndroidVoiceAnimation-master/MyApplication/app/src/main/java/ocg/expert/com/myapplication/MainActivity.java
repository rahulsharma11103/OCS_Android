package ocg.expert.com.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity implements VoiceView.OnRecordListener {

    private static final String TAG = MainActivity.class.getName();

    private TextView mTextView;
    private VoiceView mVoiceView;
    private MediaRecorder mMediaRecorder;
    private Handler mHandler;

    private boolean mIsRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);
        mVoiceView = (VoiceView) findViewById(R.id.voiceview);
        mVoiceView.setOnRecordListener(this);

        mHandler = new Handler(Looper.getMainLooper());
        checkPermi();
    }

    private void checkPermi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) ==
                    PackageManager.PERMISSION_GRANTED) {
                // put your code for Version>=Marshmallow
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
                    Toast.makeText(this,
                            "App required access to audio", Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO
                }, 101);
            }

        } else {
            // put your code for Version < Marshmallow
        }
        isStoragePermissionGranted();
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }


    @Override
    public void onRecordStart() {
        Log.d(TAG, "onRecordStart");
        try {
            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mMediaRecorder.setOutputFile(new File(Environment.getExternalStorageDirectory(), "audio.amr").getAbsolutePath());
            mMediaRecorder.prepare();
            mMediaRecorder.start();
            mIsRecording = true;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    float radius = (float) Math.log10(Math.max(1, mMediaRecorder.getMaxAmplitude() - 500)) * ScreenUtils.dp2px(MainActivity.this, 20);
                    mTextView.setText(String.valueOf(radius));
                    mVoiceView.animateRadius(radius);
                    if (mIsRecording) {
                        mHandler.postDelayed(this, 50);
                    }
                }
            });
        } catch (IOException e) {
            Toast.makeText(this, "MediaRecorder prepare failed!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onRecordFinish() {
        Log.d(TAG, "onRecordFinish");
        mIsRecording = false;
        mMediaRecorder.stop();
    }


    @Override
    protected void onDestroy() {
        if (mIsRecording) {
            mMediaRecorder.stop();
            mIsRecording = false;
        }
        mMediaRecorder.release();
        super.onDestroy();
    }
}

