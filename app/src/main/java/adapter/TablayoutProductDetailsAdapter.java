package adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import fragment.Details;

/**
 * Created by wolfsoft4 on 18/8/18.
 */

public class TablayoutProductDetailsAdapter extends FragmentStatePagerAdapter {
    int mnooftabs;

    public TablayoutProductDetailsAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.mnooftabs = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Details tab1=new Details();
                return tab1;
            case 1:
                Details tab2=new Details();
                return tab2;
            case 2:
                Details tab3=new Details();
                return tab3;
            case 3:
                Details tab4=new Details();
                return tab4;
            case 4:
                Details tab5=new Details();
                return tab5;


            default:
                 return null;
         }
    }

    @Override
    public int getCount() {
        return mnooftabs;
    }
}
