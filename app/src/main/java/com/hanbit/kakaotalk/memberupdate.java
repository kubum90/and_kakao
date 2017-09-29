package com.hanbit.kakaotalk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class memberupdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = memberupdate.this;
        setContentView(R.layout.memberupdate);
        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"확인클릭",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,memberdetail.class);
                startActivity(new Intent(context,memberdetail.class));
            }
        });
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"취소클릭",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,memberlist.class);
                startActivity(new Intent(context,memberlist.class));
            }
        });
    }
}
