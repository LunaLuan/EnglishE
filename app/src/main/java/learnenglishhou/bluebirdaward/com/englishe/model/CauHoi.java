package learnenglishhou.bluebirdaward.com.englishe.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SVTest on 02/07/2016.
 */
public class CauHoi {

    private static final String table="CauHoi";
    private static final String columns[]={"ndCauHoi", "dapAn"};
    private static String selection;
    private static final String orderBy="random()";
    private static final String limit="1";


    private Context context;

    private int maCauHoi;
    private int maLoaiCauHoi;
    private int maChuDe;


    private String ndCauHoi;
    private String dapAn;

    private boolean coCauHoi;

    public CauHoi(Context context,int maChuDe,int maLoaiCauHoi) {
        this.maChuDe=maChuDe;
        this.maLoaiCauHoi=maLoaiCauHoi;
        this.context=context;
        this.coCauHoi=true;

        selection="maChuDe="+maChuDe+" and maLoaiCauHoi"+maLoaiCauHoi;

        setCauHoi();
    }


    public CauHoi(Context context,int maChuDe) {
        this.maChuDe=maChuDe;
        this.context=context;

        selection="maChuDe="+maChuDe;

        setCauHoi();
    }

    private void setCauHoi() {
        EnglishEDatabase edb=new EnglishEDatabase(context);
        SQLiteDatabase db=edb.getReadableDatabase();

        Cursor c=db.rawQuery("select * from CauHoi where maChuDe="+maChuDe+" order by random() limit 1", null);
        if(c.getCount()==0) {
            coCauHoi=false;
        }
        else {
            c.moveToFirst();

            ndCauHoi=c.getString(c.getColumnIndex("ndCauHoi"));
            dapAn=c.getString(c.getColumnIndex("dapAn"));
            maLoaiCauHoi=c.getInt(c.getColumnIndex("maLoaiCauHoi"));

            c.close();
            edb.close();
        }

    }



    public String getNdCauHoi() {
        return ndCauHoi;
    }

    public String getDapAn() {
        return dapAn;
    }

    public boolean coCauHoi() {
        return coCauHoi;
    }

    public int getMaLoaiCauHoi() {
        return maLoaiCauHoi;
    }
}
