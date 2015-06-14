package ve.ula.edukt_mobile.library;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ve.ula.edukt_mobile.R;

/**
 * Created by realq on 13/06/15. to handle globally the swipe refresh layout actions
 */
public class SwipeRefresh {

    private SwipeRefreshLayout swipeContainer;

    public SwipeRefresh(View view, final Object object, final Method fetchData){
        //Define the swipe container for swipe refresh
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                try {
                    fetchData.invoke(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }


    public void refreshHide(){
        swipeContainer.setRefreshing(false);
    }




}
