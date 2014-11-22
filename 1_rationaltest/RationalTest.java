import junit.framework.TestCase;
import org.junit.Test;

public class RationalTest extends TestCase {

    protected Rational HALF;

    protected void setUp() {
      HALF = new Rational( 1, 2 );
    }

    // Create new test
    public RationalTest (String name) {
        super(name);
    }

    public void testEquality() {
        assertEquals(new Rational(1,3), new Rational(1,3));
        assertEquals(new Rational(1,3), new Rational(2,6));
        assertEquals(new Rational(3,3), new Rational(1,1));
    }

    // Test for nonequality
    public void testNonEquality() {
        assertFalse(new Rational(2,3).equals(
            new Rational(1,3)));
    }

    public void testAccessors() {
    	assertEquals(new Rational(2,3).numerator(), 2);
    	assertEquals(new Rational(2,3).denominator(), 3);
    }

    public void testRoot() {
        Rational s = new Rational( 1, 4 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
        assertTrue( sRoot.isLessThan( HALF.plus( Rational.getTolerance() ) ) 
                        && HALF.minus( Rational.getTolerance() ).isLessThan( sRoot ) );
    }

    public static void main(String args[]) {
        String[] testCaseName = 
            { RationalTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }

    /* my tests */
    // method under test:
    //    public Rational (int numerator, int denominator) {
    // http://junit.sourceforge.net/javadoc/org/junit/Test.html#expected()
    @Test(expected=ArithmeticException.class)
    public void testConstructorArithmeticException() {
      Rational divByZero = new Rational(0,0);
    }
    // method under test:
    //    public Rational plus(Rational b)
    public void testAddition() {
      assertEquals( new Rational(2,3).plus( new Rational(2,3)), new Rational(4,3));
      assertEquals( new Rational(-2,3).plus( new Rational(2,3)), new Rational(0,1));
      assertEquals( new Rational(13,20).plus( new Rational(2,3)), new Rational(39 + 40,60));
    }
    // method under test:
    //    public Rational times(Rational b)
    public void testMultiplication() {
      assertEquals( new Rational(2,3).times( new Rational(2,3)), new Rational(4,9));
    }
    // method under test:
    //    public Rational minus(Rational b)
    public void testSubtraction() {
      assertEquals( new Rational(2,3).minus( new Rational(2,3)), new Rational(0,1));
      assertEquals( new Rational(2,3).minus( new Rational(1,3)), new Rational(1,3));
    }
    // method under test:
    //    public Rational divides(Rational b)
    public void testDivision() {
      assertEquals( new Rational(2,3).divides( new Rational(2,3)), new Rational(1,1));
    }
    // method under test:
    //    public static void setTolerance( Rational epsilon )
    //    public static Rational getTolerance( ) {
    // set tolerance to be something high/low and see what happens
    public void testRootTolerance() {
      Rational testMe = new Rational(2,3);
      // change tolerance, then verify that it was updated
      // setting tolerance to 2x previous value to remain flexible
      Rational currentTolerance = testMe.getTolerance();
      Rational newTolerance = currentTolerance.times(new Rational(2,1));
      testMe.setTolerance(newTolerance);
      assertNotSame(currentTolerance, testMe.getTolerance());
      
    }
    // method under test:
    //    public boolean isLessThan( Rational r )
    public void testIsLessThan() {
      assertEquals( new Rational(1,3).isLessThan( new Rational(2,3)), true);
      assertEquals( new Rational(2,3).isLessThan( new Rational(2,3)), false);
      assertEquals( new Rational(3,3).isLessThan( new Rational(2,3)), false);
    }
    // method under test:
    //    public Rational abs( )
    public void testAbsolute() {
      assertEquals( new Rational(-3,3).abs(), new Rational(3,3));
    }
    // method under test:
    //    public String toString( )
    public void testToString() {
      assertEquals(new Rational(2,3).toString(), "2/3");
      assertNotSame(new Rational(2,3).toString(), "2/3");
    }
}

/*
   These are to be tested by me:
    public Rational (int numerator, int denominator) {
    public Rational plus(Rational b) {
    public Rational times(Rational b) {
    public Rational minus(Rational b) {
    public Rational divides(Rational b) {
    // set tolerance to be something high/low and see what happens
    public static void setTolerance( Rational epsilon ) {
    public boolean isLessThan( Rational r ) {
    public Rational abs( ) {
    public String toString( ) {
   Skip private methods:
    private static int gcd(int x, int y) {
    private static int lcm(int x, int y) {
 */
