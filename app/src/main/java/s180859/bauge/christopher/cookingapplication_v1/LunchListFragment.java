package s180859.bauge.christopher.cookingapplication_v1;

/**
 * Created by Christopher on 27/11/2015.
 */

import android.app.ListFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Christopher on 17/11/2015.
 */
public class LunchListFragment extends ListFragment {
    List<Recipe> rr;
    EditText search;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void populateFavs(List<Recipe> l){
        DBHandler db = new DBHandler(getActivity());
        for(Recipe r: l){
            int ok = db.getFavorite(r.getId()-1);
            if(ok == 0){
                r.setFavorite(false);
            }
            else{
                r.setFavorite(true);
            }
        }
        db.close();
    }

    public List<Recipe> populateLunch(List<Recipe> l){
        List<Recipe> r2 = new ArrayList<>();
        for(Recipe r: l){
            if(r.getType().toLowerCase().equals("lunsj")){
                r2.add(r);
            }
        }
        return r2;
    }

    @Override
    public void onResume() {
        super.onResume();
        createView();
    }

    public void createView(){
        JSONHandler js = new JSONHandler(getActivity());
        List<Recipe> ls = js.getAllReceipts();
        rr = populateLunch(ls);
        populateFavs(ls);
        final MyListAdapter mList = new MyListAdapter(getActivity(),rr);
        setListAdapter(mList);
        search = (EditText)getActivity().findViewById(R.id.searchyo);
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
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSearchField();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    // Clear inputfield for search
    public void clearSearchField(){
        search = (EditText)getActivity().findViewById(R.id.searchyo);
        search.setText("");
    }

    @Override
    public void onPause() {
        super.onPause();
        clearSearchField();
    }
}
