package ExpediaTestTravel.ExpediaTestTravel;

import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ExpediaTestTravel.ExpediaTestTravel.MainObject.FlightTicketPageEXT;
import ExpediaTestTravel.ExpediaTestTravel.MainObject.ReviewTripPageEXT;
import ExpediaTestTravel.ExpediaTestTravel.MainObject.SearchResultPageEXT;

import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;

/**
 * testCase : 
 * 		1. Book Cheapest flight
 * 		2. Book shortest duration flight
 * 		3. Book Duration of flight under 15 hours
 * 
 * @author mousumisen
 *
 */
public class BookingCheapestAndShortestDurationFlight {
	private String fromFlight = "Beij";
	private String toFlight = "san fra";
	private Date departureDate;
	private Date returningDate;
	private int journeyStartDay = 21;
	private String journeyDuration = "15h0m";
	private int indexofLowestPriceFlight = 0;
	private int indexofShortestFlight = 2;
	
	FlightTicketPageEXT flightTicketPageEXT = new FlightTicketPageEXT();
	SearchResultPageEXT searchResultPageEXT = new SearchResultPageEXT();
	ReviewTripPageEXT reviewTripPageEXT = new ReviewTripPageEXT();

	/**
	 * generate return date of your journey
	 */
	@BeforeTest
	public void init() {
		Date departureDate = this.genarateNextMonday();
		Date returningDate = this.genarateReturnDay(departureDate, journeyStartDay);
		this.departureDate = departureDate;
		this.returningDate = returningDate;
	}

	/**
	 * Test Case 1: select the flight of Duration under 15 hours
	 */

	public void selectTheShorterAndLowestFlight() {
		this.searchFlightforBooking();
		searchResultPageEXT.sortFlight(indexofLowestPriceFlight);
		searchResultPageEXT.selectShorterFlight(journeyDuration);
		WebDriverWaitUtils.waitUntilElementIsPresent("css=#flightModule0");
		searchResultPageEXT.selectShorterFlight(journeyDuration);
		searchResultPageEXT.clickDeclineHotelBookingLink();
		System.out.println("Successfully selected Shortest Duration Flight from Beijing to San Fran. under duration 15 hours.");
	}
	
	/**
	 * Test Case 2: Book the Cheapest flight
	 * @throws InterruptedException 
	 */
	@Test
	@WebTest
	public void selectCheapestFlight() throws InterruptedException {
		this.searchFlightforBooking();
		searchResultPageEXT.sortFlight(indexofLowestPriceFlight);
		WebDriverWaitUtils.waitUntilElementIsPresent("css=#flightModuleList");
		
		searchResultPageEXT.selectFirstFlight();
		WebDriverWaitUtils.waitUntilElementIsPresent("css=.title-city-text");
		WebDriverWaitUtils.waitUntilElementIsPresent("css=#flightModule0");
		
		searchResultPageEXT.selectFirstFlight();
		searchResultPageEXT.clickDeclineHotelBookingLink();
		reviewTripPageEXT.ReviewTripDetails();
	}
	
	/**
	 * Test Case 3: Book shortest duration flight
	 */
	
	public void selectShortestFlight() {
		this.searchFlightforBooking();
		searchResultPageEXT.sortFlight(indexofShortestFlight);
		WebDriverWaitUtils.waitUntilElementIsPresent("css=#flightModuleList");
		
		searchResultPageEXT.selectFirstFlight();
		WebDriverWaitUtils.waitUntilElementIsPresent("css=.title-city-text");
		WebDriverWaitUtils.waitUntilElementIsPresent("css=#flightModule0");
		
		searchResultPageEXT.selectFirstFlight();
		searchResultPageEXT.clickDeclineHotelBookingLink();
		
	}

	/**
	 * method to generate next Monday
	 * @return
	 */
	public Date genarateNextMonday() {
		Date today = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(today);
		ca.set(Calendar.WEEK_OF_YEAR, ca.get(Calendar.WEEK_OF_YEAR) + 1);
		ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return ca.getTime();
	}

	/**
	 * method to generate return date
	 * @return
	 */
	public Date genarateReturnDay(Date date, int during) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DAY_OF_YEAR, during);
		return ca.getTime();
	}
	
	/**
	 * Search flight for booking
	 * @return
	 */
	public void searchFlightforBooking() {
		flightTicketPageEXT.openWebsite();

		flightTicketPageEXT.searchFlight(fromFlight, toFlight, departureDate, returningDate);
		WebDriverWaitUtils.waitUntilElementIsPresent("css=#flightModule0");
	}
}
