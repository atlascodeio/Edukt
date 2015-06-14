package ve.ula.edukt_mobile;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ve.ula.edukt_mobile.app.AppController;
import ve.ula.edukt_mobile.library.DatabaseHandler;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAccountFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String TAG = MainActivity.class.getSimpleName();
    private String URL_FEED;
    //For handle the circle progress bar showing
    private CircleProgressBar circle_progress_bar;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyAccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyAccountFragment newInstance(String param1, String param2) {
        MyAccountFragment fragment = new MyAccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MyAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //Url to get json response
        URL_FEED =   getActivity().getString(R.string.url_json_getmyaccount);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_my_account, container, false);

        //Define the progress bar
        circle_progress_bar = (CircleProgressBar) view.findViewById(R.id.circle_progress_bar);
        circle_progress_bar.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

        //Search the loggeed user in sqlite database
        DatabaseHandler db = new DatabaseHandler(getActivity().getBaseContext());
        HashMap user = db.getUserDetails();
        final String user_uid = (String) user.get("uid");

        //prepare the json object for send uid
        JSONObject json= new JSONObject();
        try {
            json.put("id", user_uid);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // We first check for cached request
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Entry entry = cache.get(URL_FEED);
        if (entry != null && !(entry.isExpired())) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonFeed(new JSONObject(data), view);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {

            //show circle progress bar
            circle_progress_bar.setVisibility(View.VISIBLE);
            // making fresh volley request and getting json
            JsonObjectRequest jsonReq = new JsonObjectRequest(Method.POST,
                    URL_FEED, json, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        circle_progress_bar.setVisibility(View.INVISIBLE);
                        parseJsonFeed(response, view);

                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    circle_progress_bar.setVisibility(View.INVISIBLE);
                }
            });

            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(jsonReq);
        }

        return view;
    }

    /**
     * Parsing json reponse and passing the data to feed view list adapter
     * */
    private void parseJsonFeed(JSONObject response, View view) {
        try {

            TextView name = (TextView) view.findViewById(R.id.name);
            name.setText(response.getString("name"));
            TextView email = (TextView) view.findViewById(R.id.email);
            email.setText(response.getString("email"));
            TextView cedula = (TextView) view.findViewById(R.id.cedula);
            cedula.setText(response.getString("cedula"));
            TextView universidad = (TextView) view.findViewById(R.id.universidad);
            universidad.setText(response.getString("status"));

            // user profile pic
            ImageLoader imageLoader = AppController.getInstance().getImageLoader();
            NetworkImageView profilePic = (NetworkImageView) view.findViewById(R.id.profilePic);
             profilePic.setDefaultImageResId(R.drawable.profile);
            profilePic.setImageUrl(response.getString("profilePic"), imageLoader);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
