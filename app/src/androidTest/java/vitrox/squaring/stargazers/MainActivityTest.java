package vitrox.squaring.stargazers;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import vitrox.squaring.stargazers.Common.Config;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;



@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void checkifAllisDisplayed() throws InterruptedException {
        onView(withId(R.id.repo_edittext)).check(matches(isDisplayed()));
        onView(withId(R.id.user_edittext)).check(matches(isDisplayed()));
        onView(withId(R.id.search_button)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @Test
    public void checkEmptyOwnerandRepo() throws InterruptedException {
        onView(withId(R.id.search_button)).perform(click());
        String error = Config.EMPTY_ERROR;
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(error)))
                .check(matches(isDisplayed()));
    }
    @Test
    public void checkWrongOwnerAndRepo() throws InterruptedException {
        onView(withId(R.id.user_edittext)).perform(typeText("spongebob"));
        onView(withId(R.id.repo_edittext)).perform(typeText("testwrong"));
        onView(withId(R.id.search_button)).perform(click());
        String error = Config.WRONG_ERROR;
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(error)))
                .check(matches(isDisplayed()));
    }
    @Test
    public void checkGoodOwnerAndRepo() throws InterruptedException {
        onView(withId(R.id.user_edittext)).perform(typeText("square"));
        onView(withId(R.id.repo_edittext)).perform(typeText("okio"));
        onView(withId(R.id.search_button)).perform(click());
        onView(withId(R.id.recycler_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }


}
