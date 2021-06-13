package com.cStore.shop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class upload_file extends AppCompatActivity {
    private LinearLayout buh;
    private LinearLayout chooseSize;
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap, first, second, third, fourth;
    private int pic1 = 0, pic2 = 0, pic3 = 0;
    int cc1, cc2, cc3;
    private Uri filePath,filePath1,filePath2,filePath3,filePath4;
    public static final String UPLOAD_URL = "https://c-store.000webhostapp.com/ImageUpload.php";
    public static final String UPLOAD_KEY = "image";

    EditText pName, pPrice, pNumber, pDesc;

    // send to database
    private int cat;
    String Pname, Pprice, Pnumber, Pdesc, Pcolor="", Psize="",Pcat;
    ImageButton imb1, imb2, imb3;
    ProgressDialog loading;


    private Button buttonChoose;
    private Button buttonUpload;
    int cnt = 0;
    private ImageView imageView;


    String emailStr;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);
        buh = findViewById(R.id.check1);
        chooseSize = findViewById(R.id.check2);
        pName = findViewById(R.id.ProductName);
        pPrice = findViewById(R.id.ProductPrice);
        pNumber = findViewById(R.id.NumberProduct);
        pDesc = findViewById(R.id.ProductDescription);


        imb1 = findViewById(R.id.firstImage);
        imb2 = findViewById(R.id.secondImage);
        ImageView uploded = findViewById(R.id.uplodedImage);
        imb3 = findViewById(R.id.thirdImage);
        init();
    }

    public void checkbox(View view) {
        buh.setVisibility(View.VISIBLE);

        switch (view.getId()) {
            case R.id.dresss:
                cat = 2;
                break;
            case R.id.ties:
                cat = 6;
                break;
            case R.id.trouser:
                cat = 4;
                break;
            case R.id.taxido:
                cat = 1;
                break;
            case R.id.shirts:
                cat = 3;
                break;
            case R.id.shoess:
                cat = 7;
            case R.id.jacket:
                cat = 5;
                break;

        }

    }

    public void chooseSize(View view) {
        chooseSize.setVisibility(View.VISIBLE);
    }

    public void uploadImageProduct(View view) {
        if (cnt <= 4) {
          //  if (first != null) imb1.setImageBitmap(second);
         //   if (second != null) imb2.setImageBitmap(third);
        //    if (third != null) imb3.setImageBitmap(fourth);
            cnt++;
            showFileChooser();

        } else {
            Toast.makeText(getApplicationContext(), "you Uploaded too much picture .. delete one to upload more", Toast.LENGTH_SHORT).show();
        }

    }

    void init() {
        buttonChoose = findViewById(R.id.uploadProductImage);
        buttonUpload = findViewById(R.id.button4);
        imageView = findViewById(R.id.uplodedImage);
        sharedPreferences=getSharedPreferences("sharedPref",Context.MODE_PRIVATE);
        emailStr=sharedPreferences.getString("email",null);

    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                    filePath = data.getData();

                    try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                      //  saveBitmapToFile(bitmap);
                if (cnt == 1) {
                    first = bitmap;
                    filePath1=filePath;
                    imageView.setImageBitmap(first);
                    System.out.println("fffff"+first);
                }
                if (cnt == 2) {
                    filePath2=filePath1;
                    filePath1=filePath;
                    second = first;
                    first = bitmap;
                    imageView.setImageBitmap(first);
                    imb1.setImageBitmap(second);
                    System.out.println("fffff"+second);
                }
                if (cnt == 3) {
                    filePath3=filePath2;
                    filePath2=filePath1;
                    filePath1=filePath;
                    third = second;
                    second = first;
                    first = bitmap;
                    imageView.setImageBitmap(first);
                    imb1.setImageBitmap(second);
                    imb2.setImageBitmap(third);
                    System.out.println("fffff"+third);
                }
                if (cnt == 4) {
                    filePath4=filePath3;
                    filePath3=filePath2;
                    filePath2=filePath1;
                    filePath1=filePath;
                    fourth = third;
                    third = second;
                    second = first;
                    first = bitmap;
                    imageView.setImageBitmap(first);
                    imb1.setImageBitmap(second);
                    imb2.setImageBitmap(third);
                    imb3.setImageBitmap(fourth);
                    System.out.println("fffff"+fourth);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
      //  bmp = Bitmap.createScaledBitmap(bmp, 200, 200, false);
      //  bmp.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void deleteImage(View view) {
        cnt--;
        first = null;
        imageView.setImageBitmap(first);
    }


    public void changeFirstPic(View view) {
        ImageButton im = findViewById(R.id.firstImage);
        ImageView im1 = findViewById(R.id.uplodedImage);
        Bitmap temp = first;
        Bitmap temp1 = second;
        first = temp1;
        second = temp;
        im.setImageBitmap(temp1);
        im1.setImageBitmap(temp);

    }


    public void changeSecondPic(View view) {
        ImageButton im = findViewById(R.id.secondImage);
        ImageView im1 = findViewById(R.id.uplodedImage);
        Bitmap temp = third;
        Bitmap temp1 = first;
        third = temp1;
        first = temp;
        im.setImageBitmap(temp1);
        im1.setImageBitmap(temp);
    }

    public void changeThirdPic(View view) {
        ImageButton im = findViewById(R.id.thirdImage);
        ImageView im1 = findViewById(R.id.uplodedImage);
        Bitmap temp = fourth;
        Bitmap temp1 = first;
        fourth = temp1;
        first = temp;
        im.setImageBitmap(temp1);
        im1.setImageBitmap(temp);
    }

    public void submitUploadedImage(View view) {


        uploadImage();
       // Intent i=new Intent(this,MainActivity.class);
     //   startActivity(i);

      //  new SigninActivity(this, status, role, Pname, Pprice, Pnumber, Pdesc, color, size, cat, 0, 1).execute(bitmap);
    }

    int o1 = 0, o2 = 0, o3 = 0, o4 = 0, o5 = 0, o6 = 0;

    public void choosingColor(View view) {
        switch (view.getId()) {
            case R.id.redColor: {
                if (o1 % 2 == 0) o1 = 1;
                else o1 = 0;
            }
            case R.id.blackColor: {
                if (o2 % 2 == 0) o2 = 1;
                else o2 = 0;
            }
            case R.id.blueColor: {
                if (o3 % 2 == 0) o3 = 1;
                else o3 = 0;
            }
            case R.id.greenColor: {
                if (o4 % 2 == 0) o4 = 1;
                else o4 = 0;
            }
            case R.id.whiteColor: {
                if (o5 % 2 == 0) o5 = 1;
                else o5 = 0;
            }
            case R.id.grayColor: {
                if (o6 % 2 == 0) o6 = 1;
                else o6 = 0;
            }
        }
    }

    int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0, c6 = 0;

    public void choosSize(View view) {
        switch (view.getId()) {
            case R.id.small: {
                if (c1 % 2 == 0) c1 = 1;
                else c1 = 0;
            }
            case R.id.mediam: {
                if (c2 % 2 == 0) c2 = 1;
                else c2 = 0;
            }

            case R.id.larg: {
                if (c3 % 2 == 0) c3 = 1;
                else c3 = 0;
            }

            case R.id.xlarg: {
                if (c4 % 2 == 0) c4 = 1;
                else c4 = 0;
            }

            case R.id.xxlarg: {
                if (c5 % 2 == 0) c5 = 1;
                else c5 = 0;
            }

            case R.id.xxxlarg: {
                if (c1 % 6 == 0) c6 = 1;
                else c6 = 0;
            }
        }
    }
    private void uploadImage(){
     class dSigninActivity extends AsyncTask<Bitmap, Void, String> {
        private String statusField;
        private String roleField;
        private Context context;
        public String result;
        ProgressDialog loading;
        RequestHandler rh = new RequestHandler();
        private int byGetOrPost = 0, page;
        private int cat;
        String Pname, Pprice, Pnumber, Pdesc, color, size;


        /*
        public SigninActivity(Context context,String statusField,String roleField,String email,String pass,int page ,int flag) {
            this.context = context;
            this.statusField = statusField;
            this.roleField = roleField;
            byGetOrPost = flag;
            this.emailStr=email;
            this.passStr=pass;
            this.page=page;
        }

         */

        public dSigninActivity(Context context, String statusField, String roleField, String Pname, String Pprice, String Pnumber, String Pdesc, String color, String size, int cat, int page, int flag) {
            this.context = context;
            byGetOrPost = flag;

            this.Pname = Pname;
            this.Pprice = Pprice;

            this.Pnumber = Pnumber;
            this.Pdesc = Pdesc;
            this.color = color;
            this.size = size;
            this.cat = cat;
            this.page = page;
        }

            //  String username = (String) arg0[0];
            // String password = (String)arg0[1];
            //   String link = "https://c-store.000webhostapp.com/indexGet.php?username='"+emailStr+"'&password='"+passStr+"'";
               @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                Bitmap bitmap1=params[1];
                Bitmap bitmap2=params[2];
                Bitmap bitmap3=params[3];

                String uploadImage = getStringImage(bitmap);
                String uploadImage1 = getStringImage(bitmap1);
                String uploadImage2 = getStringImage(bitmap2);
                String uploadImage3= getStringImage(bitmap3);
                 //   String link = "https://c-store.000webhostapp.com/uploadFile.php?image='"+URLEncoder.encode(uploadImage)+"'&name='"+getFileName(filePath1)+"'&image1='"+URLEncoder.encode(uploadImage1)+"'&name1='"+getFileName(filePath2)+"'&image2='"+URLEncoder.encode(uploadImage2)+"'&name2='"+getFileName(filePath3)+"'&image3='"+URLEncoder.encode(uploadImage3)+"'&name3='"+getFileName(filePath4)+"'&email='"+emailStr+"'&Pname='"+Pname+"'&Pprice='"+Pprice+"'&Pnumber='"+Pnumber+"'&Pdesc='"+Pdesc+"'&color='"+color+"'&size='"+size+"'&cat='"+cat+"'";
                      String link = "https://c-store.000webhostapp.com/uploadFile.php";
/*
                   Glide.with(upload_file.this)
                           .load(link)
                           .asBitmap()
                           .toBytes(Bitmap.CompressFormat.JPEG,80)
                           .atMost()
                           .override(2048,2048)
                           .diskCacheStrategy(DiskCacheStrategy.NONE)
                           .skipMemoryCache(true)
                           .into(new SimpleTarget<byte[]>() {
                               @Override
                               public void onResourceReady(@NonNull byte[] resource, @Nullable Transition<? super byte[]> transition) {
                                   IOTools.writeFile(getTargetFile(),resource);
                               }
                           });
                      */
                        HashMap<String,String> data = new HashMap<>();
                   data.put(UPLOAD_KEY, uploadImage);
                   data.put("name",getFileName(filePath1));
                   HashMap<String,String> data1 = new HashMap<>();
                   data.put("image1", uploadImage1);
                   data.put("name1",getFileName(filePath2));
                   HashMap<String,String> data2 = new HashMap<>();
                   data.put("image2", uploadImage2);
                   data.put("name2",getFileName(filePath3));
                   HashMap<String,String> data3 = new HashMap<>();
                   data.put("image3", uploadImage3);
                   data.put("name3",getFileName(filePath4));
                   data.put("email",emailStr);
                   data.put("Pname",Pname);
                   data.put("Pprice",Pprice);
                   data.put("Pdesc",Pdesc);
                   data.put("color",color);
                   data.put("size",size);
                   data.put("Pnumber",Pnumber);
                   data.put("cat",String.valueOf(cat));


                   String result = rh.postRequest(link,data);
                            /*
                            System.out.println("this is it"+URLEncoder.encode(Pname, "UTF-8") );
                            result+="&" + data1 + "&" + data2 + "&" + data3 + "&" + URLEncoder.encode("email", "UTF-8") + "=" +
                                    URLEncoder.encode(emailStr, "UTF-8") +
                            "&" + URLEncoder.encode("Pname", "UTF-8") + "=" +
                                    URLEncoder.encode(Pname, "UTF-8") +
                            "&" + URLEncoder.encode("Pprice", "UTF-8") + "=" +
                                    URLEncoder.encode(Pprice, "UTF-8") +
                            "&" + URLEncoder.encode("Pdesc", "UTF-8") + "=" +
                                    URLEncoder.encode(Pdesc, "UTF-8") +
                            "&" + URLEncoder.encode("color", "UTF-8") + "=" +
                                    URLEncoder.encode(color, "UTF-8") +
                            "&" + URLEncoder.encode("size", "UTF-8") + "=" +
                                    URLEncoder.encode(size, "UTF-8") +
                            "&" + URLEncoder.encode("cat", "UTF-8") + "=" +
                                    URLEncoder.encode(String.valueOf(cat), "UTF-8") +
                            "&" + URLEncoder.encode("Pnumber", "UTF-8") + "=" +
                                    URLEncoder.encode(Pnumber, "UTF-8");

                             */
                   return result;
                    }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            loading = ProgressDialog.show(upload_file.this, "Uploading Product", "Please wait...", true, true);
        }

        @Override
        public void onPostExecute(String result2) {
            super.onPostExecute(result2);
         //   loading.dismiss();
            System.out.println("json11" + result2);
            this.statusField = "Login Successful";


            //Toast.makeText(getApplicationContext(),emailStr,Toast.LENGTH_SHORT).show();

            // System.out.println(result2);
            /*
            if (page == 1) {


                String s = "";
                JSONArray JArray = null;

                try {
                    JArray = new JSONArray(result2);

                    for (int i = 0; i < JArray.length(); i++) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = JArray.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {

                            userName = jsonObject.getString("name");
                            userPicture = jsonObject.getString("picture");
                            userEmail = jsonObject.getString("email");
                            goToNextPage(userName, userPicture, userEmail);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"email or password is wrong",Toast.LENGTH_SHORT).show();
                }


            }

            result = result2;
          */
        }


    }
        String method = "Post Method";
        String status = "", role = "";

        dSigninActivity ui = new dSigninActivity(this, status, role, Pname, Pprice, Pnumber, Pdesc, Pcolor, Psize, cat, 0, 1);
        ui.execute(first,second,third,fourth);
}
    public void changePicture(View view) {
        showFileChooser();
    }


    public void uploadPicture(View view) {
        if(filePath!=null) {
            Pname = pName.getText().toString();
            Pprice = pPrice.getText().toString();
            Pnumber = pNumber.getText().toString();
            Pdesc = pDesc.getText().toString();
            Pcolor += o1;
            Pcolor += o2;
            Pcolor += o3;
            Pcolor += o4;
            Pcolor += o5;
            Pcolor += o6;
            Psize += c1;
            Psize += c2;
            Psize += c3;
            Psize += c4;
            Psize += c5;
            Psize += c6;
            uploadImage();
        } else {
            Toast.makeText(upload_file.this,"Select Image",Toast.LENGTH_LONG).show();
        }
    }

    String getFileName(Uri uri){
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
    public Bitmap saveBitmapToFile(Bitmap file){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //  bmp = Bitmap.createScaledBitmap(bmp, 200, 200, false);
            //   file.compress(Bitmap.CompressFormat.JPEG, 40, );
            file.compress(Bitmap.CompressFormat.JPEG, 100 , baos);
            int options=90;
            while (baos.toByteArray().length/1024>400){
                baos.reset();;
                file.compress(Bitmap.CompressFormat.JPEG,options,baos);
                options-=10;
            }
            ByteArrayInputStream isBm=new ByteArrayInputStream(baos.toByteArray());
            Bitmap bitmap=BitmapFactory.decodeStream(isBm,null,null);
            return  bitmap;
            // BitmapFactory options to downsize the image
           // BitmapFactory.Options o = new BitmapFactory.Options();
           // o.inJustDecodeBounds = true;
           // o.inSampleSize = 6;
            // factor of downsizing the image

          //  FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
          //  BitmapFactory.decodeStream(inputStream, null, o);
           // inputStream.close();

            // The new size we want to scale to
           // final int REQUIRED_SIZE=75;

            // Find the correct scale value. It should be the power of 2.
           // int scale = 1;
            //while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
              //      o.outHeight / scale / 2 >= REQUIRED_SIZE) {
               // scale *= 2;
           // }

         //   BitmapFactory.Options o2 = new BitmapFactory.Options();
          //  o2.inSampleSize = scale;
          //  inputStream = new FileInputStream(file);

         //   Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
         //   inputStream.close();
            // here i override the original image file
         //   file.createNewFile();
         //   FileOutputStream outputStream = new FileOutputStream(file);


           // return file;

    }

    }
