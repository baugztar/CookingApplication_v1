package s180859.bauge.christopher.cookingapplication_v1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ListFragment f1 = new MyListFragment();
        ft.replace(R.id.contentFragment, f1);
        ft.addToBackStack(null).commit();
        btn1 = (Button)findViewById(R.id.btnAll);
        btn2 = (Button)findViewById(R.id.btnFav);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFragment f1 = new MyListFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null).commit();
                ft.replace(R.id.contentFragment, f1);
                fm.popBackStack();
                System.out.println(fm.getBackStackEntryCount()+"backkustackku");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFragment f1 = new FavListFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null).commit();
                ft.replace(R.id.contentFragment, f1);
                fm.popBackStack();
                System.out.println(fm.getBackStackEntryCount());
            }
        });

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

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
