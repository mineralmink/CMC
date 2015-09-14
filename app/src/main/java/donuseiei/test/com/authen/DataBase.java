package donuseiei.test.com.authen;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Donus Eiei on 9/12/2015.
 */
public class DataBase {

    public static void get(RequestParams params) {
        // Show Progress Dialog
       // prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.google.com", params, new AsyncHttpResponseHandler() {
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String response = "";
                for(int index = 0; index< bytes.length ; index++) {
                    response += (char) bytes[index];
                }
                try {
                    // JSON Object
                    JSONObject obj = new JSONObject(response);
                    Log.i("DataBase", obj.toString());
                    // When the JSON response has status boolean value assigned with true
                    if (obj.getBoolean("status")) {
                        //Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
                        // Navigate to Home screen
                        //navigatetoHomeActivity();
                    }
                    // Else display error message
                    else {
                        //errorMsg.setText(obj.getString("error_msg"));
                        //Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    //Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }
        });
    }
    public static void post(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
