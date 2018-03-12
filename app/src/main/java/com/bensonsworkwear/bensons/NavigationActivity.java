package com.bensonsworkwear.bensons;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bensonsworkwear.bensons.constants.Constants;
import com.bensonsworkwear.bensons.fragment.AboutFragment;
import com.bensonsworkwear.bensons.fragment.FeedsFragment;
import com.bensonsworkwear.bensons.fragment.NavigationFragment;
import com.bensonsworkwear.bensons.fragment.NewsFragment;
import com.bensonsworkwear.bensons.fragment.PopularTagsFragment;
import com.bensonsworkwear.bensons.fragment.SettingsFragment;
import com.bensonsworkwear.bensons.fragment.WatchLiveFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.view.Gravity.START;

public class NavigationActivity extends AppCompatActivity {

    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_ivNavigation)
    ImageView toolbar_ivNavigation;
    @BindView(R.id.toolbar_tvTitle)
    TextView tvTitle;

    @OnClick(R.id.toolbar_ivNavigation)
    void onClickNavigation() {
        openCloseDrawer();
    }

    Unbinder unbinder;

    /**
     * Runs when the activity is created. Sets the name of the activity, icons and fragments.
     *
     * @param savedInstanceState Value will be null the first time called. Otherwise it will pass the bundle.
     */

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        unbinder = ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbar);
        setToolbarTitle(getString(R.string.title_navigation_activity));
        toolbar.setNavigationIcon(null);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        replaceFragment(0);
        replaceNavigationFragment();
    }

    /**
     * Closes the drawer, if it's open it calls the superclass of {@link FragmentActivity}
     */
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(START)) {
            drawer.closeDrawer(START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Opens the drawer, if it's already open it closes it.
     */
    private void openCloseDrawer() {
        if (drawer.isDrawerOpen(START)) drawer.closeDrawer(START);
        else drawer.openDrawer(START);
    }

    /**
     * Replaces the fragment with the flContainerNavigationMenu activity.
     */
    public void replaceNavigationFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContainerNavigationMenu, NavigationFragment.newInstance(), "Navigation").commit();
    }

    /**
     * Detects the selected item and sets the activity fragment according to the selection.
     *
     * @param position The position selected.
     */
    public void replaceFragment(int position) {
        Fragment fragment = null;
        String tag = null;

        /* Parameter comes from the element pressed in the navigation drawer.
        * Pressing the first one will give the value 0 which means that the user
        * wants to go to the news section
        */
        switch (position) {
            case 0:
                fragment = NewsFragment.newInstance();
                tag = Constants.TAG_FRG_NEWS;
                break;
            case 1:
                fragment = FeedsFragment.newInstance();
                tag = Constants.TAG_FRG_FEEDS;
                break;
            case 2:
                fragment = WatchLiveFragment.newInstance();
                tag = Constants.TAG_FRG_WATCH_LIVE;
                break;
            case 3:
                fragment = PopularTagsFragment.newInstance();
                tag = Constants.TAG_FRG_POPULAR_TAGS;
                break;
            case 4:
                fragment = SettingsFragment.newInstance();
                tag = Constants.TAG_FRG_SETTINGS;
                break;
            case 5:
                fragment = AboutFragment.newInstance();
                tag = Constants.TAG_FRG_ABOUT;
                break;
        }

        replaceFragment(fragment, tag);

    }

    /**
     * Replaces the fragment with another activity. It receives the fragment that wants to be
     * used and then string for the title. To end it closes the navigation drawer.
     *
     * @param fragment The activity to be replace the fragment.
     * @param tag      The new title of the toolbar.
     */
    public void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContainerFragment, fragment, tag)
                .commit();

        setToolbarTitle(tag);
        closeNavigationDrawer();
    }

    /**
     * Sets the title on the toolbar of the activity. Receives a title of the type String
     * and replaces the current title.
     *
     * @param title The String to be set as the title of the activity.
     */
    public void setToolbarTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * If the Drawer is open it closes it.
     */
    public void closeNavigationDrawer() {
        if (drawer.isDrawerOpen(START)) drawer.closeDrawer(START);
    }

    /**
     * Unbinds all views from Butterknife to release memory before closing.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
