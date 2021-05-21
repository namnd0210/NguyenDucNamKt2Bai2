package com.example.nguyenducnam_ktra2_bai2;

public class KhoaHoc {
    String id, name, date, chuyenNganh, active;

    public KhoaHoc(String id, String name, String date, String chuyenNganh, String active) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.chuyenNganh = chuyenNganh;
        this.active = active;
    }

    public KhoaHoc() {
    }

    public KhoaHoc(String name, String date, String chuyenNganh, String active) {
        this.name = name;
        this.date = date;
        this.chuyenNganh = chuyenNganh;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public String isActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "KhoaHoc{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", chuyenNganh='" + chuyenNganh + '\'' +
                ", active=" + active +
                '}';
    }
}
