
package com.cStore.shop;
/*
import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

public class SubjectList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        .....
 
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_serch, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
 
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
 
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()))
 
        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextChange(String newText)
            {
                // this is your adapter that will be filtered
                adapter.filter(newText);
 
                return true;
            }
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                // this is your adapter that will be filtered
                adapter.filter(query);
 
                return true;
            }
        };
        searchView.setOnQueryTextListener(textChangeListener);
 
        return super.onCreateOptionsMenu(menu);
    }
}
/*الكود واضح .. عملية ارتباط : ما يهمنا هو إن :
adapter / هو الادبتر المستخدم والمرتبط باللست الفيو خاصتك وحسب مثالنا هو الملف التالي : ListAdapter

ومن داخله طلبنا الدالة : filter وقمنا بتمرار النص الذي يكتبه المستخدم داخلها.

و الآن اذهب إلى الملف ListAdapter أو الملف الذي لديك الادبتر المتحكم باللست فيو لديك, لنضيف داخله الدالة التالية filter :*/

/*
public class ListAdapter extends BaseAdapter {
 
    public ListAdapter(Activity context, List<Data> openSite) {
        ...
 
    }
 
    @Override
    public int getCount() {
        ...
    }
 
    @Override
    public Object getItem(int position) {
        ...
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(final int position, View view, ViewGroup parent) {
        ...
 
        return Item;
 
    };
 
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        Datalist.clear();
        if (charText.length() == 0) {
            Datalist.addAll(arraylist);
        }
        else
        {
            for (Data wp : arraylist)
            {
                if (wp.getSubject1().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    Datalist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
/*فقط ما تحتاجه هو الدالة filter فالدوال الاخرى موجودة اساساً لديك, لكن ارسلت الكود بهذه الطريقة لتعرف إنه يجب عليك إضافتها دالة منفردة لوحدها ولكن بداخل الكلاس.

فالكود مضمونه هو الارتباط بملف الداتا لديك Datalist وهو الملف Data.java .

يقوم بتفريط ما كتبه المستخدم إلى احرف.

وإن النص الذي كتبه المستخدم إذا كان ولا حرف صفر اي الحالة الافتراضية تظهر جميع عناصر القائمة.

ولكن في حال كتب شيء ما, يقوم بتفريط كذلك احرف اللست فيو, ومقارنتها مع ما كتبه المستخدم ويتم جلب المتطابق بينها .

getSubject1() / هي الدالة التي يتم جلب من الداتا عناصر الاسم التي بالقائمة .. يستخدمها للبحث.

فقط هذا كل شيء ..
*/