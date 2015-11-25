package s180859.bauge.christopher.cookingapplication_v1;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

/**
 * Created by Christopher on 17/11/2015.
 */
public class MyListFragment extends ListFragment{
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createView();
    }

    public void startDb(List<Recipe> l){
        DBHandler db = new DBHandler(getActivity());
        System.out.println("test0");
        if(db.isNotUpdated()<l.size()){
            System.out.println("test22");
            System.out.println(db.isNotUpdated());
            /*for(int i = db.isNotUpdated(); i==l.size();i++){
                System.out.println("test2");
                db.addRecipe(l.get(i));
            }*/
            for (Recipe r:l)
            {
                if(db.isNotUpdated()<l.size()){
                    db.addRecipe(r);
                }
            }
        }
        db.close();
    }

    public void updateList(List<Recipe> l ){
        DBHandler db = new DBHandler(getActivity());
        for (Recipe r:l)
        {
          db.updateRecipe(r);
        }
        db.close();
    }

    public void populateFavs(List<Recipe> l){
        DBHandler db = new DBHandler(getActivity());
        for(Recipe r: l){
            int ok = db.getFavorite(r.getId()-1);
            System.out.println("loggers: " + ok);
            if(ok == 0){
                r.setFavorite(false);
            }
            else{
                r.setFavorite(true);
            }
        }
        db.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        createView();
    }

    public void createView(){
        JSONHandler js = new JSONHandler(getActivity());
        List<Recipe> ls = js.getAllReceipts();
        populateFavs(ls);
        System.out.println(ls.get(1).isFavorite() + "--test0.5");
        startDb(ls);
        System.out.println(ls.get(1).isFavorite() + "--test1");
        final MyListAdapter mList = new MyListAdapter(getActivity(),ls);
        setListAdapter(mList);
        final EditText search = (EditText)getActivity().findViewById(R.id.searchyo);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = search.getText().toString().toLowerCase(Locale.getDefault());
                (mList).filter(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ImageButton b = (ImageButton)getActivity().findViewById(R.id.clearSearchfield);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Clear inputfield
                search.setText("");
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
