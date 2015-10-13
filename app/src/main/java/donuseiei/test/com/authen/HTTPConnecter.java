package donuseiei.test.com.authen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Pongpayak on 10/12/2015.
 */
public class HTTPConnecter {
    private static HTTPConnecter httpConnecter = new HTTPConnecter();

    private HTTPConnecter(){}

    public static HTTPConnecter getInstance(){
        return httpConnecter;
    }

    public static void get(RequestParams params,String url, final Fragment fragment) {
        // Show Progress Dialog
        // prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String response = "";
                for (int index = 0; index < bytes.length; index++) {
                    response += (char) bytes[index];
                }
                Log.i("res", response);
                //try {
                    //JSONObject json = new JSONObject(response);
                    //if(json.getBoolean("status")) {
                        Bundle bundle = new Bundle();
                        bundle.putString("data",response);
                        fragment.setArguments(bundle);
                    //}
                    /*else{

                    }*/

                /*} catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
        });
        // PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        // client.setCookieStore(myCookieStore);
    }
}
