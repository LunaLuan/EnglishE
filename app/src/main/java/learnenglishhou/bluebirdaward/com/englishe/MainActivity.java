package learnenglishhou.bluebirdaward.com.englishe;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import learnenglishhou.bluebirdaward.com.englishe.chude.ChuDeActivity;
import learnenglishhou.bluebirdaward.com.englishe.danhsachchude.DanhSachChuDeActivity;
import learnenglishhou.bluebirdaward.com.englishe.danhsachchude.DanhSachChuDeAdapter;
import learnenglishhou.bluebirdaward.com.englishe.model.EnglishEDatabase;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnglishEDatabase db=new EnglishEDatabase(this);

        TextView tv=(TextView) findViewById(R.id.tvHello);

        Intent i=new Intent(MainActivity.this, DanhSachChuDeActivity.class);
        startActivity(i);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
