package com.hanbit.kakaotalk;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final Context context = login.this;
        final EditText inputId=(EditText) findViewById(R.id.inputid);
        final EditText inputPass=(EditText) findViewById(R.id.inputpass);
        final MemberLogin login = new MemberLogin(context);
        findViewById(R.id.confirmbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id=String.valueOf(inputId.getText());
                final String pass=String.valueOf(inputPass.getText());
                Log.d("입력된 아이디",id);
                Log.d("입력된 아이디",pass);
                new Service.iPredicate() {
                    @Override
                    public void execute() {
                        if(login.execute(id,pass)==true){
                            Toast.makeText(context,"로그인 성공",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(context,memberlist.class));
                        }else{
                            Toast.makeText(context,"로그인 실패",Toast.LENGTH_SHORT);
                            ((EditText) findViewById(R.id.inputid)).setText("");
                            ((EditText) findViewById(R.id.inputpass)).setText("");
                        }
                    }
                }.execute();

            }
        });
        findViewById(R.id.cancelbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"취소",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context,main.class));
            }
        });

    }
    private abstract class LoginQuery extends index.QueryFactory{
        SQLiteOpenHelper helper;
        public LoginQuery(Context context) {
            super(context);
            helper=new index.SqLiteHelper(context);
        }
        @Override
        public SQLiteDatabase getDatabase() {
            return helper.getReadableDatabase();
        }
    }
    private class MemberLogin extends LoginQuery{
        public MemberLogin(Context context) {
            super(context);
        }
        public boolean execute(String id, String pass){
            return super
                    .getDatabase()
                    .rawQuery(String.format("select * from %s where %s='%s' and %s='%s'"
                            ,Cons.MEM_TBL,Cons.SEQ,id,Cons.PASS,pass),null)
                    .moveToNext()
                    ;
        }
    }
}