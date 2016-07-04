package learnenglishhou.bluebirdaward.com.englishe.danhsachchude;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import learnenglishhou.bluebirdaward.com.englishe.R;
import learnenglishhou.bluebirdaward.com.englishe.chude.ChuDeActivity;
import learnenglishhou.bluebirdaward.com.englishe.model.ChuDe;
import learnenglishhou.bluebirdaward.com.englishe.model.EnglishEDatabase;

public class DanhSachChuDeActivity extends ActionBarActivity {

    private ListView lvDanhSachChuDe;
    private ArrayList<ChuDe> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chu_de);
        setData();

        lvDanhSachChuDe=(ListView) findViewById(R.id.lvDanhSachChuDe);
        lvDanhSachChuDe.setAdapter(new DanhSachChuDeAdapter(this,data));

        lvDanhSachChuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vaoChuDe(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_danh_sach_chu_de, menu);
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

    public void vaoChuDe(int position) {
        Intent i=new Intent(DanhSachChuDeActivity.this, ChuDeActivity.class);
        i.putExtra("maChuDe", data.get(position).getMaChuDe());
        // i.putExtra("tenChuDe", data.get(position).getTenChuDe());
        Log.d("Ma chu de", String.valueOf(data.get(position).getMaChuDe()) );
        startActivity(i);

    }

    private void setData() {
        EnglishEDatabase edb=new EnglishEDatabase(this);
        SQLiteDatabase db=edb.getReadableDatabase();
        Cursor c=db.query("ChuDe", new String[]{"maChuDe", "tenChuDe"}, null, null, null, null, null);

        data=new ArrayList<>();
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++) {
            ChuDe chuDe=new ChuDe();
            chuDe.setMaChuDe(c.getInt(c.getColumnIndex("maChuDe")));
            chuDe.setTenChuDe(c.getString(c.getColumnIndex("tenChuDe")));

            data.add(chuDe);
            c.moveToNext();
        }
        c.close();
        edb.close();
    }
}
