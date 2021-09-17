package com.example.foodservice;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodservice.model.Restaurant;

public class ResActivity extends AppCompatActivity {
    private RecyclerViewAdapterForRestaurants adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        String savedExtra = getIntent().getStringExtra("franchiseId");
        int id = Integer.parseInt(savedExtra);

        Restaurant r = new Restaurant();
        r.initialLoad();
        adapter = new RecyclerViewAdapterForRestaurants(r.getRestaurantsByFranchise(id));
        RecyclerView cv=this.findViewById(R.id.recycler_view_r);
        cv.setLayoutManager(new LinearLayoutManager(this));
        cv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        new MenuHelper().menuActionsConfig(item, this);
        return super.onOptionsItemSelected(item);
    }
}