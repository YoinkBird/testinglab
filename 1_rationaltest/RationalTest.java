import junit.framework.TestCase;

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
}

/*
   Test method stubs:
    // method under test:
    //    public Rational (int numerator, int denominator) {
    public void testConstructor() {
    }
    // method under test:
    //    public Rational plus(Rational b)
    public void testAddition() {
    }
    // method under test:
    //    public Rational times(Rational b)
    public void testMultiplication() {
    }
    // method under test:
    //    public Rational minus(Rational b)
    public void testSubtraction() {
    }
    // method under test:
    //    public Rational divides(Rational b)
    public void testDivision() {
    }
    // method under test:
    //    public static void setTolerance( Rational epsilon )
    // set tolerance to be something high/low and see what happens
    public void testRootTolerance() {
    }
    // method under test:
    //    public boolean isLessThan( Rational r )
    public void testisLessThan() {
    }
    // method under test:
    //    public Rational abs( )
    public void testAbsolute() {
    }
    // method under test:
    //    public String toString( )
    public void testToString() {
    }
*/
/*
   These are not tested yet:
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
