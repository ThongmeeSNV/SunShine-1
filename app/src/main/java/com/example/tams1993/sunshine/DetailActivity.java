package com.example.tams1993.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);





        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class DetailFragment extends Fragment {

        private final static String LOG_TAG = DetailFragment.class.getSimpleName();
        private final static String FORECAST_SHARE_HASHTAG = "#SunShineApp";
        private String strForecast;

        public DetailFragment() {

            setHasOptionsMenu(true); // to let activity know that we have option menu in fragment

        }


        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

            super.onCreateOptionsMenu(menu, inflater);

            // inflate the menu; this add items to the actionbar if it is present
            inflater.inflate(R.menu.detailfragment, menu);

            //Retrieve the share menu item
            MenuItem menuItem = menu.findItem(R.id.action_share);


            // Get the provider and hold onto it to set/change the share intent
            ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);


            if (shareActionProvider != null) {

                shareActionProvider.setShareIntent(createShareForecastIntent());

            } else {

                Log.d(LOG_TAG, "Share Action provider is NULL????");

            }


        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Intent intent = getActivity().getIntent();




            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

            if (intent !=null && intent.hasExtra(Intent.EXTRA_TEXT)) {

                strForecast = intent.getStringExtra(Intent.EXTRA_TEXT);
                ((TextView) rootView.findViewById(R.id.detail_text)).setText(strForecast);


            }

            return rootView;
        }

        private Intent createShareForecastIntent() {

            Intent ShareIntent = new Intent(Intent.ACTION_SEND);
            ShareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET); // to prevent when press back you will get back to your app instead of other application that you send data to
            ShareIntent.setType("text/plain"); // to let android know that we are going to share plain text
            ShareIntent.putExtra(Intent.EXTRA_TEXT, strForecast + FORECAST_SHARE_HASHTAG);

            return ShareIntent;


            // and don't forget to let Activity know that we are going to use option menu in fragment by use setHasOptionsMenu(true)

        }   //  end of createShareForecastIntent

    }

    // use for send data


}
