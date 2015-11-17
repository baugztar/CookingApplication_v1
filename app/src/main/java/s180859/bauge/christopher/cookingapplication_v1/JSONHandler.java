package s180859.bauge.christopher.cookingapplication_v1;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Christopher on 17/11/2015.
 */
public class JSONHandler {

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


}
