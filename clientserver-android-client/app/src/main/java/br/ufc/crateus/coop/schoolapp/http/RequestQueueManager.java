package br.ufc.crateus.coop.schoolapp.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by andremeireles on 29/06/16.
 */
public class RequestQueueManager {

    private static RequestQueueManager instance;
    private RequestQueue requestQueue;
    private static Context appContext;

    private RequestQueueManager(Context context){
        appContext = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized RequestQueueManager getInstance(Context context){
        if(instance == null){
            instance = new RequestQueueManager(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(appContext.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
