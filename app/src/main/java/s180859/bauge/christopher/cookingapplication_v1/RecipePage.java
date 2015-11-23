package s180859.bauge.christopher.cookingapplication_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Christopher on 20/11/2015.
 */
public class RecipePage extends AppCompatActivity {

    String name, desc;
    String[] ingrArr, amoArr;
    public RecipePage() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        final Recipe r = new Recipe();
        boolean isFav = b.getBoolean("fav");
        TextView outName = (TextView)findViewById(R.id.recipe_name);
        TextView outDesc = (TextView)findViewById(R.id.recipe_description);
        TextView outIngr = (TextView)findViewById(R.id.recipe_ingredients);
        final ImageView favIcon = (ImageView)findViewById(R.id.favIconToggle);

        r.setName(name);
        r.setDescription(desc);
        r.setFavorite(isFav);

        drawFav(r, favIcon);


        name = b.getString("name");
        desc = b.getString("desc");
        ingrArr = b.getStringArray("contains");
        amoArr = b.getStringArray("amount");
        outName.setText(name);
        outDesc.setText(desc);
        outIngr.setText(formatContainsAndAmount(ingrArr,amoArr));


        favIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r.isFavorite()) {
                    r.setFavorite(false);
                    Toast.makeText(getApplicationContext().getApplicationContext(), "Favoritt fjernet", Toast.LENGTH_SHORT).show();
                    drawFav(r, favIcon);

                } else {
                    r.setFavorite(true);
                    Toast.makeText(getApplicationContext().getApplicationContext(), "Lagt til favoritt", Toast.LENGTH_SHORT).show();
                    drawFav(r, favIcon);
                }
            }
        });
    }

    private String formatContainsAndAmount(String[] arrIng, String[] arrAmo ){
        int i = 0;
        String out = "Ingredients:\n";
        while(i<arrIng.length && i<arrAmo.length){
            out+=arrAmo[i]+" "+arrIng[i]+"\n";
            i++;
        }
        return out;
    }

    public void drawFav(Recipe r, ImageView favIcon){
        int imgFav;
        if(r.isFavorite()){
            imgFav = getResources().getIdentifier("favstarylw","drawable",getPackageName());
        }
        else{
            imgFav = getResources().getIdentifier("favstargrey","drawable",getPackageName());
        }
        favIcon.setImageResource(imgFav);
    }
}
