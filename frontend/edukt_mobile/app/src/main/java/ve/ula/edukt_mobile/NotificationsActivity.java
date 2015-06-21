package ve.ula.edukt_mobile;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ve.ula.edukt_mobile.app.AppController;
import ve.ula.edukt_mobile.library.DatabaseHandler;


public class NotificationsActivity extends ActionBarActivity {

    private EditText registerNotificationsNombre;
    private EditText registerNotificationsDescripcion;

    private static final String TAG = MainActivity.class.getSimpleName();
    private String URL_FEED;
    private TextView btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        //Url to get json response
        URL_FEED = getString(R.string.url_json_addnotifications);

        //Set the title toolbar
        TextView titletoolbar = (TextView) findViewById(R.id.titletoolbar);
        titletoolbar.setText(R.string.hint_notifications_bar_title);

        // Set a toolbar which will replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Hide actionbar title (substituted by toolbar)
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //pick the form edittext
        registerNotificationsNombre = (EditText) findViewById(R.id.registerNotificationsNombre);
        registerNotificationsDescripcion = (EditText) findViewById(R.id.registerNotificationsDescripcion);

        //onClick Listener button fab guardar
        btnGuardar = (TextView) findViewById(R.id.raised_button);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotifications();
            }
        });


    }


    public boolean addNotifications(){

        //Search the loggeed user in sqlite database
        DatabaseHandler db = new DatabaseHandler(this);
        HashMap user = db.getUserDetails();
        final String user_uid = (String) user.get("uid");

        //Disabled button to avoid multiple msg submit until web server respond
        btnGuardar.setEnabled(false);

        // making fresh volley request and getting json
        StringRequest stringReq = new StringRequest(Request.Method.POST,URL_FEED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                VolleyLog.d(TAG, "Response: " + response.toString());
                if (response != null) {
                    Toast.makeText(getApplicationContext(), getString(R.string.add_notifications_success), Toast.LENGTH_LONG).show();
                    //redirect to msg list fragment
                    finish();
                    //btnGuardar.setEnabled(true);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), getString(R.string.add_notifications_error), Toast.LENGTH_LONG).show();
                btnGuardar.setEnabled(true);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("id", user_uid);
                params.put("nombre", registerNotificationsNombre.getText().toString());
                params.put("descripcion", registerNotificationsDescripcion.getText().toString());
                return params;
            }
        };

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(stringReq);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
