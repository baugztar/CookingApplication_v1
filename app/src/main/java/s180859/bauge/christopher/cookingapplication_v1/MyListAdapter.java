package s180859.bauge.christopher.cookingapplication_v1;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
        return mReceipts.size();
    }

    @Override
    public Object getItem(int position) {
        return mReceipts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.listitem,parent,false);
            view.setTag(holder);
        }
        else{
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }
        int clr = (position % 2 == 0 ? R.color.white : R.color.grey);
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), clr));
        holder.name = (TextView)view.findViewById(R.id.listItem_name);
        holder.difficulty = (TextView)view.findViewById(R.id.listItem_diff);
        holder.avatar = (ImageView)view.findViewById(R.id.listItem_img);
        Receipt r = mReceipts.get(position);
        int imgR = view.getResources().getIdentifier(r.getImg(), "drawable", getPackageName(view.getContext()));
        holder.avatar.setImageResource(imgR);
        holder.difficulty.setText(r.getDifficulty());
        holder.name.setText(r.getName());
        return view;
    }


    private static class ViewHolder{
        protected TextView name, description, difficulty;
        protected ImageView avatar;
    }

    public String getPackageName(Context context) {
        return context.getPackageName();
    }
}
