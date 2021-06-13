package adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import fragment.Shoes_Four;
import fragment.Shoes_One;
import fragment.Shoes_Three;
import fragment.Shoes_Two;

/**
 * Created by wolfsoft on 10/11/2015.
 */
public class ShoesPagerAdapter extends FragmentStatePagerAdapter {
String Pic1,Pic2,Pic3,Pic4;


    public ShoesPagerAdapter(FragmentManager fm,String pic1,String pic2,String pic3,String pic4) {
        super(fm);
this.Pic1=pic1;
this.Pic2=pic2;
this.Pic3=pic3;
this.Pic4=pic4;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Shoes_One tab1 = new Shoes_One(Pic1);
                return tab1;

            case 1:
                Shoes_Two tab6 = new Shoes_Two(Pic2);
                return tab6;


            case 2:
                Shoes_Three tab2 = new Shoes_Three(Pic3);
                return tab2;

            case 3:
                Shoes_Four tab3 = new Shoes_Four(Pic4);
                return tab3;



            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}