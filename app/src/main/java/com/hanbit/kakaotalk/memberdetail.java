package com.hanbit.kakaotalk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class memberdetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = memberdetail.this;
        setContentView(R.layout.memberdetail);
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"수정하기",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,memberupdate.class);
                startActivity(new Intent(context,memberupdate.class));
            }
        });
        findViewById(R.id.list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"리스트가기",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,memberlist.class);
                startActivity(new Intent(context,memberlist.class));
            }
        });

    }
}
