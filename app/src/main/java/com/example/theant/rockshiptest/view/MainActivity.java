package com.example.theant.rockshiptest.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.theant.rockshiptest.R;
import com.example.theant.rockshiptest.home.HomeFragment;
import com.example.theant.rockshiptest.util.ResourceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String HOME_FRAGMENT = "HomeFragment";
    private static final String FRIENDS_FRAGMENT = "FriendsFragment";
    private static final String MESSAGES_FRAGMENT = "MessagesFragment";
    private static final String WORLD_FRAGMENT = "WorldFragment";
    private static final int STARTING_TAB = 0;
    private static final String TAG = "home_frag";

    @BindView(R.id.navigation)
    AHBottomNavigation bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBottomBar();
    }

    private void initBottomBar() {
        bottomNavigationView.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
        bottomNavigationView.setAccentColor(ResourceUtils.fetchColor(this, R.color.colorPrimary));
        bottomNavigationView.setInactiveColor(ResourceUtils.fetchColor(this, R.color.colorDefaultIconNavigation));
        bottomNavigationView.setDefaultBackgroundColor(ResourceUtils.fetchColor(this, R.color.colorBackgroundNavigation));
        bottomNavigationView.setBehaviorTranslationEnabled(true);

        AHBottomNavigationItem home = new AHBottomNavigationItem("", R.drawable.ic_home);
        AHBottomNavigationItem community = new AHBottomNavigationItem("", R.drawable.ic_earth);
        AHBottomNavigationItem friends = new AHBottomNavigationItem("", R.drawable.ic_group_man);
        AHBottomNavigationItem messages = new AHBottomNavigationItem("", R.drawable.ic_messages);

        bottomNavigationView.addItem(home);
        bottomNavigationView.addItem(friends);
        bottomNavigationView.addItem(messages);
        bottomNavigationView.addItem(community);
        bottomNavigationView.setCurrentItem(STARTING_TAB);

        loadHomeFragment(HomeFragment.newInstance(), HOME_FRAGMENT);

        bottomNavigationView.setOnTabSelectedListener((pos, wasSelected) -> {
            switch (pos) {
                case 0:
                    HomeFragment homeFragment = HomeFragment.newInstance();
                    loadHomeFragment(homeFragment, HOME_FRAGMENT);
                    break;
                case 1:
                    FriendsFragment friendsFragment = FriendsFragment.newInstance();
                    loadFriendsFragment(friendsFragment, FRIENDS_FRAGMENT);
                    break;
                case 2:
                    MessagesFragment messagesFragment = MessagesFragment.newInstance();
                    loadMessagesFragment(messagesFragment, MESSAGES_FRAGMENT);
                    break;
                case 3:
                    WorldFragment worldFragment = WorldFragment.newInstance();
                    loadWorldFragment(worldFragment, WORLD_FRAGMENT);
                    break;
            }
            return true;
        });
    }

    private void loadHomeFragment(HomeFragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, tag)
                    .commit();
    }

    private void loadFriendsFragment(FriendsFragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, tag)
                    .commit();
    }

    private void loadMessagesFragment(MessagesFragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, tag)
                    .commit();
    }

    private void loadWorldFragment(WorldFragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, tag)
                    .commit();
    }

}
