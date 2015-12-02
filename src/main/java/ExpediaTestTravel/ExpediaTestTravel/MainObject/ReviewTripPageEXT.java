package ExpediaTestTravel.ExpediaTestTravel.MainObject;

import java.util.Set;

import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.testcomponents.ExpediaFlight.ReviewTripPage;

/**
 * Extended class of ReviewTripPage
 * Using Custom SeLion element
 * @author mousumisen
 *
 */
public class ReviewTripPageEXT extends ReviewTripPage {

	public ReviewTripPageEXT() {
		super();
	}

	public ReviewTripPageEXT(String siteLocale) {
		super(siteLocale);
	}
	
	private String reviewPageTitle = "Review";
	
	/**
	 * switch window method
	 */
	public void switchWindow() {
		String window = Grid.driver().getWindowHandle();
		Set<String> windows = Grid.driver().getWindowHandles();

		for (String windowName : windows) {
			if (!window.equals(windowName)) {
				window = windowName;
			}
		}
		Grid.driver().switchTo().window(window);
	}
	
	/**
	 * check test result page i.e. review trip page
	 */
	public void ReviewTripDetails() {
		getReviewTripLabel().getText().contains(reviewPageTitle);
		switchWindow();
		System.out.println("Flight Booked successfully");
	}



}
