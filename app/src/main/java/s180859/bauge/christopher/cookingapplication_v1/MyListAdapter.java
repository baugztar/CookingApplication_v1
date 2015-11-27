package s180859.bauge.christopher.cookingapplication_v1;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Christopher on 17/11/2015.
 */
public class MyListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Recipe> mRecipes;
    private Context context;
    private ArrayList<Recipe> arraylist;

    public MyListAdapter(Context context, List<Recipe> recipes){
        this.mInflater = LayoutInflater.from(context);
        this.mRecipes = recipes;
        this.context = context;
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(mRecipes);
    }

    @Override
    public int getCount() {
        return mRecipes.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final View view;
        final ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.listitem,parent,false);
            view.setTag(holder);
        }
        else{
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }

        holder.name = (TextView)view.findViewById(R.id.listItem_name);
        holder.difficulty = (TextView)view.findViewById(R.id.listItem_diff);
        holder.avatar = (ImageView)view.findViewById(R.id.listItem_img);
        holder.favIcon = (ImageView)view.findViewById(R.id.favIcon);

        final Recipe r = mRecipes.get(position);
        int imgR = view.getResources().getIdentifier(r.getImg(), "drawable", getPackageName(view.getContext()));
        int imgFav;
        if(r.isFavorite()){
            imgFav = view.getResources().getIdentifier("favstarylw","drawable",getPackageName(view.getContext()));
        }
        else{
            imgFav = view.getResources().getIdentifier("favstargrey","drawable",getPackageName(view.getContext()));
        }
        holder.avatar.setImageResource(imgR);
        holder.favIcon.setImageResource(imgFav);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked(position);
            }
        });
        holder.difficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked(position);
            }
        });
        holder.rel = (RelativeLayout) view.findViewById(R.id.itemyo);
        holder.difficulty.setText(r.getDifficulty());
        holder.name.setText(r.getName());
        holder.rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked(position);
            }
        });
        return view;
    }


    private static class ViewHolder{
        protected TextView name, difficulty;
        protected ImageView avatar, favIcon;
        protected RelativeLayout rel;
    }

    public void clicked(int position){
        System.out.println("pressed");
        Recipe r2 = mRecipes.get(position);
        Intent i = new Intent(context,RecipePage.class);
        Bundle b = new Bundle();
        b.putString("name",r2.getName());
        b.putString("desc", r2.getDescription());
        b.putStringArray("contains", r2.getContains());
        b.putStringArray("amount", r2.getAmount());
        b.putString("cooktime", r2.getCooktime());
        b.putString("image",r2.getRecipeimg());
        b.putString("portions",r2.getPortions());
        b.putString("type",r2.getType());
        b.putInt("id", r2.getId());
        b.putBoolean("fav",r2.isFavorite());
        i.putExtras(b);
        context.startActivity(i);
    }
    public String getPackageName(Context c) {
        return c.getPackageName();
    }

    // Filter-method to use on search method.
    public void filter(String inText) {
        // Text to lowercase for testing purposes.
        inText = inText.toLowerCase(Locale.getDefault());
        // clear list
        mRecipes.clear();
        // if searchfield empty, add entire list.
        if (inText.length() == 0) {
            mRecipes.addAll(arraylist);
        } else {
            for (Recipe r : arraylist) {
                // If search text is in name or type, add to results.
                if (r.getName().toLowerCase(Locale.getDefault())
                        .contains(inText) || r.getType().toLowerCase(Locale.getDefault()).contains(inText)) {
                    mRecipes.add(r);
                }
            }
        }
        updateView();
    }



    // Refresh listview.
    private void updateView(){
        notifyDataSetChanged();
    }

}
