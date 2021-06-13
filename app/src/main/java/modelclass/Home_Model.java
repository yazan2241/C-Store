package modelclass;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Home_Model {
int productId;
    String imageshose;
    String price,desc;
    public String getImageshose() {
        return imageshose;
    }

    public void setImageshose(String imageshose) {
        this.imageshose = imageshose;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String discount) {
        this.price = discount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String text1) {
        this.desc = text1;
    }
    public void setProductId(int productId){this.productId=productId;}
    public int getProductId(){return productId;}

    public Home_Model(int productId,String imageshose,String price,  String text1) {
        this.productId=productId;
        this.imageshose = imageshose;
        this.price=price;
        this.desc = text1;
    }
    public Home_Model(String imageshose,String price,  String text1) {

        this.imageshose = imageshose;
        this.price=price;
        this.desc = text1;
    }



}
