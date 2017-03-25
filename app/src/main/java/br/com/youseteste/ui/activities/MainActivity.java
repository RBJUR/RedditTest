package br.com.youseteste.ui.activities;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Toast;

import br.com.youseteste.R;
import br.com.youseteste.application.App;
import br.com.youseteste.ui.fragments.PostListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App app = (App) getApplicationContext();
        showFragment(PostListFragment.newInstance(), "postList");

    }

    /**
     * function to show the fragment
     *
     * @param name fragment to be shown
     * @param tag  fragment tag
     */
    private void showFragment(Fragment name, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // check if the fragment is in back stack
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(tag, 0);
        if (fragmentPopped) {
            // fragment is pop from backStack
        } else {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment, name, tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        }
    }

    /**
     * function to show the fragment
     *
     * @param current current visible fragment
     * @param tag     fragment tag
     */
    public void showFragmentWithTransition(Fragment current, Fragment newFragment, String tag, View sharedView, String sharedElementName) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // check if the fragment is in back stack
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(tag, 0);
        if (!fragmentPopped) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                current.setSharedElementReturnTransition(TransitionInflater.from(this).inflateTransition(R.transition.default_transition));
                current.setExitTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));

                newFragment.setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.default_transition));
                newFragment.setEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));
            }
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment, newFragment, tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.addSharedElement(sharedView, sharedElementName);
            fragmentTransaction.commit();
        }
    }

    /**
     * function to go back to previous fragment
     */
    private void oneStepBack() {
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() >= 2) {
            fragmentManager.popBackStackImmediate();
            fts.commit();
        } else {
            doubleClickToExit();
        }
    }

    // double back pressed function
    private static long back_pressed;

    private void doubleClickToExit() {
        if ((back_pressed + 2000) > System.currentTimeMillis())
            finish();
        else
            Toast.makeText(MainActivity.this, "Click again to exit", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

    @Override
    public void onBackPressed() {
        oneStepBack();
    }

}
