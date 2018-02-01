package com.example.lucia.santaburguersf;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import com.example.lucia.santaburguersf.Fragment.Inicio;
import com.example.lucia.santaburguersf.Fragment.Lista_Menu;
import com.example.lucia.santaburguersf.Fragment.Mi_Cuenta;

public class MainActivity extends FragmentActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_inicio:
                    Inicio in = new Inicio();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fm_principal,in).commit();

                    return true;
                case R.id.navigation_que_pedir:
                    Lista_Menu lm = new Lista_Menu();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fm_principal,lm).commit();
                    return true;
                case R.id.navigation_mi_cuenta:
                    Mi_Cuenta mc = new Mi_Cuenta();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fm_principal,mc).commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        

        BottomNavigationView navigation =  findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Lista_Menu lm = new Lista_Menu();
        getSupportFragmentManager().beginTransaction().replace(R.id.fm_principal,lm).commit();
    }

}
