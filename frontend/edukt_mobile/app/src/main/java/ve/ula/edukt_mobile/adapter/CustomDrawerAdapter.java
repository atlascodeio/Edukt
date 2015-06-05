package ve.ula.edukt_mobile.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import ve.ula.edukt_mobile.R;
import ve.ula.edukt_mobile.app.AppController;
import ve.ula.edukt_mobile.data.DrawerItem;
import ve.ula.edukt_mobile.data.FeedItem;

public class CustomDrawerAdapter extends BaseAdapter {
	private Activity activity;

	private Fragment fragment;


	private LayoutInflater inflater;
	private List<DrawerItem> drawerItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	private String modulo;

	public CustomDrawerAdapter(Activity activity, List<DrawerItem> drawerItems) {
		this.activity = activity;
		this.drawerItems = drawerItems;
		this.modulo = modulo;

	}

	@Override
	public int getCount() {
		return drawerItems.size();
	}

	@Override
	public Object getItem(int location) {
		return drawerItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.drawer_item, null);

		TextView icon = (TextView) convertView.findViewById(R.id.icon);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        View view = (View) convertView.findViewById(R.id.divider);

		DrawerItem item = drawerItems.get(position);

		icon.setText(item.getIcon());//f03a
        icon.setTypeface(Typeface.createFromAsset(parent.getContext().getAssets(), "octicons/octicons.ttf"));
        name.setText(item.getName());

        //Drawer Divider
        view.setClickable(false);
        view.setEnabled(false);
        view.setMinimumHeight(1);
        if(position != 4){
            view.setVisibility(View.GONE);
        }


        return convertView;
	}

}
