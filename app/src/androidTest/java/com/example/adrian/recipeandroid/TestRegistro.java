package com.example.adrian.recipeandroid;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TestRegistro {
    @Rule
    public ActivityTestRule<SignInActivity> rule=
            new ActivityTestRule<>(SignInActivity.class,true,false);

    @Test
    public void EnviarDato(){
        Intent intent=new Intent();
        rule.launchActivity(intent);
        onView(withId(R.id.editTextMail)).perform(typeText("usuario@mail.com"),closeSoftKeyboard());
        onView(withId(R.id.buttonSignIn)).perform(click());
        onView(withId(R.id.editTextMail)).check(matches(withText("usuario@mail.com")));
    }
}
