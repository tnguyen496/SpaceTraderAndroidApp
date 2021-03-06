package edu.gatech.cs2340.spacetrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import edu.gatech.cs2340.spacetrader.MainActivity;
import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.models.MarketPlace;
import edu.gatech.cs2340.spacetrader.models.SolarSystem;
import edu.gatech.cs2340.spacetrader.models.player;
import edu.gatech.cs2340.spacetrader.viewmodel.CreateUniverse;

public class PlayingActivity extends AppCompatActivity {
    MarketPlace marketPlace;
    SolarSystem planet;
    private player player;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        getIncomingIntent();
        Button market = findViewById(R.id.market_button);
        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                CreateUniverse universe = new CreateUniverse();
                ArrayList<SolarSystem> solarList = new ArrayList<>(universe.create());
                planet = solarList.get(0);
                marketPlace = new MarketPlace(planet, planet.getPriceResources());
                marketPlace.display();

                Intent intent = new Intent(PlayingActivity.this, MarketActivity.class);
                intent.putExtra("player", player);
                intent.putExtra("marketPlace", marketPlace);
                intent.putExtra("planet", planet);

                startActivity(intent);
            }
        });


    }
    private void getIncomingIntent() {
//        System.out.println("aaaaaaaaaaaasdasdasssssss");
        if (getIntent().hasExtra("player")) {
            player = getIntent().getParcelableExtra("player");
//            Log.d("receive Player", player.toString());
        }
        if (getIntent().hasExtra("marketPlace")) {
            marketPlace = getIntent().getParcelableExtra("marketPlace");
//            Log.d("receive marketPlace", "marketPlace hello");
        }
        if (getIntent().hasExtra("planet")) {
            planet = getIntent().getParcelableExtra("planet");
//            Log.d("receive planet", "planet hello");
        }
    }
}
