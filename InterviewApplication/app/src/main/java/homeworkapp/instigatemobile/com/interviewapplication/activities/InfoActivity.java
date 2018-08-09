package homeworkapp.instigatemobile.com.interviewapplication.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import homeworkapp.instigatemobile.com.interviewapplication.R;
import homeworkapp.instigatemobile.com.interviewapplication.models.Result;
import homeworkapp.instigatemobile.com.interviewapplication.providers.DataProvider;

public class InfoActivity extends AppCompatActivity {

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initData();
        goToMap();
    }

    private void goToMap() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(InfoActivity.this, MapsActivity.class);
                intent.putExtra(DataProvider.getMyKey(), position);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        final TextView name = findViewById(R.id.text_name);
        final TextView email = findViewById(R.id.text_email);
        final TextView phone = findViewById(R.id.text_phone);
        final TextView date = findViewById(R.id.text_date);
        final TextView age = findViewById(R.id.text_age);
        final TextView nationality = findViewById(R.id.text_nationality);
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        position = getIntent().getIntExtra(DataProvider.getMyKey(), 0);
        final Result result = DataProvider.getList().get(position);
        collapsingToolbarLayout.setTitle(result.getName().getFirst());
        name.setText(getString(R.string.name) + "   " + result.getName().getFirst());
        email.setText(getString(R.string.email) + "   " + result.getName().getTitle());
        phone.setText(getString(R.string.phone) + "   " + result.getPhone());
        nationality.setText(getString(R.string.nationality) + " " + result.getNat());
        age.setText(getString(R.string.age) + "   " + result.getRegistered().getAge());
        date.setText(getString(R.string.date) + "   " + result.getRegistered().getDate());
        final ImageView imageView = findViewById(R.id.info_image);
        Picasso.get().load(result.getPicture().getLarge()).fit().into(imageView);
    }


}
