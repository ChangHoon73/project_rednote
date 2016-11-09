package com.example.hoon.t20161019_1;

/**
 * Created by Hoon on 2016-10-20.
 */

public class Addr {
    private int _id;
    private String _name;
    private String _tel;

    public Addr(int _id, String _name, String _tel) {
        this._id = _id;
        this._name = _name;
        this._tel = _tel;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_tel() {
        return _tel;
    }

    public void set_tel(String _tel) {
        this._tel = _tel;
    }
}
