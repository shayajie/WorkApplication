package door.manage.com.adapter;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import door.manage.com.R;
import test.greendao.bean.Door;
import test.greendao.bean.User;

public class GridView_Adapter extends BaseAdapter {
	private List<Door> doors;
	private List<User> users;
	private Context mContext;
//	private View addview;
	private LayoutInflater mInflater;
	private String flag = "";
	public GridView_Adapter(List<Door> doors, Context mContext) {
		super();
		this.doors = doors;
		this.mContext = mContext;
		this.flag = "door";
//		this.addview = addview;
		this.mInflater = LayoutInflater.from(mContext);
	}
	public GridView_Adapter(Context mContext,List<User> users) {
		super();
		this.users = users;
		this.mContext = mContext;
		this.flag = "user";
//		this.addview = addview;
		this.mInflater = LayoutInflater.from(mContext);
	}
	@Override
	public int getCount() {
		if("door".equals(flag)){
			return doors.size()+1;
		}else if ("user".equals(flag)) {
			return users.size()+1;
		}
		return 0;
		
	}
	
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if("door".equals(flag)){
			return doors.get(position);
		}else if ("user".equals(flag)) {
			return users.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.gridview_item_layout, parent, false);
			viewHolder.door = (LinearLayout) convertView.findViewById(R.id.door_linearLayout);
			viewHolder.add = (LinearLayout) convertView.findViewById(R.id.add_linearLayout);
			viewHolder.door_img = (ImageView) convertView.findViewById(R.id.door_imageview);
			viewHolder.user_img = (ImageView) convertView.findViewById(R.id.user_imageview);
			viewHolder.doorname = (TextView) convertView.findViewById(R.id.door_name_textview);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if("door".equals(flag)){
			viewHolder.door_img.setVisibility(View.VISIBLE);
			viewHolder.user_img.setVisibility(View.GONE);
			
			if(position==doors.size()){
				viewHolder.door.setVisibility(View.GONE);
				viewHolder.add.setVisibility(View.VISIBLE);
			}else {
				viewHolder.door.setVisibility(View.VISIBLE);
				viewHolder.add.setVisibility(View.GONE);
					viewHolder.doorname.setText(doors.get(position).getDoorname());
			}
			
		}else if ("user".equals(flag)) {
			viewHolder.door_img.setVisibility(View.GONE);
			viewHolder.user_img.setVisibility(View.VISIBLE);
			if(position==users.size()){
				viewHolder.door.setVisibility(View.GONE);
				viewHolder.add.setVisibility(View.VISIBLE);
			}else {
				viewHolder.door.setVisibility(View.VISIBLE);
				viewHolder.add.setVisibility(View.GONE);
					viewHolder.doorname.setText(users.get(position).getName());
			}
		}
		
		
		return convertView;
	}
	class ViewHolder{
		TextView doorname;
		ImageView door_img,user_img;
		LinearLayout door,add;
	}
}
