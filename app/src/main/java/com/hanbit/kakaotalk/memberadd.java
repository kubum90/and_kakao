package com.hanbit.kakaotalk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class memberadd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = memberadd.this;
        setContentView(R.layout.memberadd);
        findViewById(R.id.addok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"확인누름",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,memberlist.class);
                startActivity(new Intent(context,memberlist.class));
            }
        });
        findViewById(R.id.addcancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"확인누름",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,memberlist.class);
                startActivity(new Intent(context,memberlist.class));
            }
        });
    }
}
