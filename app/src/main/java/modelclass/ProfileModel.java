package modelclass;

import android.widget.LinearLayout;

/**
 * Created by wolfsoft4 on 20/8/18.
 */

public class ProfileModel {

    String txtmyreceived,txtn1;

    public String getTxtmyreceived() {
        return txtmyreceived;
    }

    public void setTxtmyreceived(String txtmyreceived) {
        this.txtmyreceived = txtmyreceived;
    }

    public String getTxtn1() {
        return txtn1;
    }

    public void setTxtn1(String txtn1) {
        this.txtn1 = txtn1;
    }

    public ProfileModel(String txtmyreceived, String txtn1) {
        this.txtmyreceived = txtmyreceived;
        this.txtn1 = txtn1;
    }
}
