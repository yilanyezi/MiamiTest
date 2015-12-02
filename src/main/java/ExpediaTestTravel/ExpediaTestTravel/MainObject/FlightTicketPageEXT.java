package ExpediaTestTravel.ExpediaTestTravel.MainObject;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;
import com.paypal.selion.testcomponents.ExpediaFlight.FlightTicketPage;
/**
 * Extended class of FlightTicketPage
 * Using Custom SeLion element
 * @author mousumisen
 *
 */
public class FlightTicketPageEXT extends FlightTicketPage {

	public FlightTicketPageEXT() {
		super();
	}

	public FlightTicketPageEXT(String siteLocale) {
		super(siteLocale);
	}
		
	/**
	 * open Web Site and maximization
	 */
	public void openWebsite() {
		// open driver and URL
		Grid.driver().get("http://expedia.com");
		// window size will be maximized
		Grid.driver().manage().window().maximize();
	}
	
	/**
	 * Search flight and open the search result page
	 * 
	 * @param fromFlight
	 * @param toFlight
	 * @param departingDate
	 * @param returningDate
	 */
	public void searchFlight(String fromFlight, String toFlight, Date departingDate, Date returningDate) {
		getFlightLink().click();
		getFlightFromTextField().type(fromFlight);
		
		//calling wait method to check whether the element is present or not
		WebDriverWaitUtils.waitUntilElementIsPresent("css=.results");
		getFlightFromLink().click();
		getFlightToTextField().type(toFlight);
		
		//calling wait method to check whether the element is present or not
		WebDriverWaitUtils.waitUntilElementIsPresent("css=.results");
		getFlightToLink().click();
		
		//Click and wait until the web element is visible or not
		getDepartingDateTextField().clickAndExpect(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cal")));
		getDepartingAndReturningExpediaDatePicker().set(departingDate);
		
		//Click and wait until the web element is visible or not
		getReturningDateTextField().clickAndExpect(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cal")));
		getDepartingAndReturningExpediaDatePicker().set(returningDate);
		getSearchButton().click();
	}
}
