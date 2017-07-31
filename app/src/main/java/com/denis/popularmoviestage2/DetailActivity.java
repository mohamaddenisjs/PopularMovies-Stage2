package com.denis.popularmoviestage2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.denis.popularmoviestage2.model.Result;
import com.facebook.stetho.Stetho;

public class DetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        Stetho.Initializer initializer = initializerBuilder.build();

        Stetho.initialize(initializer);

        setContentView(R.layout.activity_detail);
        Result movie = (Result) getIntent().getSerializableExtra(DetailFragment.MOVIE_ARG_KEY);

        if (savedInstanceState == null) {
            Log.d(LOG_TAG, "savedInstanceState is null in onCreate()");
            Bundle bundle = new Bundle();
            bundle.putSerializable(DetailFragment.MOVIE_ARG_KEY, movie);
            DetailFragment fragment = new DetailFragment();
            fragment.setRetainInstance(true);
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }
}