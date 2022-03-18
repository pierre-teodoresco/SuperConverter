package fr.pteodoresco.superconverter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class ConverterGeoplugin extends Converter {

    @Override
    public void init(Context context) {
        String url = "http://geoplugin.net/json.gp?base_currency=USD";
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
            response -> {
                try {
                    this.rate = (float) response.getDouble("geoplugin_currencyConverter");
                    if (this.listener != null) {
                        this.listener.update(this.rate);
                    }
                    Log.d("CURRENCY", String.valueOf(this.rate));
                } catch (JSONException e) {
                    Log.d("CURRENCY", "ERROR " + e.getMessage());
                }
            }
            , error -> {
                Log.d("CURRENCY", "Error !");
                Log.d("CURRENCY", error.toString());
            }
        );
        queue.add(jsonObjectRequest);
    }
}
