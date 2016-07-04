package learnenglishhou.bluebirdaward.com.englishe.chude;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import learnenglishhou.bluebirdaward.com.englishe.R;


public class AudioPlayerFragment extends Fragment {


    private MediaPlayer audio;

    private Handler myHandler=new Handler();

    private ImageButton btnPlay;
    private SeekBar seekBarStatus;

    private Runnable updateAudio=new Runnable() {
        @Override
        public void run() {
            seekBarStatus.setProgress(audio.getCurrentPosition());
            myHandler.postDelayed(this, 100);
        }
    };

    private String audioChuDe;

    public AudioPlayerFragment(String audioChuDe) {
        // Required empty public constructor
        this.audioChuDe=audioChuDe;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        audio=MediaPlayer.create(getContext(),
                getResources().getIdentifier(audioChuDe, "raw", getContext().getPackageName()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_audio_player, container, false);
        btnPlay=(ImageButton) root.findViewById(R.id.btnPlay);
        seekBarStatus=(SeekBar) root.findViewById(R.id.seekBarStatus);
        seekBarStatus.setMax(audio.getDuration());
        myHandler.postDelayed(updateAudio, 100);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (audio.isPlaying()) {
                    setPause();
                } else {
                    setPlay();
                }
            }
        });


        seekBarStatus.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b) {
                    seekBar.setProgress(i);
                    audio.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        setPause();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void setPlay() {
        btnPlay.setImageResource(R.drawable.pause);
        audio.start();
    }

    public void setPause() {
        btnPlay.setImageResource(R.drawable.play);
        audio.pause();
    }
}
