package ExpediaTestTravel.ExpediaTestTravel;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.testcomponents.ExpediaFlight.FlightTicketPage;

public class FlightTicketTest {

	private String fligthFrom = "北京";
	private String flightTo = "San Francisco";
	
	@Test
	@WebTest
	public void fligthTicketTest_3() {
				
		FlightTicketPage flightTicketPage = new FlightTicketPage();
		flightTicketPage.clickFlightLink();
		flightTicketPage.getFlightFromTextField().type(fligthFrom);
		
	}
	
	
	@BeforeMethod
	public void initWebpage() {
		Grid.driver().get("http://expedia.com");
	}


}
