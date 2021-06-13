package adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.cStore.shop.Price;

/**
 * Created by wolfsoft4 on 18/8/18.
 */

public class ProductDetailPagerAdapter extends FragmentStatePagerAdapter {
    int mnooftabs;
String price,name,seller,pic1,pic2,pic3,pic4;
String quan,cat;
    public ProductDetailPagerAdapter(FragmentManager fm, int tabCount,String price,String name,String seller,String pic1,String pic2,String pic3,String pic4,String quan,String cat) {
        super(fm);
        this.mnooftabs = tabCount;
        this.name=name;
        this.price=price;
        this.seller=seller;
        this.pic2=pic2;
        this.pic3=pic3;
        this.pic1=pic1;
        this.pic4=pic4;
        this.quan=quan;
        this.cat=cat;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Price tab1=new Price(name,price,seller,pic1,pic2,pic3,pic4,quan,cat);
                return tab1;
            case 1:
                Price tab2=new Price(name,price,seller,pic1,pic2,pic3,pic4,quan,cat);
                return tab2;
            case 2:
                Price tab3=new Price(name,price,seller,pic1,pic2,pic3,pic4,quan,cat);
                return tab3;
            case 3:
                Price tab4=new Price(name,price,seller,pic1,pic2,pic3,pic4,quan,cat);
                return tab4;
            case 4:
                Price tab5=new Price(name,price,seller,pic1,pic2,pic3,pic4,quan,cat);
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
