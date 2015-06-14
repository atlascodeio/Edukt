package ve.ula.edukt_mobile;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ve.ula.edukt_mobile.adapter.FeedListAdapter;
import ve.ula.edukt_mobile.app.AppController;
import ve.ula.edukt_mobile.data.FeedItem;
import ve.ula.edukt_mobile.library.SwipeRefresh;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.ListView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimeLineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimeLineFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<FeedItem> feedItems;
    //private String URL_FEED = "http://api.androidhive.info/feed/feed.json";
    private String URL_FEED;

    private SwipeRefresh swipeRefresh;
    //For handle the circle progress bar showing
    private CircleProgressBar circle_progress_bar;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimeLineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimeLineFragment newInstance(String param1, String param2) {
        TimeLineFragment fragment = new TimeLineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TimeLineFragment() {
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
        URL_FEED =   getActivity().getString(R.string.url_json_gettimeline);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_line, container, false);
        listView = (ListView) view.findViewById(R.id.list);
        feedItems = new ArrayList<FeedItem>();
        listAdapter = new FeedListAdapter(getActivity(), feedItems, "Timeline");
        listView.setAdapter(listAdapter);

        //Prepare the swipe refresh action
        try {
            swipeRefresh = new SwipeRefresh(view, (Object) this, TimeLineFragment.class.getMethod("fetchData"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //Define the progress bar
        circle_progress_bar = (CircleProgressBar) view.findViewById(R.id.circle_progress_bar);
        circle_progress_bar.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);


        // We first check for cached request
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Entry entry = cache.get(URL_FEED);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonFeed(new JSONObject(data));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {

            //show circle progress bar
            circle_progress_bar.setVisibility(View.VISIBLE);
            //execute remote request
            fetchData();
        }

        return view;
    }


    //Http request in Json Format
    public void fetchData(){
        // making fresh volley request and getting json
        JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
                URL_FEED, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(TAG, "Response: " + response.toString());
                if (response != null) {
                    swipeRefresh.refreshHide();
                    circle_progress_bar.setVisibility(View.INVISIBLE);
                    parseJsonFeed(response);

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipeRefresh.refreshHide();
                circle_progress_bar.setVisibility(View.INVISIBLE);
            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(jsonReq);

    }


    /**
     * Parsing json reponse and passing the data to feed view list adapter
     * */
    private void parseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                FeedItem item = new FeedItem();
                item.setId(feedObj.getInt("id"));
                item.setName(feedObj.getString("name"));

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj
                        .getString("image");
                item.setImge(image);
                item.setStatus(feedObj.getString("status"));
                item.setProfilePic(feedObj.getString("profilePic"));
                item.setTimeStamp(feedObj.getString("timeStamp"));

                // url might be null sometimes
                String feedUrl = feedObj.isNull("url") ? null : feedObj
                        .getString("url");
                item.setUrl(feedUrl);

                // nombre might be null sometimes
                String nombre = feedObj.isNull("nombre") ? null : feedObj
                        .getString("nombre");
                item.setNombre(nombre);

                feedItems.add(item);
            }

            // notify data changes to list adapater
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }





}
