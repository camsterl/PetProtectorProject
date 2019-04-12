package edu.miracostacollege.cs134.petprotector;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import edu.miracostacollege.cs134.petprotector.model.PetList;

public class PetDetailsActivity extends AppCompatActivity {


    private static final String TAG = PetDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        ImageView gameDetailsImageView = findViewById(R.id.gameDetailsImageView);
        TextView NameTextView = findViewById(R.id.NameTextView);
        TextView DescriptionTextView = findViewById(R.id.DescriptionTextView);
        TextView phoneTextView = findViewById(R.id.phoneTextView);

        Intent detailsIntent = getIntent();
        //retrive game object from intent
        PetList pet = detailsIntent.getParcelableExtra("SelectedPet");


        AssetManager am = getAssets();
        try {
            InputStream stream = am.open(pet.getImageName());
            Drawable image = Drawable.createFromStream(stream, pet.getmName());
            gameDetailsImageView.setImageDrawable(image);
        }
        catch (IOException ex)
        {
            Log.e(TAG, "Error loading: " + pet.getImageName(), ex);
        }

        NameTextView.setText(pet.getmName());
        DescriptionTextView.setText(pet.getmDescription());
        phoneTextView.setText(pet.getmPhone());
    }
}
