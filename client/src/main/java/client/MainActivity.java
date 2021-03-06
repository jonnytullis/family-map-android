package client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import com.example.client.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private DataCache dataCache = DataCache.getInstance();
    private LoginFragment loginFragment;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (dataCache.authToken() == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            loginFragment = (LoginFragment) fragmentManager.findFragmentById(R.id.login_layout);
            if (loginFragment == null) {
                loginFragment = createLoginFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.main_frame_layout, loginFragment)
                        .commit();
            }
        } else {
            switchToMapFragment();
        }

        Iconify.with(new FontAwesomeModule());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private LoginFragment createLoginFragment() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    private MapFragment createMapFragment() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    public void switchToMapFragment () {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map_fragment);
        if (mapFragment == null) {
            mapFragment = createMapFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.main_frame_layout, mapFragment)
                    .commit();
        }

        // Enable menu options in app bar
        View view = getLayoutInflater().inflate(R.layout.custom_menu_bar, null);
        Objects.requireNonNull(getSupportActionBar()).setCustomView(view);

        ImageView gearImageView = view.findViewById(R.id.settings_button);
        gearImageView.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_gear).
                colorRes(R.color.light).sizeDp(25));

        gearImageView.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        ImageView searchImageView = view.findViewById(R.id.search_button);
        searchImageView.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_search).
                colorRes(R.color.light).sizeDp(25));

        searchImageView.setOnClickListener(v -> {
            Intent intent = new Intent(this, SearchActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowCustomEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}