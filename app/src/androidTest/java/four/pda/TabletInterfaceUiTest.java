package four.pda;

import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import four.pda.ui.article.NewsActivity;
import four.pda.ui.article.NewsActivity_;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Klishin Pavel on 02.03.2016.
 * Tablet Device is strongly recommended!
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
@Ignore
public class TabletInterfaceUiTest {

	private UiDevice device;
	static final String APP_ID = BuildConfig.APPLICATION_ID;

	@Rule
	public ActivityTestRule<NewsActivity> activityTestRule = new ActivityTestRule(NewsActivity_.class);

	@Before
	public void startMainActivityFromHomeScreen() throws RemoteException {
		// Initialize UiDevice instance
		device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
		device.wakeUp();
	}

	@Test
	public void tabletInterfaceTest() throws RemoteException {

		device.waitForIdle();
		device.setOrientationRight();
		device.waitForWindowUpdate(APP_ID, 100);

		onView(withId(R.id.all_category_view))
				.check(matches(isDisplayed()))
				.check(matches(isClickable()));
		onView(withId(R.id.news_category_view))
				.check(matches(isDisplayed()))
				.check(matches(isClickable()));
		onView(withId(R.id.articles_category_view))
				.check(matches(isDisplayed()))
				.check(matches(isClickable()));
		onView(withId(R.id.reviews_category_view))
				.check(matches(isDisplayed()))
				.check(matches(isClickable()));
		onView(withId(R.id.software_category_view))
				.check(matches(isDisplayed()))
				.check(matches(isClickable()));
		onView(withId(R.id.games_category_view))
				.check(matches(isDisplayed()))
				.check(matches(isClickable()));
		onView(withId(R.id.about_view))
				.check(matches(isDisplayed()))
				.check(matches(isClickable()));
		onView(withId(R.id.login_view))
				.check(matches(isDisplayed()))
				.check(matches(isClickable()));
		onView(withId(R.id.toolbar))
				.check(matches(isDisplayed()));
	}

	@Test
	public void tabletInterfaceDrawerSwipeTest() throws RemoteException, UiObjectNotFoundException {

		tabletInterfaceTest();

		for (int i = 0; i <= 41; i++) {
			onView(withId(R.id.recycler_view)).perform(swipeUp());
			if (i == 10)
				device.setOrientationNatural();
			if (i == 20)
				device.setOrientationLeft();
			if (i == 30)
				device.setOrientationRight();
			if (i == 40)
				device.setOrientationNatural();
		}
	}
}
