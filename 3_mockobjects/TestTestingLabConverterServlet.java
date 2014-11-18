import junit.framework.*;
import com.mockobjects.servlet.*;

public class TestTestingLabConverterServlet extends TestCase {
  private static final String urlParam = "farenheitTemperature";

  public void test_bad_parameter() throws Exception {
    // declare internal vars
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request =
      new MockHttpServletRequest();
    MockHttpServletResponse response =
      new MockHttpServletResponse();

    String expectedContentFragment = "Bad Temperature";

    request.setupAddParameter(this.urlParam, "lol");
    response.setExpectedContentType("text/html");
    s.doPost(request,response);
    response.verify();
    //assertEquals("Invalid temperature: boo!\r\n",
    //             response.getOutputStreamContents());
    assertTrue(
        response.getOutputStreamContents().contains(
          expectedContentFragment)
        );
  }

  public void test_missing_parameter() throws Exception {
    // declare internal vars
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request =
      new MockHttpServletRequest();
    MockHttpServletResponse response =
      new MockHttpServletResponse();

    String expectedContentFragment = "No Temperature";

    response.setExpectedContentType("text/html");
    s.doPost(request,response);
    response.verify();
    assertTrue(
        response.getOutputStreamContents().contains(
          expectedContentFragment)
        );
  }

  public void test_boil() throws Exception {
    // declare internal vars
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request =
      new MockHttpServletRequest();
    MockHttpServletResponse response =
      new MockHttpServletResponse();

    //String expectedContentFragment = "the temperature in Austin is 100";//.0 degrees Farenheit";
    String expectedContentFragment = "212 Farenheit = 100 Celsius";//.0 degrees Farenheit";

    request.setupAddParameter(this.urlParam, "212");
    response.setExpectedContentType("text/html");
    s.doPost(request,response);
    response.verify();
    //DEBUG// System.out.println("test_boil: " + response.getOutputStreamContents());
    assertTrue(
        response.getOutputStreamContents().contains(
          expectedContentFragment)
        );
  }
}

