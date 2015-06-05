package ve.ula.edukt_mobile.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import ve.ula.edukt_mobile.FeedImageView;
import ve.ula.edukt_mobile.R;
import ve.ula.edukt_mobile.app.AppController;
import ve.ula.edukt_mobile.data.FeedItem;

public class FeedListAdapter extends BaseAdapter {
	private Activity activity;

	private Fragment fragment;


	private LayoutInflater inflater;
	private List<FeedItem> feedItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	private String modulo;

	public FeedListAdapter(Activity activity, List<FeedItem> feedItems, String modulo) {
		this.activity = activity;
		this.feedItems = feedItems;
		this.modulo = modulo;

	}







	@Override
	public int getCount() {
		return feedItems.size();
	}

	@Override
	public Object getItem(int location) {
		return feedItems.get(location);
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
			convertView = inflater.inflate(R.layout.feed_item, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();

		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView timestamp = (TextView) convertView.findViewById(R.id.timestamp);
		TextView statusMsg = (TextView) convertView.findViewById(R.id.txtStatusMsg);
		TextView url = (TextView) convertView.findViewById(R.id.txtUrl);
		TextView other1 = (TextView) convertView.findViewById(R.id.other1);
		NetworkImageView profilePic = (NetworkImageView) convertView.findViewById(R.id.profilePic);
		//FeedImageView feedImageView = (FeedImageView) convertView.findViewById(R.id.feedImage1);

		FeedItem item = feedItems.get(position);

		name.setText(item.getName());

		// Converting timestamp into x ago format00
		if(item.getTimeStamp() != null) {
			CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
					Long.parseLong(item.getTimeStamp()),
					System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
			timestamp.setText(timeAgo);
		} else {
			timestamp.setVisibility(View.GONE);
		}
		// Chcek for empty status message
		if (!TextUtils.isEmpty(item.getStatus())) {
			statusMsg.setText(item.getStatus());
			statusMsg.setVisibility(View.VISIBLE);
		} else {
			// status is empty, remove from view
			statusMsg.setVisibility(View.GONE);
		}

		// Checking for null feed url
		if (item.getUrl() != null) {
			url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
					+ item.getUrl() + "</a> "));

			// Making url clickable
			url.setMovementMethod(LinkMovementMethod.getInstance());
			url.setVisibility(View.VISIBLE);
		} else {
			// url is null, remove from the view
			url.setVisibility(View.GONE);
		}

		// user profile pic
		profilePic.setImageUrl(item.getProfilePic(), imageLoader);

		//If modulo is not teachers then remove the other1 textview
		if(modulo != "teachers"){
			other1.setVisibility(View.GONE);
		} else {
			other1.setText(item.getEmail());
		}

		// Chcek for empty status message
		if (item.getNombre() != null) {
			url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
					+ item.getNombre() + "</a> "));
		}


		return convertView;
	}

}
