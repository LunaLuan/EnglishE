package learnenglishhou.bluebirdaward.com.englishe.chude;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

;

import learnenglishhou.bluebirdaward.com.englishe.R;
import learnenglishhou.bluebirdaward.com.englishe.kiemtra.KiemTraActivity;
import learnenglishhou.bluebirdaward.com.englishe.model.ChuDe;

public class ChuDeActivity extends ActionBarActivity {

    private int maChuDe;

    private ChuDe chuDe;

    private boolean xemSub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_de);

        Intent intent=getIntent();
        maChuDe=intent.getIntExtra("maChuDe", 1);
        chuDe=new ChuDe(this, maChuDe);


        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();

        transaction.replace(R.id.fragmentAudio, new AudioPlayerFragment(chuDe.getAudioChuDe())).commit();

        xemSub=false;

    }


    public void clickXemSub(View view) {
        Button v=(Button) view;

        xemSub=!xemSub;

        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();

        if(xemSub) {
            v.setText("Ẩn sub");
            transaction.replace(R.id.fragment_sub, new EngsubFragment(chuDe.getEngsub())).commit();
        }
        else {
            v.setText("Xem sub");
            transaction.replace(R.id.fragment_sub, new EmptyFragment()).commit();
        }

    }

    public void clickChoiTiNao(View view) {
        Intent i=new Intent(ChuDeActivity.this, KiemTraActivity.class);
        i.putExtra("maChuDe", chuDe.getMaChuDe());
        i.putExtra("audioChuDe", chuDe.getAudioChuDe());
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chu_de, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
