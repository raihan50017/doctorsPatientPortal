package com.medicine.doctorspatientportal.model;

public class User {
    String fullName;
    String memberType, mobile, birthDate, imgUrl, gender, id;
    public User(){

    };

    public User(String fullName, String memberType, String mobile, String birthDate, String imgUrl, String gender, String id) {
        this.fullName = fullName;
        this.memberType = memberType;
        this.mobile = mobile;
        this.birthDate = birthDate;
        this.imgUrl = imgUrl;
        this.gender = gender;
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMemberType() {
        return memberType;
    }

    public String getMobile() {
        return mobile;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public String getGender() {
        return gender;
    }
    public String getId() {
        return id;
    }
    public String getImgUrl() {
        return imgUrl;
    }
}
