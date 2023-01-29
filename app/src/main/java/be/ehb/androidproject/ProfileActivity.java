package be.ehb.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {


    SearchView searchView;
    RecyclerView recyclerView;

    ArrayList<ModelClass> arrayList = new ArrayList<>();
    ArrayList<ModelClass> searchList;
    String[] AutoList=new String[]{"Golf Gti"," golf R","class A","Duster","Supra","jaguar F","class GLC","class C","Tiguan","308",};

    String[] AutoNum=new String[]{"Auto 1","Auto 2","Auto 3","Auto 4","Auto 5","Auto 6","Auto 7","Auto 8","Auto 9","Auto 10", };

    int[] imgList=new int[]{R.drawable.gti,R.drawable.golfr,R.drawable.classea,R.drawable.duster,R.drawable.supra,
            R.drawable.f,R.drawable.glc,R.drawable.classc,R.drawable.tiguan,R.drawable.pegeut,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        recyclerView=findViewById(R.id.recyclerView);
        searchView=findViewById(R.id.searchView);

        searchView.setIconified(false);
        searchView.clearFocus();

        for (int i = 0; i < AutoList.length; i++){
            ModelClass modelClass =new ModelClass();
            modelClass.setAutoName(AutoList[i]);
            modelClass.setAutoNum(AutoNum[i]);
            modelClass.setImg(imgList[i]);
            arrayList.add(modelClass);
        }

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ProfileActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        AutoAdapter autoAdapter=new AutoAdapter(ProfileActivity.this,arrayList);
        recyclerView.setAdapter(autoAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchList=new ArrayList<>();

                if (query.length()>0){
                    for (int i =0; i<arrayList.size();i++ ){
                        if (arrayList.get(i).getAutoName().toUpperCase().contains(query.toUpperCase()) || arrayList.get(i).getAutoNum().toUpperCase().contains(query.toUpperCase())){

                            ModelClass modelClass=new ModelClass();
                            modelClass.setAutoName(arrayList.get(i).getAutoName());
                            modelClass.setAutoNum(arrayList.get(i).getAutoNum());
                            modelClass.setImg(arrayList.get(i).getImg());
                            searchList.add(modelClass);

                        }
                    }


                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ProfileActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                    AutoAdapter autoAdapter=new AutoAdapter(ProfileActivity.this,searchList);
                    recyclerView.setAdapter(autoAdapter);
                }else {

                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ProfileActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                    AutoAdapter autoAdapter=new AutoAdapter(ProfileActivity.this,arrayList);
                    recyclerView.setAdapter(autoAdapter);


                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList=new ArrayList<>();
                if (newText.length()>0){
                    for (int i =0; i<arrayList.size();i++ ){
                        if (arrayList.get(i).getAutoName().toUpperCase().contains(newText.toUpperCase()) || arrayList.get(i).getAutoNum().toUpperCase().contains(newText.toUpperCase())){

                            ModelClass modelClass=new ModelClass();
                            modelClass.setAutoName(arrayList.get(i).getAutoName());
                            modelClass.setAutoNum(arrayList.get(i).getAutoNum());
                            modelClass.setImg(arrayList.get(i).getImg());
                            searchList.add(modelClass);
                        }
                    }


                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ProfileActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                    AutoAdapter autoAdapter=new AutoAdapter(ProfileActivity.this,searchList);
                    recyclerView.setAdapter(autoAdapter);
                }else {

                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ProfileActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                    AutoAdapter autoAdapter=new AutoAdapter(ProfileActivity.this,arrayList);
                    recyclerView.setAdapter(autoAdapter);
                }

                return false;
            }
        });
    }
}