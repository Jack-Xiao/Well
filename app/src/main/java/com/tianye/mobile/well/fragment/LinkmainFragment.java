package com.tianye.mobile.well.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tianye.mobile.well.App;
import com.tianye.mobile.well.R;
import com.tianye.mobile.well.core.LinkMan;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lenovo on 2015/4/1.
 */
public class LinkmainFragment extends BaseFragment {

    private List<LinkMan> data = new ArrayList<>();
    @InjectView(R.id.listview)
    ListView listView;
    LinkAdapter adapter = new LinkAdapter(getActivity());
    //private List<String> list;

    String[] PHONES_PROJECTION = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER, "sort_key"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //list = new ArrayList<String>();

        data = loadData();
        //loadData1();


//        if(list.size()>0){
//            ArrayAdapter adapter = new ArrayAdapter(getActivity(),simple_expandable_list_item_1,list);
//            listView.setAdapter(adapter);
//        }
        LinkMan l1=new LinkMan();
        l1.phone="123456";
        l1.name="张三";

        LinkMan l2= new LinkMan();
        l2.phone="654987";
        l2.name="李四";
        data.add(l1);
        data.add(l2);

    }

    private void loadData1() {
        int number = 0;
        String name = "";
        String strPhoneNumber = "";
        //ArrayList<LinkMan> data = new ArrayList<LinkMan>();
        ContentResolver cr = getActivity().getContentResolver();

        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);

        //Log.d("LinkMain", "..................");
        LinkMan linkMan;
        while (cursor.moveToNext()) {
            linkMan = new LinkMan();
            linkMan.name = cursor.getString(0);
            linkMan.phone = cursor.getString(1);
            //String i  = cursor.getString(1);

            //String phone1 = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            // int contactIdIndex;
            //      strPhoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            //name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
////            LinkMan man = new LinkMan();
//            name = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
//            Log.d("LinkMain", "name-------" + name);
//            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                    null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +"=" + contactId,null,null);
//            Log.d("LinkMain", "contactId-------" + contactId);
//            int i =0;
//            while(phone.moveToNext()){
//                strPhoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            }
            //contactIdIndex = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)));

            //man.name = String.valueOf(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));

//            Cursor c =  cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
//                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= " +contactId,null,null);
//            while(c.moveToNext()){
//
//                number = Integer.parseInt( c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
//                //name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//            }

            //man.phone = number;
            //list.add(linkMan.phone);
            // data.add(man);
            //phone.close();
        }
        cursor.close();
        // adapter.addData(data);

    }

    private ArrayList<LinkMan> loadData() {
        StringBuilder string = new StringBuilder();
        // data= new ArrayList<>();

        ArrayList<LinkMan> data = new ArrayList<LinkMan>();
        ContentResolver cr = getActivity().getContentResolver();
        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
        LinkMan man;
        while (cursor.moveToNext()) {
            man = new LinkMan();
            //man.id = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            man.name = String.valueOf(cursor.getString(0));
            man.phone = cursor.getString(1);
            data.add(man);
        }
        cursor.close();
        return data;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View contentView = inflater.inflate(R.layout.contact_fragment, null);
        ButterKnife.inject(this, contentView);

        LinkAdapter adapter = new LinkAdapter(App.getContext());
        if (data.size()>0) {
            listView.setAdapter(adapter);
        }
        //listView.setAdapter(adapter);
        //listView.setAdapter(adapter);
        //listView.setAdapter(new ArrayAdapter<>());

//        ContentResolver cr = getActivity().getContentResolver();
//        Cursor mCursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

//        SimpleCursorAdapter mAdapter ;
//        mAdapter = new SimpleCursorAdapter(TestSimpleCursorAdapter.this,
//                android.R.layout.simple_expandable_list_item_1, mCursor, new String[]{LinkMan}, new int[]{android.R.id.name});

        return contentView;
    }


    public class LinkAdapter extends BaseAdapter {
        private Context context;
        //private List<LinkMan> list;

        //public void addData(List<LinkMan> linkMan) {
//            list.addAll(linkMan);
//        }

        public LinkAdapter(Context context) {
            this.context = context;
            //list = new ArrayList<LinkMan>();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public LinkMan getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vHolder;
            if (convertView == null) {
                vHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.contact_item, null);
                vHolder.name = (TextView) convertView.findViewById(R.id.name);
                vHolder.number = (TextView) convertView.findViewById(R.id.number);
                convertView.setTag(vHolder);
            } else {
                vHolder = (ViewHolder) convertView.getTag();
            }
            vHolder.name.setText(getItem(position).name.toString());
            vHolder.number.setText(String.valueOf(getItem(position).phone));

            return convertView;
        }
    }

    class ViewHolder {
        TextView name;
        TextView number;
    }

}
