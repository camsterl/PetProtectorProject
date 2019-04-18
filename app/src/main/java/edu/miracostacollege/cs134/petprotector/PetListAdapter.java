package edu.miracostacollege.cs134.petprotector;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import edu.miracostacollege.cs134.petprotector.model.Pet;

public class PetListAdapter extends ArrayAdapter<Pet> {

    //declare member variables to store the params
    private Context mContext;
    private int mResourceId;
    private List<Pet> mAllPets;

    //this constructor is being called  by MainActivity
    public PetListAdapter(@NonNull Context context, int resource, @NonNull List<Pet> objects) {
        super(context, resource, objects);
        mContext = context;
        mResourceId = resource;
        mAllPets = objects;

    }


    //in order to bridge the view (music_event_list_item) with model (MusicEvent) we override;
    //crtl 0 = override


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // inflate custom layout with data from List<MusicEvents>

        Pet focusedEvent = mAllPets.get(position);

        // manually inflate custom layout
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //tell inflater to inflate music_event_list_item

        View customView = inflater.inflate(mResourceId, null);

        //fill parts of custom view

        ImageView listItemImageView = customView.findViewById(R.id.PetImage);
        TextView listDateTextView = customView.findViewById(R.id.NameText);
        TextView listTitleTextView = customView.findViewById(R.id.DescriptionText);

        //put info in text views and image views
        listTitleTextView.setText(focusedEvent.getmName());
        listDateTextView.setText(focusedEvent.getmDescription());
        listDateTextView.setText(focusedEvent.getmPhone());

        //load pic



            listItemImageView.setImageURI(focusedEvent.getImageName());


        //return customview with all information
        return customView;
    }
}