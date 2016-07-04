package learnenglishhou.bluebirdaward.com.englishe.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SVTest on 02/07/2016.
 */
public class LoaiCauHoi {



    private Context context;

    private int maLoaiCauHoi;
    private String loaiCauHoi;

    public LoaiCauHoi(Context context) {
        this.context=context;
        setLoaiCauHoi();
    }

    private void setLoaiCauHoi() {
        EnglishEDatabase edb=new EnglishEDatabase(context);
        SQLiteDatabase db=edb.getReadableDatabase();


    }

}
