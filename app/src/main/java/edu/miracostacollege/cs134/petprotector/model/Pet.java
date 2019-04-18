package edu.miracostacollege.cs134.petprotector.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.URI;

public class Pet implements Parcelable {

    private long mId;
    private String mName;
    private String mDescription;
    private String mPhone;
    private Uri mImageName;




    public Pet(String mName, String mDescription, String mPhone, Uri imageName) {
        this(-1, mName, mDescription, mPhone, imageName);
    }


    public Pet(long id, String name, String description, String phone, Uri imageName) {
        mId = id;
        mName = name;
        mDescription = description;
        mPhone = phone;
        mImageName = imageName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String name) {
        mName = name;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String description) {
        mDescription = description;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String phone) {
        mPhone = phone;
    }

    public Uri getImageName() {
        return mImageName;
    }

    public void setImageName(Uri imageName) {
        mImageName = imageName;
    }

    @Override
    public String toString() {
        return "MusicEvent{" +
                "Name='" + mName + '\'' +
                ", Description='" + mDescription + '\'' +
                ", Phone='" + mPhone + '\'' +
                ", mImageName='" + mImageName + '\'' +
                '}';
    }


    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeString(mPhone);
        dest.writeString(mImageName.toString());


    }

    //mechanism to create(instantiate) a new Game object from a Parcel
    //private constructor to create a new Game from a parcel

    private Pet(Parcel parcel) {
        mId = parcel.readLong();
        mName = parcel.readString();
        mDescription = parcel.readString();
        mPhone = parcel.readString();
        mImageName = Uri.parse(parcel.readString());

    }

    public static final Parcelable.Creator<Pet> CREATOR = new Parcelable.Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel source) {
            return new Pet(source);
        }


        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

}


