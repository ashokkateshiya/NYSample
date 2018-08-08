package com.nytimesexample;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.nytimesexample.articles.ArticleListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<ArticleListActivity> mTasksActivityTestRule =
            new ActivityTestRule<ArticleListActivity>(ArticleListActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
                }
            };

    @Test
    public void listOnItemClick() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (getCount() == 0) return;

        Espresso.onView(ViewMatchers.withId(R.id.item_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));


        Espresso.onView(ViewMatchers.withId(R.id.webView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public int getCount() {
        RecyclerView rv = mTasksActivityTestRule.getActivity().findViewById(R.id.item_list);
        return rv.getAdapter().getItemCount();
    }
}
