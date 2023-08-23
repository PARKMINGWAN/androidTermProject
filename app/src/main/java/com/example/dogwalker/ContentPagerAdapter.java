package com.example.dogwalker;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ContentPagerAdapter extends FragmentStateAdapter {
    private int mPageCount = 4;
    public ContentPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                Tab1Fragment tab1Fragment = new Tab1Fragment();
                return tab1Fragment;

            case 1:

                Tab2Fragment tab2Fragment = new Tab2Fragment();
                return tab2Fragment;

            case 2:
                TabWalkerProfileFragment tabWalkerProfileFragment = new TabWalkerProfileFragment();
                return tabWalkerProfileFragment;
            case 3 :
                Tab4Fragment tab4Fragment = new Tab4Fragment();
                return  tab4Fragment;

            default : return null;

        }

    }

    @Override
    public int getItemCount() {
        return mPageCount;
    }
}
