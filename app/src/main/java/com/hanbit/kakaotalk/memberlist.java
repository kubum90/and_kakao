package com.hanbit.kakaotalk;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class memberlist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memberlist);
        final Context context = memberlist.this;
        ListView listview = (ListView) findViewById(R.id.listview);
        final friendlist flist=new friendlist(context);

        ArrayList<Member> friends =(ArrayList<Member>) new Service.iList(){
            @Override
            public ArrayList<?> execute(Object o) {
                return flist.execute();
            }
        }.execute(null);

        listview.setAdapter(new MemberAdapter(context,friends));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"로그아웃",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,login.class);
                startActivity(new Intent(context,login.class));
            }

        });

        findViewById(R.id.memberadd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"친구추가",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,memberadd.class);
                startActivity(new Intent(context,memberadd.class));

            }
        });
        findViewById(R.id.detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"상세보기",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,memberdetail.class);
                startActivity(new Intent(context,memberdetail.class));

            }
        });
    }

    private abstract class ListQuery extends index.QueryFactory{
        SQLiteOpenHelper helper;
        public ListQuery(Context context) {
            super(context);
            helper=new index.SqLiteHelper(context);
        }
        @Override
        public SQLiteDatabase getDatabase() {
            return helper.getReadableDatabase();
        }
    }
    private class friendlist extends ListQuery{
        public friendlist(Context context) {
            super(context);
        }
        public ArrayList<Member> execute(){
            ArrayList<Member> list = new ArrayList<>();
            String sql=String.format("select * from %s ;",Cons.MEM_TBL);
            Cursor cursor=super.getDatabase().rawQuery(sql,null);
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do{
                    Member member = null;
                    member = new Member();
                    member.setSeq(cursor.getString(cursor.getColumnIndex(Cons.SEQ)));
                    member.setName(cursor.getString(cursor.getColumnIndex(Cons.NAME)));
                    member.setEmail(cursor.getString(cursor.getColumnIndex(Cons.EMAIL)));
                    member.setPass(cursor.getString(cursor.getColumnIndex(Cons.PASS)));
                    member.setAddr(cursor.getString(cursor.getColumnIndex(Cons.ADDR)));
                    member.setPhone(cursor.getString(cursor.getColumnIndex(Cons.PHONE)));
                    member.setProfileimg(cursor.getString(cursor.getColumnIndex(Cons.PROFILEIMAGE)));
                    list.add(member);
                    }while(cursor.moveToNext());
                }
            }

            return list;
        }
    }
    class MemberAdapter extends BaseAdapter{
        ArrayList<Member> list;
        LayoutInflater inflater;
        public MemberAdapter(Context context,ArrayList<Member> list) {
            this.list=list;
            this.inflater=LayoutInflater.from(context);

        }
        private int[] photos={
            R.drawable.cupcake,
            R.drawable.donut,
            R.drawable.eclair,
            R.drawable.froyo,
            R.drawable.gingerbread,
            R.drawable.honeycomb
        };

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View v, ViewGroup g) {
            ViewHolder viewHolder;
            if(v==null){
                v=inflater.inflate(R.layout.member_adapter,null);
                viewHolder = new ViewHolder();
                viewHolder.imageView= (ImageView) v.findViewById(R.id.imageview);
                viewHolder.name= (TextView) v.findViewById(R.id.name);
                viewHolder.phone= (TextView) v.findViewById(R.id.phone);
                v.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) v.getTag();
            }
            viewHolder.imageView.setImageResource(photos[i]);
            viewHolder.name.setText(list.get(i).getName());
            viewHolder.phone.setText(list.get(i).getPhone());
            return v;
        }
    }
    static class ViewHolder{
        ImageView imageView;
        TextView name;
        TextView phone;
    }
}
