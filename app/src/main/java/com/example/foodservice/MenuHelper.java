package com.example.foodservice;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

public class MenuHelper {

    public void menuActionsConfig(MenuItem item, Activity activity) {
        Intent myIntent;
        switch (item.getItemId()) {
            case R.id.home:
                myIntent = new Intent(activity.getBaseContext(), MainActivity.class);
                activity.startActivity(myIntent);
                break;
            default:
        }
    }

}
