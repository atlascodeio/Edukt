
package ve.ula.edukt_mobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import ve.ula.edukt_mobile.adapter.FeedListAdapter;
import ve.ula.edukt_mobile.app.AppController;
import ve.ula.edukt_mobile.data.FeedItem;
import ve.ula.edukt_mobile.data.Universidad;
import ve.ula.edukt_mobile.library.DatabaseHandler;
import ve.ula.edukt_mobile.library.UserFunctions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends Activity {
	Button btnRegister;
	Button btnLinkToLogin;
	EditText inputFullName;
	EditText inputEmail;
	EditText inputPassword;
	TextView registerErrorMsg;
	EditText inputCedula;
	Spinner inputSpinner;
	
	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";


	private static final String TAG = MainActivity.class.getSimpleName();
	private ListView listView;
	private FeedListAdapter listAdapter;
	private List<FeedItem> feedItems;
	private String URL_FEED;
	private Universidad universidad_selected;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

        //Add to prevent the emulator localhost main threat error
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

		// Importing all assets like buttons, text fields
		inputFullName = (EditText) findViewById(R.id.registerName);
		inputEmail = (EditText) findViewById(R.id.registerEmail);
		inputPassword = (EditText) findViewById(R.id.registerPassword);
		inputCedula = (EditText) findViewById(R.id.registerCedula);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
		registerErrorMsg = (TextView) findViewById(R.id.register_error);

		inputSpinner = (Spinner) findViewById(R.id.registerUniversidad);
		//Obtengo las universidades de la BD
		final ProgressDialog pDialog = new ProgressDialog(this);
		pDialog.setMessage(getString(R.string.progress_dialog));
		pDialog.show();
		//Url to get json response
		URL_FEED =   getString(R.string.url_json_getuniversidades);
		// making fresh volley request and getting json
		JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
				URL_FEED, null, new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				VolleyLog.d(TAG, "Response: " + response.toString());
				if (response != null) {
					pDialog.hide();
					parseJsonFeed(response, inputSpinner);

				}
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				pDialog.hide();
			}
		});

		// Adding request to volley request queue
		AppController.getInstance().addToRequestQueue(jsonReq);

		// Register Button Click event
		btnRegister.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View view) {
				String name = inputFullName.getText().toString();
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				String cedula = inputCedula.getText().toString();
				//String spinner = Long.toString(inputSpinner.getSelectedItemId());
				String spinner = Integer.toString(universidad_selected.getId());
				UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.registerUser(name, email, password, cedula, spinner);
				
				// check for login response
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						registerErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS); 
						if(Integer.parseInt(res) == 1){
							// user successfully registred
							// Store user details in SQLite Database
							DatabaseHandler db = new DatabaseHandler(getApplicationContext());
							JSONObject json_user = json.getJSONObject("user");
							
							// Clear all previous data in database
							userFunction.logoutUser(getApplicationContext());
							db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));						
							// Launch Dashboard Screen
							Intent dashboard = new Intent(getApplicationContext(), DashboardActivity.class);
							// Close all views before launching Dashboard
							dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(dashboard);
							// Close Registration Screen
							finish();
						}else{
							// Error in registration
							registerErrorMsg.setText("Error occured in registration");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

		// Link to Login Screen
		btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(i);
				// Close Registration View
				finish();
			}
		});
	}



	/**
	 * Parsing json reponse and passing the data to feed view list adapter
	 * */
	private void parseJsonFeed(JSONObject response, Spinner inputSpinner) {
		try {
			JSONArray feedArray = response.getJSONArray("feed");
			List<Universidad> list = new ArrayList<Universidad>();
			for (int i = 0; i < feedArray.length(); i++) {
				JSONObject feedObj = (JSONObject) feedArray.get(i);
				Universidad universidad = new Universidad(feedObj.getInt("id"), feedObj.getString("nombre"));
				list.add(universidad);
			}

			ArrayAdapter<Universidad> dataAdapter = new ArrayAdapter<Universidad>(this,
					android.R.layout.simple_spinner_item, list);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			//on item select listener
			inputSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
					universidad_selected = (Universidad) parent.getItemAtPosition(pos);

				}

				public void onNothingSelected(AdapterView<?> parent) {
				}
			});
			inputSpinner.setAdapter(dataAdapter);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
