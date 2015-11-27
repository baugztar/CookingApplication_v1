package s180859.bauge.christopher.cookingapplication_v1;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnAll,btnFav,btnDinner,btnLunch,btnDessert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHandler db = new DBHandler(this);
        final FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ListFragment f1 = new MyListFragment();
        ft.replace(R.id.contentFragment, f1);
        ft.addToBackStack(null).commit();
        btnAll = (Button)findViewById(R.id.btnAll);
        btnFav = (Button)findViewById(R.id.btnFav);
        btnLunch= (Button)findViewById(R.id.btnLunch);
        btnDinner = (Button)findViewById(R.id.btnDinner);
        btnDessert = (Button)findViewById(R.id.btnDessert);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.popBackStack();
                ListFragment f = new MyListFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.contentFragment, f);
                ft.commit();
                fm.executePendingTransactions();
            }
        });

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // prevent fragments from stacking
                fm.popBackStack();
                ListFragment f = new FavListFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.contentFragment, f);
                ft.commit();
                fm.executePendingTransactions();
                System.out.println(fm.getBackStackEntryCount());
            }
        });
        btnLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // prevent fragments from stacking
                fm.popBackStack();
                ListFragment f = new LunchListFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.contentFragment, f);
                ft.commit();
                fm.executePendingTransactions();
            }
        });
        btnDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // prevent fragments from stacking
                fm.popBackStack();
                ListFragment f = new DinnerListFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.contentFragment, f);
                ft.commit();
                fm.executePendingTransactions();
            }
        });
        btnDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // prevent fragments from stacking
                fm.popBackStack();
                ListFragment f = new DessertListFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.contentFragment, f);
                ft.commit();
                fm.executePendingTransactions();
            }
        });
        db.close();
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

    @Override
    // Prompt if user want to exit app.
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Er du sikker på du vil avslutte?")
                .setCancelable(false)
                .setPositiveButton("Ja",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int i) {
                                // destroy activity - keep state
                                finish();
                            }
                        })
                .setNegativeButton("Nei", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int i) {
                        // dismiss dialog
                        d.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
