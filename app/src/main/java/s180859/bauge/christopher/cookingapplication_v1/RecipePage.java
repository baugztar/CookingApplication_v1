package s180859.bauge.christopher.cookingapplication_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Christopher on 20/11/2015.
 */
public class RecipePage extends AppCompatActivity {

    String name, desc,type,portions;
    String[] ingrArr, amoArr;
    int id;
    public RecipePage() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        final JSONHandler js = new JSONHandler(getApplicationContext());
        final JSONObject j = js.getJson();
        final Recipe r = new Recipe();
        boolean isFav = b.getBoolean("fav");
        TextView outName = (TextView) findViewById(R.id.recipe_name);
        TextView outDesc = (TextView) findViewById(R.id.recipe_description);
        TextView outIngr = (TextView) findViewById(R.id.recipe_ingredients);
        TextView outDiv = (TextView) findViewById(R.id.recipe_div);
        final ImageView favIcon = (ImageView) findViewById(R.id.favIconToggle);
        ImageView image = (ImageView)findViewById(R.id.recipe_image);
        int img = getResources().getIdentifier("sildpotet", "drawable", getPackageName());
        image.setImageResource(img);
        id = b.getInt("id");
        r.setId(id);
        r.setName(name);
        r.setDescription(desc);
        r.setFavorite(isFav);
        drawFav(r, favIcon);
        portions = b.getString("portions");
        type = b.getString("type");
        name = b.getString("name");
        desc = b.getString("desc");
        ingrArr = b.getStringArray("contains");
        amoArr = b.getStringArray("amount");
        String outputDiv = "Type: "+type+"\nPortions: "+portions;
        outDiv.setText(outputDiv);
        outName.setText(name);
        outDesc.setText(desc);
        outIngr.setText(formatContainsAndAmount(ingrArr, amoArr));


        favIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler db = new DBHandler(getApplicationContext());
                if (r.isFavorite()) {
                    r.setFavorite(false);
                    db.updateRecipe(r);
                    try {
                        JSONArray jar = j.getJSONArray("recipe");
                        for(int i = 0; i<jar.length();i++){
                            JSONObject ob = jar.getJSONObject(i);
                        if ((ob.getString("id").equals(String.valueOf(r.getId())))){
                            ob.put("favorite", "false");
                            }
                        }
                    }
                    catch(JSONException jse){
                        jse.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext().getApplicationContext(), "Favoritt fjernet", Toast.LENGTH_SHORT).show();
                    drawFav(r, favIcon);
                } else {
                    r.setFavorite(true);
                    db.updateRecipe(r);
                    try {
                        JSONArray jar = j.getJSONArray("recipe");
                        for(int i = 0; i<jar.length();i++){
                            JSONObject ob = jar.getJSONObject(i);
                            if (Integer.parseInt(ob.getString("id"))==(r.getId())) {
                                ob.put("favorite", "true");
                            }
                        }
                    }
                    catch(JSONException jse){
                        jse.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext().getApplicationContext(), "Lagt til favoritt", Toast.LENGTH_SHORT).show();
                    drawFav(r, favIcon);
                }
            }
        });
    }

    private String formatContainsAndAmount(String[] arrIng, String[] arrAmo) {
        int i = 0;
        String out = "Ingredients:\n";
        while (i < arrIng.length && i < arrAmo.length) {
            out += arrAmo[i] + " " + arrIng[i] + "\n";
            i++;
        }
        return out;
    }

    public void drawFav(Recipe r, ImageView favIcon) {
        int imgFav;
        if (r.isFavorite()) {
            imgFav = getResources().getIdentifier("favstarylw", "drawable", getPackageName());
        } else {
            imgFav = getResources().getIdentifier("favstargrey", "drawable", getPackageName());
        }
        favIcon.setImageResource(imgFav);
    }

}