package com.tasfia.orphanhouse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VolunteerModel implements Parcelable {

    @SerializedName("VName")
    @Expose
    public String name;

    @SerializedName("UserName")
    @Expose
    public String userName;

    @SerializedName("ContactNo")
    @Expose
    public String cell;

    @SerializedName("Address")
    @Expose
    public String address;

    @SerializedName("Email")
    @Expose
    public String email;

    @SerializedName("Profession")
    @Expose
    public String profession;

    @SerializedName("Blood")
    @Expose
    public String blood;

    @SerializedName("Uimg")
    @Expose
    public String image;

    protected VolunteerModel(Parcel in) {
        name = in.readString();
        userName = in.readString();
        cell = in.readString();
        address = in.readString();
        email = in.readString();
        profession = in.readString();
        blood = in.readString();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(userName);
        dest.writeString(cell);
        dest.writeString(address);
        dest.writeString(email);
        dest.writeString(profession);
        dest.writeString(blood);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VolunteerModel> CREATOR = new Creator<VolunteerModel>() {
        @Override
        public VolunteerModel createFromParcel(Parcel in) {
            return new VolunteerModel(in);
        }

        @Override
        public VolunteerModel[] newArray(int size) {
            return new VolunteerModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
