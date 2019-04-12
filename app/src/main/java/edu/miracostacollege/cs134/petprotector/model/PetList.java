package edu.miracostacollege.cs134.petprotector.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PetList {

    private long mId;
    private String mName;
    private String mDescription;
    private int mPhone;
    private String mImageName;

    public PetList() {
        this(-1, "", "", 0, "none.png");
    }


    public PetList(String mName, String mDescription, int mPhone) {
        this(-1, mName, mDescription, mPhone, "none.png");
    }



    public PetList(String mName, String mDescription, int mPhone, String imageName) {
        this(-1, mName, mDescription, mPhone, imageName);
    }



    public PetList(long id, String name, String description, int phone, String imageName) {
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

    public int getmPhone() {
        return mPhone;
    }

    public void setmPhone(int phone) {
        mPhone = phone;
    }

    public String getImageName() {
        return mImageName;
    }

    public void setImageName(String imageName) {
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

    @Override
    public int describeContents() {
        return 0;
    }

   @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeFloat(mPhone);
        dest.writeString(mImageName);


    }

    //mechanism to create(instantiate) a new Game object from a Parcel
    //private constructor to create a new Game from a parcel

    private PetList(Parcel parcel) {
        mId = parcel.readLong();
        mName = parcel.readString();
        mDescription = parcel.readString();
        mPhone = parcel.readInt();
        mImageName = parcel.readString();

    }

    public static final Parcelable.Creator<PetList> CREATOR = new Parcelable.Creator<PetList>() {
        @Override
        public PetList createFromParcel(Parcel source) {
            return new PetList(source);
        }


        @Override
        public PetList[] newArray(int size) {
            return new PetList[size];
        }
    };

}


