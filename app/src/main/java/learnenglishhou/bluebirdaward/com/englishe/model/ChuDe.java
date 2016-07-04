package learnenglishhou.bluebirdaward.com.englishe.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by SVTest on 01/07/2016.
 */
public class ChuDe {

    private static final String table="ChuDe";
    private static final String comlumns[] = {"tenChuDe", "engSub", "audioChuDe"};
    private String selection= "maChuDe=";

    private int maChuDe;
    private String tenChuDe;
    private String engSub;
    private String audioChuDe;

    private Context context;

    public ChuDe() {
    }

    public void setMaChuDe(int maChuDe) {
        this.maChuDe=maChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe=tenChuDe;
    }

    public ChuDe(Context context,int maChuDe) {
        this.context=context;
        this.maChuDe=maChuDe;
        selection+=maChuDe;
        setChuDe();
        // Log.d("ChuDe", audioChuDe);
    }

    private void setChuDe() {
        EnglishEDatabase edb=new EnglishEDatabase(context);
        SQLiteDatabase db=edb.getReadableDatabase();

        Cursor c=db.query(table, comlumns, selection, null, null, null, null);
        if(c!=null)
            c.moveToFirst();
        else if(c.getCount()>0)
            c.moveToNext();

        tenChuDe=c.getString(c.getColumnIndex("tenChuDe"));
        engSub=c.getString(c.getColumnIndex("engSub"));
        audioChuDe=c.getString(c.getColumnIndex("audioChuDe"));

        c.close();
        edb.close();
    }

    public int getMaChuDe() {
        return maChuDe;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public String getEngsub() {
        return engSub;
    }

    public String getAudioChuDe() {
        return audioChuDe;
    }
}
