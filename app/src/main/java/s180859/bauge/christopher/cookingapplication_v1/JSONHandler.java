package s180859.bauge.christopher.cookingapplication_v1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christopher on 17/11/2015.
 */
public class JSONHandler extends AppCompatActivity {

    Context c;

    public JSONHandler(Context cont){
        this.c = cont;
    }

    public JSONObject getJson(){
        JSONObject js;

        try{
            InputStream is = c.getAssets().open("test2.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String bufferString = new String(buffer);
            try{
                js = new JSONObject(bufferString);
                System.out.println(bufferString);
                return js;
            }
            catch (JSONException jse){
                jse.printStackTrace();
                return null;
            }
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Recipe> getAllReceipts(){
       List<Recipe> rList = new ArrayList<>();
       JSONObject j = getJson();
        JSONArray ar = new JSONArray();
        try {
            ar = j.getJSONArray("recipe");
        } catch(JSONException je){
            je.printStackTrace();
        }
        System.out.println("LENGDE:"+j.length());
        try{
            for (int i = 0; i < ar.length(); i++) {
                Recipe r = new Recipe();
                //Hele Json array
                JSONArray innerJsonArray = j.getJSONArray("recipe");

                // Henter hvert objekt i recipe
                JSONObject jsonObject = innerJsonArray.getJSONObject(i);

                // Get ingredients
                JSONObject ingred = jsonObject.getJSONObject("ingredients");
                // Getting containz and putting in array
                JSONArray innerJsonArrayCont = ingred.getJSONArray("contains");
                String[] containz = new String[innerJsonArrayCont.length()];
                for(int l = 0; l<containz.length;l++){
                    JSONObject newJs = innerJsonArrayCont.getJSONObject(l);
                    containz[l] = newJs.getString("ing");
                    System.out.println(containz[l]);
                }

                // Get amount JSONarray and put in array.
                JSONArray innerJsonArrayAmount = ingred.getJSONArray("amount");
                String[] amount = new String[innerJsonArrayAmount.length()];


                for(int k = 0; k<amount.length;k++){
                    JSONObject newO = innerJsonArrayAmount.getJSONObject(k);
                    amount[k] = newO.getString("amo");
                    System.out.println(amount[k]+"--");
                }

                r.setAmount(amount);
                r.setImg(jsonObject.getString("img"));
                // Get portions
                r.setPortions(jsonObject.getString("portions"));
                // Get cooktime
                r.setCooktime(jsonObject.getString("cooktime"));

                // Get name
                r.setName(jsonObject.getString("name"));

                // Get description
                r.setDescription(jsonObject.getString("description"));

                // Get difficulty
                r.setDifficulty(jsonObject.getString("difficulty"));

                // Get Containsarray
                r.setContains(containz);

                // Add receipt to list
                rList.add(r);
            }
        }catch(JSONException jse){
            jse.printStackTrace();
        }
        return rList;
    }
}
