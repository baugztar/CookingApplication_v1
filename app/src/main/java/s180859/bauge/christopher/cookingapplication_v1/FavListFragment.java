package s180859.bauge.christopher.cookingapplication_v1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;
import java.util.Locale;

/**
 * Created by Christopher on 24/11/2015.
 */
public class FavListFragment extends MyListFragment {
    List<Recipe> rr;
    EditText search;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createView();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void startDb(List<Recipe> l){
        DBHandler db = new DBHandler(getActivity());
        if(db.isNotUpdated()<l.size()){
            System.out.println(db.isNotUpdated());
            for (Recipe r:l)
            {
                if(db.isNotUpdated()<l.size()){
                    db.addRecipe(r);
                }
            }
        }
        db.close();
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

    @Override
    public void onResume() {
        super.onResume();
        createView();
    }

    public void createView(){
        JSONHandler js = new JSONHandler(getActivity());
        List<Recipe> ls = js.getAllReceipts();
        populateFavs(ls);
        startDb(ls);
        final MyListAdapter mList = new MyListAdapter(getActivity(),ls);
        rr = mList.getFavz(ls);
        final MyListAdapter mList2 = new MyListAdapter(getActivity(),rr);
        setListAdapter(mList2);
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
                // Clear inputfield
                clearSearchField();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

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
