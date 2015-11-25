package s180859.bauge.christopher.cookingapplication_v1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Christopher on 24/11/2015.
 */
public class FavListFragment extends MyListFragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        JSONHandler js = new JSONHandler(getActivity());
        List<Recipe> ls = js.getAllReceipts();
        final MyListAdapter mList = new MyListAdapter(getActivity(),ls);
        mList.getFavIds(ls);
        setListAdapter(mList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
