
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.action.Swipe;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.android.apps.common.testing.accessibility.framework.ClickableSpanInfoCheck;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least one item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * When we click on an item, it shows the neighbour activity details
     */
    @Test
    public void myNeighboursList_clickItem_shouldOpenActivity() {
        // When perform a click on item position
        onView(Matchers.allOf(ViewMatchers.isDisplayed(),ViewMatchers.withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        // Then : check if activity details is displayed
        onView(ViewMatchers.withId(R.id.neighbourInfo)).check(matches(ViewMatchers.isDisplayed()));

    }

    /**
     * We ensure that when the neighbour activity starts, the TextView shows the correct neighbour name
     */
    @Test
    public void correctNeighbourName_onNeighbourInfo() {
        //when perform a click on item position
        onView(Matchers.allOf(ViewMatchers.isDisplayed(),ViewMatchers.withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        //Then : check if name contains neighbour's name
        onView(withId(R.id.item_list_avatarName)).check(matches(withText("Caroline")));


    }


    /**
     * We ensure that the favorite button is functioning properly
     */
    @Test
    public void favoriteButton_isFunctioningProperly() {
        //Given : The Favorite List is empty
        //Perform a click on item position to open the activity details
        onView(Matchers.allOf(ViewMatchers.isDisplayed(),ViewMatchers.withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, ViewActions.click()));

        // Then : click on favorite button
        onView(withId(R.id.favorite)).perform(ViewActions.click());

        // Then : return back
        onView(withId(R.id.back_button)).perform(ViewActions.click());

        // Then : swipe to the right
       // onView(withId(R.id.tabItem2)).perform(ViewActions.click()); (Pourquoi cette méthode de clique ne passe pas ?)
        onView(Matchers.allOf(ViewMatchers.isDisplayed(),ViewMatchers.withId(R.id.list_neighbours)))
                .perform(ViewActions.swipeLeft());

        // Then : check if the item is on the favorite list
        onView(Matchers.allOf(ViewMatchers.isDisplayed(),ViewMatchers.withId(R.id.list_neighbours)))
                .check(matches(ViewMatchers.withId(6)));
              //  .check(withItemCount(1));

        // Then : click on the item
        onView(Matchers.allOf(ViewMatchers.isDisplayed(),ViewMatchers.withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, ViewActions.click()));

        // Then : click on favorite button
        onView(withId(R.id.favorite)).perform(ViewActions.click());

        // Then : return back
        onView(withId(R.id.back_button)).perform(ViewActions.click());

        // Then : check if the Favorite list is empty
        onView(Matchers.allOf(ViewMatchers.isDisplayed(),ViewMatchers.withId(R.id.list_neighbours)))
                .check(withItemCount(0));


    }


}