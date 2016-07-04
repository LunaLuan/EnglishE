package learnenglishhou.bluebirdaward.com.englishe.chude;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import learnenglishhou.bluebirdaward.com.englishe.R;


public class EngsubFragment extends Fragment {

    private String engSub;


    public EngsubFragment(String engSub) {
        // Required empty public constructor
        this.engSub=engSub;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_engsub, container, false);
        TextView tvEngsub=(TextView) root.findViewById(R.id.tvEngsub);
        tvEngsub.setText(engSub);

        tvEngsub.setMovementMethod(new ScrollingMovementMethod());
        return root;
    }


}
