package learnenglishhou.bluebirdaward.com.englishe.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DatabaseMetaData;

import learnenglishhou.bluebirdaward.com.englishe.R;

/**
 * Created by SVTest on 30/06/2016.
 */
public class EnglishEDatabase extends SQLiteOpenHelper {

    private static final String name="EnglishE2.s3db";

    private static String creatDBSql;


    private static String DB_PATH;

    private Context context;


    public EnglishEDatabase(Context context) {
        super(context, name, null, 3);
        if(Build.VERSION.SDK_INT >= 17)
            DB_PATH=context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH="/data/data/" + context.getPackageName() + "/databases/";

        this.context=context;
        createDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        if(!checkDatabase())
            createDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean checkDatabase() {
        File f=new File(DB_PATH + name);
        return f.exists();
    }

    public void copyDatabase() throws IOException {
        InputStream inputStream = context.getAssets().open(name);
        String outFileName= DB_PATH + name ;
        OutputStream outputStream=new FileOutputStream(outFileName);

        byte mBuffer[]=new byte[1024];
        int mLength;

        while ((mLength = inputStream.read(mBuffer)) > 0) {
            outputStream.write(mBuffer, 0, mLength);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void createDatabase() {
        if(!checkDatabase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDatabase();
                Log.d("Tạo Database", "Thành công");
            } catch (IOException e) {
                Log.e("Lỗi Database", e.getMessage());
            }
        }
    }


}
