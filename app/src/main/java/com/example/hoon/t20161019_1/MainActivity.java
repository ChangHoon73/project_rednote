package com.example.hoon.t20161019_1;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    DBHandler dbhandler;
    Vector<Addr> vc;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhandler = DBHandler.open(this);
        final EditText editName = (EditText) this.findViewById(R.id.editName);
        final EditText editTel = (EditText) this.findViewById(R.id.editTel);

        Button btnInsert = (Button) this.findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long cnt = dbhandler.insert( editName.getText().toString(), editTel.getText().toString());
                Toast.makeText(MainActivity.this, "cnt:"+cnt, Toast.LENGTH_SHORT).show();
                DataReset();
                adapter.notifyDataSetChanged();

                view_alert();

            }
        });

        ListView listview = (ListView) this.findViewById(R.id.listview);
        vc = new Vector<Addr>();

        DataReset();

        adapter = new MyAdapter(this, vc, dbhandler);
        listview.setAdapter(adapter);

    }

    public void view_alert(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setIcon(R.drawable.icon);
        dialog.setTitle("테스트");
        dialog.setMessage("다이얼로그 박스 테스트입니다.");
        dialog.setCancelable(true);
        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "확인", Toast.LENGTH_SHORT).show();
            }
        });
//        dialog.setView(R.layout)
        dialog.setNegativeButton("취소", null);
        dialog.create();
        dialog.show();
    }
    public void DataReset(){
        vc.clear();
        Cursor cursor = dbhandler.getSelectAll();
        if( cursor.getCount() > 0 ) {
            do {
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                String _name = cursor.getString(cursor.getColumnIndex("_name"));
                String _tel = cursor.getString(cursor.getColumnIndex("_tel"));
                vc.add(new Addr(_id, _name, _tel));
            } while (cursor.moveToNext());
        }
    }
}








