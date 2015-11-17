package s180859.bauge.christopher.cookingapplication_v1;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Christopher on 17/11/2015.
 */
public class MyListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Receipt> mReceipts;

    public MyListAdapter(Context context, List<Receipt> receipts){
        this.mInflater = LayoutInflater.from(context);
        this.mReceipts = receipts;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        System.out.println("JEG ER HER FOr FAEN");
        View view;
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.listitem,parent,false);
            view.setTag(holder);
            Log.d("Hello", "sweeet cheeks");
        }
        else{
            view = convertView;
            holder = (ViewHolder)view.getTag();
            Log.d("Hello","sweeet 22222");
        }
/*
        int clr = (position % 2 == 0 ? R.color.white : R.color.grey);
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), clr));*/
        holder.name = (TextView)view.findViewById(R.id.listItem_name);
        holder.description = (TextView)view.findViewById(R.id.listItem_desc);
        Receipt r = mReceipts.get(position);
        holder.name.setText(r.getName());
        holder.description.setText(r.getDescription());
        return view;
    }


    private static class ViewHolder{
        protected TextView name, description, difficulty;
    }
}
