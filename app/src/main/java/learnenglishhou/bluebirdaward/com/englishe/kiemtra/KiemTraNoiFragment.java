package learnenglishhou.bluebirdaward.com.englishe.kiemtra;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import learnenglishhou.bluebirdaward.com.englishe.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link KiemTraNoiFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link KiemTraNoiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KiemTraNoiFragment extends Fragment implements TextToSpeech.OnInitListener {

    public void daTraLoiXong(){
        KiemTraActivity.daTraLoiXongCauHoi();
    }

    private TextToSpeech daoDau;

    private Button btnKiemTraNoi;
    private TextView tvCauCanNoi;
    private TextView tvCauTraLoiCuaNguoiDung;
    private TextView tvDungHaySai;

    private boolean daTraLoiDung;

    private String cauTiengAnh;

    private int soLanNoiToiDa=3;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KiemTraNoiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KiemTraNoiFragment newInstance(String param1, String param2) {
        KiemTraNoiFragment fragment = new KiemTraNoiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public KiemTraNoiFragment() {

    }

    public void clickNoi() {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, cauTiengAnh);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 20);

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
                    ArrayList<String> txt = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    StringBuilder sb=new StringBuilder();
                    for(int i=0;i<txt.size();i++)
                        sb.append(txt.get(i) + "||");
                    tvCauTraLoiCuaNguoiDung.setText("Người dùng nói là:" + sb.toString());
                    if(cauTiengAnh.trim().toLowerCase().equals(txt.get(0).trim().toLowerCase())) {
                        KiemTraActivity.diemTungPhan[0]++;
                        tvDungHaySai.setText("(Đúng rồi)");
                        daTraLoiDung=true;
                    }
                    else
                        tvDungHaySai.setText("(Không chấp nhận câu trả lời của người dùng)");
                }
                else if (resultCode == KiemTraActivity.RESULT_OK && data == null) {
                    tvCauTraLoiCuaNguoiDung.setText("Chưa ghi nhận đáp án");
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
        btnKiemTraNoi.setText("Nói ("+soLanNoiToiDa+")");
        if(soLanNoiToiDa==0 || daTraLoiDung){
            daTraLoiXong();
            btnKiemTraNoi.setClickable(false);
            btnKiemTraNoi.setAlpha(0.2f);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        daTraLoiDung=false;
        // daoDau.setPitch(0.6d);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_kiem_tra_noi, container, false);

        daoDau=new TextToSpeech(getActivity(), this);
        daoDau.speak("Speak this sentences, please", TextToSpeech.QUEUE_FLUSH, null);


        tvCauCanNoi=(TextView) root.findViewById(R.id.tvCauCanNoi);
        tvCauCanNoi.setText(cauTiengAnh);

        tvCauTraLoiCuaNguoiDung=(TextView) root.findViewById(R.id.tvCauTraLoiCuaNguoiDung);
        tvDungHaySai=(TextView) root.findViewById(R.id.tvDungHaySai);


        btnKiemTraNoi=(Button) root.findViewById(R.id.btnKiemTraNoi);
        btnKiemTraNoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickNoi();
                soLanNoiToiDa--;
            }
        });


        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onInit(int i) {

        if(i != TextToSpeech.ERROR) {
            daoDau.setLanguage(Locale.US);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onPause() {
        daoDau.stop();
        daoDau.shutdown();
        super.onPause();
    }


}
