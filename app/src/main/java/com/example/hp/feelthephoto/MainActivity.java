package com.example.hp.feelthephoto;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;
    Button button;
    ImageView imageView;
    Intent intent;
    Bitmap bitmp;

    final static int picbycamera=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton = findViewById(R.id.imageButton2);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);

        InputStream inputStream = getResources().openRawResource(R.drawable.image_one);
        bitmp = BitmapFactory.decodeStream(inputStream);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getApplicationContext().setWallpaper(bitmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "FEEL", Toast.LENGTH_SHORT).show();

            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,picbycamera);
                Toast.makeText(MainActivity.this, "LOOK", Toast.LENGTH_SHORT).show();

            }
        });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            Bundle extras = data.getExtras();
            bitmp = (Bitmap)extras.get("data");
            imageView.setImageBitmap(bitmp);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();              //integer since we cannot pass objects to switch case
        switch (id)
        {
            case R.id.item1:
                Toast.makeText(MainActivity.this, "HI 1!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item2:
                Toast.makeText(MainActivity.this, "HI 2 !", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item3:
                Toast.makeText(MainActivity.this, "HI 3!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
