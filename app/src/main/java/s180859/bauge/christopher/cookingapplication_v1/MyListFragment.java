package s180859.bauge.christopher.cookingapplication_v1;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Christopher on 17/11/2015.
 */
public class MyListFragment extends ListFragment{
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        JSONHandler js = new JSONHandler(getActivity());
        List<Receipt> ls = js.getAllReceipts();
        System.out.println(ls.get(0).getDescription()+"-------");
        MyListAdapter mList = new MyListAdapter(getActivity(),ls);
        setListAdapter(mList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
