package com.mridul.managesmartbin;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.mridul.managesmartbin.BackgroundWorker.ACCOUNT_INFO_json_EMAIL;
import static com.mridul.managesmartbin.BackgroundWorker.ACCOUNT_INFO_json_MOB_NO;
import static com.mridul.managesmartbin.BackgroundWorker.ACCOUNT_INFO_json_NAME;


public class FragmentAccountInfo extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account_info, container, false);
        AfterLogin1.toolbar.setTitle("Account Info");

        ListView listView = (ListView)v.findViewById(R.id.listview_accountInfo1);

        HashMap<String, String> hashMap = new HashMap<>();


        hashMap.put("E-mail :", ACCOUNT_INFO_json_EMAIL);
        hashMap.put("Mobile No. :", ACCOUNT_INFO_json_MOB_NO);
        hashMap.put("Name :", ACCOUNT_INFO_json_NAME);

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(getContext(), listItems,R.layout.list_item_account_info, new String[]{"First Line","Second Line"}, new int[]{R.id.item,R.id.sub_item});

        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()){
            HashMap<String , String> h = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            h.put("First Line",pair.getKey().toString());
            h.put("Second Line", pair.getValue().toString());
            listItems.add(h);
        }

        listView.setAdapter(adapter);

        return v;
    }

}
