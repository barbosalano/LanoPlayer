package com.example.barbosa.lanoplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekVolume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.teste);
        inicializarSeekBar();

    }

    private void inicializarSeekBar() {
        seekVolume = findViewById(R.id.seekVolume);

        //configura o audio manager
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //recupera os valores de Vol max e vl atual
        int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //configurar o valor maximo de volume da SeekBar

        seekVolume.setMax(volumeMaximo);
        seekVolume.setProgress(volumeAtual);

        seekVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_SHOW_UI);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    public void executarSom(View view) {
        if (mediaPlayer != null) {

            mediaPlayer.start();

        }
    }

    public void pararSom(View view) {
        if (mediaPlayer.isPlaying()) {

            mediaPlayer.stop();

            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.teste);

        }
    }

    public void pauseSom(View view) {
        if (mediaPlayer.isPlaying()) {

            mediaPlayer.pause();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null && mediaPlayer.isPlaying()){

            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying()) {

            mediaPlayer.pause();

        }
    }
}
