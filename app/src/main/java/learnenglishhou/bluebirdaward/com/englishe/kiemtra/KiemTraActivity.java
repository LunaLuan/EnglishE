package learnenglishhou.bluebirdaward.com.englishe.kiemtra;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import learnenglishhou.bluebirdaward.com.englishe.R;
import learnenglishhou.bluebirdaward.com.englishe.chude.AudioPlayerFragment;
import learnenglishhou.bluebirdaward.com.englishe.model.CauHoi;

public class KiemTraActivity extends ActionBarActivity {

    public static final int REQUES_CODE=1010;
    public static boolean traLoiDung=false;
    private Button[] buttonsTrangThaiTraLoi;

    private int soCauMax=20;
    private int soCau=1;

    private static Button btnCauTiepTheo;

    protected static int diemTungPhan[];

    protected CauHoi cauHoi;

    private int maChuDe;
    private String audioChuDe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiem_tra);

        diemTungPhan=new int[3];
        Intent intent=getIntent();
        maChuDe=intent.getIntExtra("maChuDe", 1);
        audioChuDe=intent.getStringExtra("audioChuDe");


        createNewQuestion();
        createAudioPlayer();

        btnCauTiepTheo=(Button) findViewById(R.id.btnCauTiepTheo);
        btnCauTiepTheo.setClickable(false);
        btnCauTiepTheo.setAlpha(0.2f);


        createTrangThaiTraLoi();

    }

    public void createNewQuestion() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        cauHoi=new CauHoi(this, maChuDe);
        int k=cauHoi.getMaLoaiCauHoi();
        if(k==0) {
            transaction.replace(R.id.fragmentKiemTra, new KiemTraTuVungFragment()).commit();
        }
        else if(k==1) {
            transaction.replace(R.id.fragmentKiemTra, new KiemTraNoiFragment()).commit();
        }
        else {
            transaction.replace(R.id.fragmentKiemTra, new KiemTraCauHoiFragment(cauHoi.getNdCauHoi(), cauHoi.getDapAn())).commit();
        }

    }

    public void createAudioPlayer() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentAudio, new AudioPlayerFragment(audioChuDe)).commit();
    }


    public void clickCauTiepTheo(View v) {
        if(traLoiDung)
            buttonsTrangThaiTraLoi[soCau-1].getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        else
            buttonsTrangThaiTraLoi[soCau-1].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        traLoiDung=false;

        if(soCau==soCauMax) {
            final Dialog ketThuc=new Dialog(this);
            ketThuc.setContentView(R.layout.dialog_ket_thuc);
            ketThuc.setTitle("Kết thúc");

            TextView tvDiemNoi=(TextView) ketThuc.findViewById(R.id.tvDiemNoi);
            tvDiemNoi.setText("Điểm nói: " + diemTungPhan[0]);

            TextView tvDiemTuVung=(TextView) ketThuc.findViewById(R.id.tvDiemTuVung);
            tvDiemTuVung.setText("Điểm dịch: "+diemTungPhan[1]);

            TextView tvDiemCauHoi=(TextView) ketThuc.findViewById(R.id.tvDiemCauHoi);
            tvDiemCauHoi.setText("Điểm câu hỏi: "+diemTungPhan[2]);

            ketThuc.show();
        }
        else {
            soCau++;

            btnCauTiepTheo.setClickable(false);
            btnCauTiepTheo.setAlpha(0.2f);

            createNewQuestion();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kiem_tra, menu);
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

    public static void daTraLoiXongCauHoi() {
        btnCauTiepTheo.setClickable(true);
        btnCauTiepTheo.setAlpha(1);
    }

    public void createTrangThaiTraLoi() {
        buttonsTrangThaiTraLoi=new Button[soCauMax];

        LinearLayout layoutTrangThai=(LinearLayout) findViewById(R.id.layoutTrangThaiTraLoi);


        for(int i=0;i<soCauMax;i++) {
            buttonsTrangThaiTraLoi[i] = new Button(this);
            buttonsTrangThaiTraLoi[i].
                    setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
;
            // buttonsTrangThaiTraLoi[i].setBackgroundResource(R.drawable.roundedmain);
            layoutTrangThai.addView(buttonsTrangThaiTraLoi[i]);
        }

    }



}
