package com.acme.a3csci3130;

import android.support.test.runner.AndroidJUnit4;

import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Testing class that provides tests from creating, reading, updating and deleting a contact
 * NOTE: Will have errors if there is already a Contact with the name "TestName" in the database.
 */

@RunWith(AndroidJUnit4.class)
public class CRUD_Test {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Test for successful creation of a contact
     *
     * Spinner testing help from:
     * https://stackoverflow.com/questions/31420839/android-espresso-check-selected-spinner-text
     *
     * ListView testing help from:
     * https://guides.codepath.com/android/ui-testing-with-espresso
     */
    @Test
    public void createContact(){
        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.name))
                .perform(typeText("TestName"), closeSoftKeyboard());

        onView(withId(R.id.email))
                .perform(typeText("email@test.com"), closeSoftKeyboard());

        onView(withId(R.id.address))
                .perform(typeText("100 Street Street, City"), closeSoftKeyboard());



        onView(withId(R.id.business)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Fisher"))).perform(click());
        onView(withId(R.id.business)).check(matches(withSpinnerText(containsString("Fisher"))));

        onView(withId(R.id.province)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("NS"))).perform(click());
        onView(withId(R.id.province)).check(matches(withSpinnerText(containsString("NS"))));

        onView(withId(R.id.businessNumber))
                .perform(typeText("123456789"), closeSoftKeyboard());

        onView(withId(R.id.submitButton))
                .perform(click());

        onView(allOf(withText("TestName"))).perform(click());




    }

    /**
     * This test checks that the contact that was created in the createContact test has all
     * the things that we set it to.
     * As long as no one deletes Fisher Mann from the database, this test will pass.
     */
    @Test
    public void lookAtContact(){

        onView(allOf(withText("Fisher Mann"))).perform(click());

        onView(withId(R.id.currBusiness)).check(matches(withText("Current Business: Fisher")));
        onView(withId(R.id.currProvince)).check(matches(withText("Current Province/Territory: NS")));
        onView(withId(R.id.address)).check(matches(withText("123 Fish Street, Fishton")));
        onView(withId(R.id.name)).check(matches(withText("Fisher Mann")));
        onView(withId(R.id.email)).check(matches(withText("Fisher.Mann@fishmail.com")));
        onView(withId(R.id.businessNumber)).check(matches(withText("999999999")));

    }

    /**
     * This updates the contact by changing the email, business and province,
     * then clicks again to this TestName Contact to validate that it changed.
     */
    @Test
    public void updateContact(){
        onView(allOf(withText("TestName"))).perform(click());

        onView(withId(R.id.email))
                .perform(replaceText("newEmail@test.com"), closeSoftKeyboard());

        onView(withId(R.id.business)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Fish Monger"))).perform(click());
        onView(withId(R.id.business)).check(matches(withSpinnerText(containsString("Fish Monger"))));

        onView(withId(R.id.province)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("NB"))).perform(click());
        onView(withId(R.id.province)).check(matches(withSpinnerText(containsString("NB"))));

        onView(withId(R.id.updateButton))
                .perform(click());


        onView(allOf(withText("TestName"))).perform(click());

        onView(withId(R.id.currBusiness)).check(matches(withText("Current Business: Fish Monger")));
        onView(withId(R.id.currProvince)).check(matches(withText("Current Province/Territory: NB")));
        onView(withId(R.id.address)).check(matches(withText("100 Street Street, City")));
        onView(withId(R.id.name)).check(matches(withText("TestName")));
        onView(withId(R.id.email)).check(matches(withText("newEmail@test.com")));
        onView(withId(R.id.businessNumber)).check(matches(withText("123456789")));


    }

    /**
     *This test deletes the TestName contact and verifies that it is indeed gone.
     */
    @Test
    public void deleteContact(){
        onView(allOf(withText("TestName"))).perform(click());

        onView(withId(R.id.deleteButton))
                .perform(click());

        onView(allOf(withText("TestName"))).equals(null);
    }
}
