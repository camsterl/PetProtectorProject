package edu.miracostacollege.cs134.petprotector;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.miracostacollege.cs134.petprotector.model.DBHelper;
import edu.miracostacollege.cs134.petprotector.model.Pet;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_LOAD_IMAGE = 200;
    private ImageView petImageView;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView PetListView;
    private List<Pet> pet;
    private DBHelper db;
    private  PetListAdapter petListAdapter;
    private Uri image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        pet = db.getAllPets();
      PetListView = findViewById(R.id.PetListView);
        PetListView.setAdapter(new PetListAdapter(this, R.layout.pet_list_item, pet));


        //connect pet image view to layout
        //set image url to petImageView

        petImageView = findViewById(R.id.petImageView);
        petImageView.setImageURI(getUritoResources(this, R.drawable.none));


        pet = db.getAllPets();
        petListAdapter = new PetListAdapter(this, R.layout.pet_list_item, pet);

        PetListView = findViewById(R.id.PetListView);
        PetListView.setAdapter(petListAdapter);
    }

    private static Uri getUritoResources(Context context, int id) {
        Resources res = context.getResources();
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + res.getResourcePackageName(id) + "/" + res.getResourceTypeName(id) + "/" + res.getResourceEntryName(id);


        return Uri.parse(uri);
    }

    public void selectPetImage(View v) {
        //1) Make list(empty) of permissions
        //2) As user grants them, add each permissions to the list
        List<String> permsList = new ArrayList<>();
        int perReqCode = 100;
        int hasCameraPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int hasReadExternalPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int hasWriteExternalPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //Check to see if camera permission is denied
        //If denied, add it to the list of permissions requests
        if (hasCameraPerm == PackageManager.PERMISSION_DENIED) {
            permsList.add(Manifest.permission.CAMERA);
        }
        if (hasReadExternalPerm == PackageManager.PERMISSION_DENIED) {
            permsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (hasWriteExternalPerm == PackageManager.PERMISSION_DENIED) {
            permsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (permsList.size() > 0) {
            //Convert the List into an array
            String[] perms = new String[permsList.size()];
            permsList.toArray(perms);
            //Make request to the user
            ActivityCompat.requestPermissions(this, perms, perReqCode);
        }

        //After requesting permissions, find out which ones user granted
        //check to see if ALL permissions were granted
        if (hasCameraPerm == PackageManager.PERMISSION_GRANTED && hasReadExternalPerm == PackageManager.PERMISSION_GRANTED && hasWriteExternalPerm == PackageManager.PERMISSION_GRANTED) {
            //Open the Gallery
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        } else {
            //Toast saying permissions required
        }
    }
    //Override onActivityResult to find out what the user picked

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE) {
            image = data.getData();
            petImageView.setImageURI(image);
        }
    }

        public void viewPetDetails(View view) {
            Pet selectedPet = (Pet) view.getTag();

            Intent detailsIntent = new Intent(this, PetDetailsActivity.class);
            detailsIntent.putExtra("SelectedPet", selectedPet);

            startActivity(detailsIntent);
        }

        public void addPet(View v)
        {

            EditText nameEditText = findViewById(R.id.nameTextView);
            EditText descriptionEditText = findViewById(R.id.descriptionEditText);
            EditText phoneTextView = findViewById(R.id.phoneTextView);

            String name = nameEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            String phone = phoneTextView.getText().toString();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(description))
            {
                Toast.makeText(this, "Both name and description of the game must be provided.", Toast.LENGTH_LONG);
                return;
            }

            Pet newPet = new Pet(name, description, phone, image);

            // Add the new game to the database to ensure it is persisted.
            db.addPet(newPet);
            petListAdapter.add(newPet);
            // Clear all the entries (reset them)
            nameEditText.setText("");
            descriptionEditText.setText("");
            phoneTextView.setText("");
        }


}

