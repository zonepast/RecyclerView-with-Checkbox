package com.pariportal.seconddemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.pariportal.seconddemo.R;

public class NextActivity extends AppCompatActivity {

    private TextView txtnewsname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        txtnewsname = (TextView)findViewById(R.id.txtnews);
        String setnewsname = getIntent().getExtras().getString("newsname");
        txtnewsname.setText(setnewsname);
    }
}
