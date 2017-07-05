package com.example.kdeek.enelchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class UserHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Toast.makeText(this,"THIS IS USER PROFILE",Toast.LENGTH_LONG).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu1) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profilemenuopts, menu1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editProfile :
                Toast.makeText(this,"View Profile Activity Call.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserHome.this,EditProfile.class);
                startActivityForResult(intent,456);
                break;
            case R.id.backtoInbox :
                Intent in = new Intent();
                setResult(RESULT_OK,in);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}
