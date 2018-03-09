package com.gehostingv2.gesostingv2iptvbilling.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gehostingv2.gesostingv2iptvbilling.R;
import com.gehostingv2.gesostingv2iptvbilling.view.fragment.ParentalControlLiveCatFragment;
import com.gehostingv2.gesostingv2iptvbilling.view.fragment.ParentalControlSettingFragment;
import com.gehostingv2.gesostingv2iptvbilling.view.fragment.ParentalControlVODCatFragment;

import java.util.ArrayList;

public class ParentalControlPagerAdapter extends FragmentStatePagerAdapter {
    private String tabTitles[] = new String[3];
    int mNumOfTabs = 3;
    private Context context;
    private ArrayList<Integer> tabServicesTotalCount = new ArrayList<>();
    boolean flag = false;

    public ParentalControlPagerAdapter(FragmentManager fm, int NumOfTabs, Context myContext, ArrayList<Integer> tabServicesTotalCount) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.context = myContext;
        this.tabServicesTotalCount = tabServicesTotalCount;
        this.tabTitles[0]= context.getResources().getString(R.string.live_categories);
        this.tabTitles[1]= context.getResources().getString(R.string.vod_categories);
        this.tabTitles[2]= context.getResources().getString(R.string.settings);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ParentalControlLiveCatFragment parentalControlLiveCatFragment = new ParentalControlLiveCatFragment();
                return parentalControlLiveCatFragment;
            case 1:
                ParentalControlVODCatFragment parentalControlVODCatFragment = new ParentalControlVODCatFragment();
                return parentalControlVODCatFragment;
            case 2:
                ParentalControlSettingFragment parentalControlSettingFragment = new ParentalControlSettingFragment();
                return parentalControlSettingFragment;
            default:
                return null;
        }
    }

    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(context).inflate(R.layout.tablayout_invoices, null);
        TextView tv = (TextView) v.findViewById(R.id.tv_tab_service_name);
        tv.setText(tabTitles[position]);
        return v;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    public void selectLiveCatTabView(View view, Typeface fontOPenSansBold, int position) {
        if (view != null) {
            TextView serviceName = (TextView) view.findViewById(R.id.tv_tab_service_name);
            serviceName.setTypeface(fontOPenSansBold);
            serviceName.setTextColor(Color.parseColor("#ffffff"));   // 0cdc78
        }

    }

    public void selectVODCatTabView(View view, Typeface fontOPenSansBold, int position) {
        if (view != null) {
            TextView serviceName = (TextView) view.findViewById(R.id.tv_tab_service_name);
            serviceName.setTypeface(fontOPenSansBold);
            serviceName.setTextColor(Color.parseColor("#ffffff"));   // 0cdc78
        }

    }

    public void selectSettingsTabView(View view, Typeface fontOPenSansBold) {
        if (view != null) {
            TextView serviceName = (TextView) view.findViewById(R.id.tv_tab_service_name);
            serviceName.setTypeface(fontOPenSansBold);
            serviceName.setTextColor(Color.parseColor("#ffffff"));    //ff6f43

        }
    }

    public void unSelectLiveCatTabView(View view, Typeface fontOPenSansRegular) {
        if (view != null) {
            TextView serviceName = (TextView) view.findViewById(R.id.tv_tab_service_name);
            serviceName.setTypeface(fontOPenSansRegular);
            serviceName.setTextColor(Color.parseColor("#000000"));
        }
    }

    public void unSelectVODCatTabView(View view, Typeface fontOPenSansRegular) {
        if (view != null) {
            TextView serviceName = (TextView) view.findViewById(R.id.tv_tab_service_name);
            serviceName.setTypeface(fontOPenSansRegular);
            serviceName.setTextColor(Color.parseColor("#000000"));
        }
    }

    public void unSelectSettingsTabView(View view, Typeface fontOPenSansRegular) {
        if (view != null) {
            TextView serviceName = (TextView) view.findViewById(R.id.tv_tab_service_name);
            serviceName.setTypeface(fontOPenSansRegular);
            serviceName.setTextColor(Color.parseColor("#000000"));
        }
    }

    public void setDefaultOpningViewTab(View viewDefaultOPeningTab, Typeface font) {
        if (viewDefaultOPeningTab != null) {
            TextView serviceName = (TextView) viewDefaultOPeningTab.findViewById(R.id.tv_tab_service_name);
            serviceName.setTypeface(font);
            serviceName.setTextColor(Color.parseColor("#ffffff"));
        }
    }

    public void setSecondViewTab(View viewSecondTab, Typeface fontOPenSansBold) {
        if(viewSecondTab!=null) {
            TextView serviceName = (TextView) viewSecondTab.findViewById(R.id.tv_tab_service_name);
            serviceName.setTypeface(fontOPenSansBold);
            serviceName.setTextColor(Color.parseColor("#000000"));    //ff6f43
        }
    }
}