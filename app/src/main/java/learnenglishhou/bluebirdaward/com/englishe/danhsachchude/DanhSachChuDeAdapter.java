package learnenglishhou.bluebirdaward.com.englishe.danhsachchude;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import learnenglishhou.bluebirdaward.com.englishe.R;
import learnenglishhou.bluebirdaward.com.englishe.model.ChuDe;
import learnenglishhou.bluebirdaward.com.englishe.model.EnglishEDatabase;

/**
 * Created by SVTest on 03/07/2016.
 */
public class DanhSachChuDeAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<ChuDe> data;
    private static LayoutInflater inflater=null;


    public DanhSachChuDeAdapter(Context context, ArrayList<ChuDe> data) {
        this.context=context;
        this.data=data;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }




    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
            view=inflater.inflate(R.layout.list_item_chu_de, null);

        TextView tvTenChuDe=(TextView) view.findViewById(R.id.tvTenChuDe);

        ChuDe chuDe=data.get(i);

        tvTenChuDe.setText(chuDe.getTenChuDe());

        return view;
    }
}
