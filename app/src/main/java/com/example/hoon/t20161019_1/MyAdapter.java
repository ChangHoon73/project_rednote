package com.example.hoon.t20161019_1;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Vector;

/**
 * Created by Hoon on 2016-10-20.
 */

public class MyAdapter extends BaseAdapter {
    Context ctx;
    Vector<Addr> vc;
    LayoutInflater inflater;
    DBHandler dbhandler;

    EditText edit_mod_name;
    EditText edit_mod_tel;

    public MyAdapter(Context ctx, Vector<Addr> vc, DBHandler dbhandler) {
        this.ctx = ctx;
        this.vc = vc;
        this.dbhandler = dbhandler;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return vc.size();
    }

    @Override
    public Object getItem(int position) {
        return vc.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if( convertView == null){
            convertView = inflater.inflate(R.layout.content_main, null);
        }
        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        TextView txtTel = (TextView) convertView.findViewById(R.id.txtTel);
        Button btnModify = (Button) convertView.findViewById(R.id.btnModify);
        Button btnDelete = (Button) convertView.findViewById(R.id.btnDelete);

        txtName.setText( vc.get(position).get_name());
        txtTel.setText( vc.get(position).get_tel());
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_data(position);
            }
        });

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modify_Dialog(position);
            }
        });
        return convertView;
    }

    public void Modify_Dialog(final int position){
        View view = inflater.inflate(R.layout.modify_addr, null);
        edit_mod_name = (EditText) view.findViewById(R.id.edit_mod_name);
        edit_mod_tel = (EditText) view.findViewById(R.id.edit_mod_tel);
        edit_mod_name.setText( vc.get(position).get_name() );
        edit_mod_tel.setText( vc.get(position).get_tel() );

        new AlertDialog.Builder(ctx)
                .setIcon(R.drawable.icon)
                .setTitle("전화번호수정")
                .setCancelable(false)
                .setView(view)
                .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        modify_data(position);
                    }
                })
                .setNegativeButton("취소", null)
                .setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete_data(position);
                    }
                })
                .create()
                .show();
    }

    public void modify_data(int position){
        dbhandler.update(
                vc.get(position).get_id(),
                edit_mod_name.getText().toString(),
                edit_mod_tel.getText().toString()
        );
        ((MainActivity)ctx).DataReset();
        MyAdapter.this.notifyDataSetChanged();
    }

    public void delete_data(int position){
        dbhandler.delete(vc.get(position).get_id());
        ((MainActivity)ctx).DataReset();
        MyAdapter.this.notifyDataSetChanged();
    }
}
