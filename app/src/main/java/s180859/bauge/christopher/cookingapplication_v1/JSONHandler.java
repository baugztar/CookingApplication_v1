package s180859.bauge.christopher.cookingapplication_v1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
            InputStream is = c.getAssets().open("test.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String bufferString = new String(buffer);
            Log.d("JSON HEEER: ", "-" + bufferString);
            try{
                js = new JSONObject(bufferString);
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

    public List<Receipt> getAllReceipts(){
       List<Receipt> rList = new ArrayList<>();
       JSONObject j = getJson();

        try{
            for (int i = 0; i <= j.length(); i++) {
                Receipt r = new Receipt();
                //Hele Json array
                JSONArray innerJsonArray = j.getJSONArray("recipe");

                // Henter hvert objekt i recipe
                JSONObject jsonObject = innerJsonArray.getJSONObject(i);

                // Henter ingredientsobj
                JSONObject ingred = jsonObject.getJSONObject("ingredients");
                // Henter contains og slenger i array
                JSONArray innerJsonArrayCont = ingred.getJSONArray("contains");
                String[] containz = new String[innerJsonArrayCont.length()];
                for(int l = 0; l<containz.length;l++){
                    JSONObject newJs = innerJsonArrayCont.getJSONObject(l);
                    containz[l] = newJs.getString("ing");
                    System.out.println(containz[l]);
                }
                /*  JSONArray innerJsonArrayPort = ingred.getJSONArray("portions");
                String[] portionz = new String[innerJsonArrayPort.length()];
                System.out.println(innerJsonArrayPort.length());
                for(int k = 0; k<portionz.length;k++){
                    System.out.println(innerJsonArrayPort.length());
                    JSONObject newJZ = innerJsonArrayPort.getJSONObject(k);
                    portionz[k] = newJZ.getString("por");
                    System.out.println(portionz[k]);
                }*/
                r.setName(jsonObject.getString("name"));
                r.setDescription(jsonObject.getString("description"));
                r.setContains(containz);
                rList.add(r);
            }
        }catch(JSONException jse){
            jse.printStackTrace();
        }
        return rList;
    }


}
