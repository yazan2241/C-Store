package modelclass;

public class Home_Model_admin {

    String pic23;
    String name2,markaname2,comnumber2,mobilenumber2;
    public String getImageshose() {
        return pic23;
    }

    public void setImageshose(String pic23) {
        this.pic23 = pic23;
    }

    public String getPrice() {
        return name2;
    }

    public void setPrice(String discount) {
        this.name2 = discount;
    }

    public String getDesc() {
        return markaname2;
    }

    public void setDesc(String text4) {
        this.markaname2 = text4;
    }
    public String getDesc1() {
        return comnumber2;
    }

    public void setDesc1(String text2) {
        this.comnumber2 = text2;
    }
    public String getDesc2() {
        return mobilenumber2;
    }

    public void setDesc2(String text3) {
        this.mobilenumber2 = text3;
    }

    public Home_Model_admin(String pic23, String name2, String text4,String text2,String text3) {

        this.pic23 = pic23;
this.name2=name2;
        this.markaname2 = text4;
        this.comnumber2 = text2;
        this.mobilenumber2 = text3;
    }


}
