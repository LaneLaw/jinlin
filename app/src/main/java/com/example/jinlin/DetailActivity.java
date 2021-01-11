package com.example.jinlin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetailActivity extends AppCompatActivity {
    ScrollView scrollView;
    Button button;
    TextView merchantNameDetail, merchantAmountDetail,merchantDescriptionDetail;
    ImageView merchantImageDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("merchant_id");
        int amount = bundle.getInt("merchant_amount");
        String name = bundle.getString("merchant_name");
        String desc = bundle.getString("merchant_description");
        String image = bundle.getString("merchant_image");


        merchantNameDetail.setText(name);
        merchantDescriptionDetail.setText(desc);
        merchantAmountDetail.setText(amount+"");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                merchantNameDetail.setText("");
            }
        });
    }

    private void init(){
        scrollView = findViewById(R.id.scrollview_detail);
        button = findViewById(R.id.button_test);
        merchantNameDetail = findViewById(R.id.merchant_name_detail);
        merchantAmountDetail = findViewById(R.id.amount_detail);
        merchantDescriptionDetail = findViewById(R.id.description_detail);
        merchantImageDetail = findViewById(R.id.merchant_image_detail);
    }
}
