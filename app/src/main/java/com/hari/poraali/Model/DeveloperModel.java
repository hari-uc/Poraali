package com.hari.poraali.Model;

public class DeveloperModel {

    String img,name,role;

    DeveloperModel(){

    }

    public DeveloperModel(String img, String name, String role) {
        this.img = img;
        this.name = name;
        this.role= role;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
