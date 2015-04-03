package com.tianye.mobile.well.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tianye.mobile.well.R;
import com.tianye.mobile.well.core.NavigationDrawerObject;

import java.util.ArrayList;
import java.util.List;

import static com.tianye.mobile.well.core.NavigationDrawerObject.TYPE_ITEM_MENU;
import static com.tianye.mobile.well.core.NavigationDrawerObject.TYPE_ITEM_ORG;
import static com.tianye.mobile.well.core.NavigationDrawerObject.TYPE_SUBHEADER;

/**
 * Created by lenovo on 2015/3/19.
 */
public class NavigationDrawerAdapter extends BaseAdapter {

    private Context context;
    private List<NavigationDrawerObject> data ;
    private LayoutInflater mLayoutInflater;

    public NavigationDrawerAdapter(Context context){
        this.context = context;
        //this.mLayoutInflater=LayoutInflater.from(context);
        this.mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        createData();
    }

    private void createData() {
        String [] names =context.getResources().getStringArray(R.array.navigation_drawer_name_list);

        //String [] icons = context.getResources().getStringArray(R.array.navigation_drawer_icon_list);
        String[] icons=new String[]{String.valueOf(R.drawable.inspection_not_ok),
                String.valueOf(R.drawable.inspection_not_ok),String.valueOf(R.drawable.inspection_not_ok),
                String.valueOf(R.drawable.inspection_not_ok),String.valueOf(R.drawable.inspection_not_ok),
                String.valueOf(R.drawable.inspection_not_ok),String.valueOf(R.drawable.inspection_not_ok),
        String.valueOf(R.drawable.inspection_not_ok),String.valueOf(R.drawable.inspection_not_ok)};

        data = new ArrayList<>();
        int amount = names.length ;
        for(int i =0;i<amount; i++){
            if(i<names.length){
                data.add(new NavigationDrawerObject(names[i],icons[i],TYPE_ITEM_MENU));
            }
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public NavigationDrawerObject getItem(int position) {
       return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final NavigationDrawerObject obj = data.get(position);
        if(convertView == null || obj.getType() !=((ViewHolder)convertView.getTag()).type){
            viewHolder = new ViewHolder();
            switch (obj.getType()){
                case TYPE_ITEM_MENU:
                    convertView =mLayoutInflater.inflate(R.layout.navigation_drawer_list_item_text,parent,false);
                    viewHolder.name=(TextView)convertView.findViewById(R.id.navigation_drawer_item_name);
                    //viewHolder.iconString = (TextView)convertView.findViewById(R.id.navigation_drawer_item_text_icon);
                    viewHolder.iconDrawable = (ImageView)convertView.findViewById(R.id.navigation_drawer_item_icon);
                    break;
                case TYPE_ITEM_ORG:
                    break;
                case TYPE_SUBHEADER:
                    break;
                default:
                    break;
            }
            viewHolder.type = obj.getType();
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        switch (obj.getType()){
            case TYPE_ITEM_MENU:
                //Typeface font = Typeface.createFromAsset(context.getAssets(),"octions.ttf");
//                Typeface font = Typeface.DEFAULT;
//                viewHolder.iconString.setTypeface(font);
                //viewHolder.iconString.setText(obj.getIconString());
                viewHolder.name.setText(obj.getTitle());
                viewHolder.iconDrawable.setImageResource(Integer.parseInt(obj.getIconString()));
                viewHolder.iconDrawable.setBackgroundResource(Integer.parseInt(obj.getIconString()));
                break;
        }
        return convertView;
    }


    private class ViewHolder{
        int type;
        TextView name;
        TextView iconString;
        ImageView iconDrawable;

    }
}
