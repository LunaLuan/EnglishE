package learnenglishhou.bluebirdaward.com.englishe.kiemtra;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import learnenglishhou.bluebirdaward.com.englishe.R;


public class KiemTraCauHoiFragment extends Fragment {

    public void daTraLoiXong(){
        KiemTraActivity.daTraLoiXongCauHoi();
    }

    private TextView tvCauHoi;

    private Button btnKiemTraCauHoi;
    private TextView tvCauTraLoiCuaNguoiDung;
    private TextView tvDungHaySai;

    private TextView tvDapAn;

    private String ndCauHoi;
    private String dapAn;


    public KiemTraCauHoiFragment(String ndCauHoi, String dapAn) {
        this.ndCauHoi=ndCauHoi;
        this.dapAn=dapAn;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_kiem_tra_cau_hoi, container, false);
        btnKiemTraCauHoi=(Button) root.findViewById(R.id.btnKiemTraCauHoi);
        btnKiemTraCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickNoi();
            }
        });
        tvCauHoi=(TextView) root.findViewById(R.id.tvCauHoi);
        tvCauHoi.setText(ndCauHoi);

        tvCauTraLoiCuaNguoiDung=(TextView) root.findViewById(R.id.tvCauTraLoiCuaNguoiDung);
        tvDapAn=(TextView) root.findViewById(R.id.tvDapAn);
        tvDungHaySai=(TextView) root.findViewById(R.id.tvDungHaySai);

        return root;
    }



    public void clickNoi() {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, ndCauHoi);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);

        try {
            startActivityForResult(intent, KiemTraActivity.REQUES_CODE);
        }
        catch (ActivityNotFoundException a)
        {
            Toast.makeText(getContext(), " Không đúng ", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case KiemTraActivity.REQUES_CODE:
                if (resultCode == KiemTraActivity.RESULT_OK && data != null) {
                    xuLyCauTraLoi(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS));
                }
                else if(resultCode == RecognizerIntent.RESULT_AUDIO_ERROR)
                    Toast.makeText(getContext(), "Âm thanh lỗi", Toast.LENGTH_LONG);
                else if(resultCode == RecognizerIntent.RESULT_CLIENT_ERROR)
                    Toast.makeText(getContext(), "Client lỗi", Toast.LENGTH_LONG);
                else if(resultCode == RecognizerIntent.RESULT_NETWORK_ERROR)
                    Toast.makeText(getContext(), "Mạng lỗi", Toast.LENGTH_LONG);
                else if(resultCode == RecognizerIntent.RESULT_NO_MATCH)
                    Toast.makeText(getContext(), "Không trùng vs kết quả nào", Toast.LENGTH_LONG);
                else if(resultCode == RecognizerIntent.RESULT_SERVER_ERROR)
                    Toast.makeText(getContext(), "Server lỗi", Toast.LENGTH_LONG);
                break;

            default:
                Toast.makeText(getContext(), "Chưa nhận được câu trả lời", Toast.LENGTH_LONG);
                break;
        }


        daTraLoiXong();
        btnKiemTraCauHoi.setClickable(false);
        btnKiemTraCauHoi.setAlpha(0.2f);

    }

    public void xuLyCauTraLoi(ArrayList<String> a) {
        tvCauTraLoiCuaNguoiDung.setText("Người dùng nói là: " + a.get(0));
        tvDapAn.setText("Đáp án: "+dapAn);
        String temp1=dapAn.trim().toLowerCase();
        String temp2=a.get(0).trim().toLowerCase();
        if(temp1.equals(temp2)) {
            KiemTraActivity.diemTungPhan[2]++;
            tvDungHaySai.setText("(Đúng rồi)");
            KiemTraActivity.traLoiDung=true;
        }
        else {
            tvDungHaySai.setText("(Không chấp nhận câu trả lời của người dùng)");
            KiemTraActivity.traLoiDung=false;
        }

    }

}
