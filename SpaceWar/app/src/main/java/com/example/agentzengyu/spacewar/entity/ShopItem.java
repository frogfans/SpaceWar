package com.example.agentzengyu.spacewar.entity;

/**
 * Created by Agent ZengYu on 2017/6/20.
 */

import java.io.Serializable;

/**
 * 所有商店item的父类
 */
public class ShopItem implements Serializable{
    private String Name = "";
    private int Detail = 0;
    private int Level = 0;
    private int Fee = 0;
    private int Image = 0;

    public ShopItem(){
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDetail(int detail) {
        Detail = detail;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public void setFee(int fee) {
        Fee = fee;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public int getDetail() {
        return Detail;
    }

    public int getLevel() {
        return Level;
    }

    public int getFee() {
        return Fee;
    }

    public int getImage() {
        return Image;
    }
}