package com.example.adrian.recipeandroid;

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
public class TestLogin {
    @Rule
    public ActivityTestRule<LoginActivity> lrule=
            new ActivityTestRule<>(LoginActivity.class);


    @Test
    public void PasarTexto(){
        String texto="usuario";
        onView(withId(R.id.editTextEmail)).perform(typeText(texto),closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
        onView(withId(R.id.resultView)).check(matches(withText(texto)));
    }
}
