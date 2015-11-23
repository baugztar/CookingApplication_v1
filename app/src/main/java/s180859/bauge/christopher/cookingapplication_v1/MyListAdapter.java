package s180859.bauge.christopher.cookingapplication_v1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private List<Recipe> favList;

    public MyListAdapter(Context context, List<Recipe> recipes){
        this.mInflater = LayoutInflater.from(context);
        this.mRecipes = recipes;
        this.context = context;
        this.arraylist = new ArrayList<Recipe>();
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
       /* int clr = R.color.orange_light;
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), clr));*/

        holder.name = (TextView)view.findViewById(R.id.listItem_name);
        holder.difficulty = (TextView)view.findViewById(R.id.listItem_diff);
        holder.avatar = (ImageView)view.findViewById(R.id.listItem_img);
        holder.butt = (Button)view.findViewById(R.id.btnlol);
        holder.favIcon = (ImageView)view.findViewById(R.id.favIcon);

       /* holder.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });*/

        final Recipe r = mRecipes.get(position);
        int imgR = view.getResources().getIdentifier(r.getImg(), "drawable", getPackageName(view.getContext()));
        int imgFav;
        if(r.isFavorite()){
            imgFav = view.getResources().getIdentifier("favstarylw","drawable",getPackageName(view.getContext()));
        }
        else{
            imgFav = view.getResources().getIdentifier("favstargrey","drawable",getPackageName(view.getContext()));
        }

        // Setter lytter til favorittikon


        holder.avatar.setImageResource(imgR);
        holder.favIcon.setImageResource(imgFav);
        String formattedDiff = r.getDifficulty().substring(0,1).toUpperCase() + r.getDifficulty().substring(1);
        holder.difficulty.setText(formattedDiff);
        holder.name.setText(r.getName());
        holder.butt.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                switch(v.getId()){
                    case R.id.btnlol:
                        System.out.println("pressed");
                        Recipe r2 = mRecipes.get(position);
                        Intent i = new Intent(context,RecipePage.class);
                        Bundle b = new Bundle();
                        b.putString("name",r2.getName());
                        b.putString("desc", r2.getDescription());
                        b.putStringArray("contains", r2.getContains());
                        b.putStringArray("amount", r2.getAmount());
                        b.putString("cooktime", r2.getCooktime());
                        b.putBoolean("fav",r2.isFavorite());
                        i.putExtras(b);
                        context.startActivity(i);
                }
            }
        });
        return view;
    }


    private static class ViewHolder{
        protected TextView name, description, difficulty;
        protected ImageView avatar, favIcon;
        protected Button butt;
        protected EditText search;
    }


    public String getPackageName(Context context) {
        return context.getPackageName();
    }
    public void filter(String inText) {
        inText = inText.toLowerCase(Locale.getDefault());
        mRecipes.clear();
        if (inText.length() == 0) {
            mRecipes.addAll(arraylist);
        } else {
            for (Recipe r : arraylist) {
                if (r.getName().toLowerCase(Locale.getDefault())
                        .contains(inText)) {
                    mRecipes.add(r);
                }
            }
        }
        // refresh listview
        updateView();
    }


    // Update listview.
    private void updateView(){
        notifyDataSetChanged();
    }

    // TEST CLASssSS
    private List<Recipe> getFavList(){
        return favList;
    }

    // TEST CLASSS --- _ -
    private Boolean getFavorites(){
        SharedPreferences s = context.getSharedPreferences("prefs",Context.MODE_PRIVATE);
        return true;
    }
}
