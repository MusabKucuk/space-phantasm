package Test;

import Domain.GamePlay.Collision;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Line2D;

//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class TestAngleBetweenTwoLines {

	Collision collision;

	@BeforeEach
	void setUp() {

		collision = Collision.getInstance();
	}

	@Test
	void testFor270() {
	    //REQUIRES: angle == 270
	    //MODIFIES: line1 and line2
	    //EFFECTS:  checks if the angle between two lines is 270.

		Line2D line1 = new Line2D.Double(0, 0, 1, 0);
		Line2D line2 = new Line2D.Double(0, 0, 0, 1);

		assertFalse(Math.toDegrees(collision.angleBetween2Lines(line1, line2)) == 90);
		assertTrue(Math.toDegrees(collision.angleBetween2Lines(line1, line2)) == 270);

	}

	@Test
	void testFor45() {
	    //REQUIRES: angle == 45
	    //MODIFIES: line1 and line2
	    //EFFECTS:  checks if the angle between two lines is 45.

		Line2D line1 = new Line2D.Double(0, 0, 0, 1);
		Line2D line2 = new Line2D.Double(0, 0, 1, 1);

		assertFalse(Math.toDegrees(collision.angleBetween2Lines(line1, line2)) == 0);
		assertTrue(Math.toDegrees(collision.angleBetween2Lines(line1, line2)) == 45);
		assertTrue(Math.toDegrees(collision.angleBetween2Lines(line2, line1)) == 45);

	}

	@Test
	void testFor0() {
	    //REQUIRES: angle == 0
	    //MODIFIES: line1 and line2
	    //EFFECTS:  checks if the angle between two lines is 0.

		Line2D line1 = new Line2D.Double(0, 0, 0, 1);
		Line2D line2 = new Line2D.Double(0, 0, 0, 1);

		assertFalse(Math.toDegrees(collision.angleBetween2Lines(line1, line2)) == 30);
		assertTrue(Math.toDegrees(collision.angleBetween2Lines(line1, line2)) == 0);

	}

	@Test
	void testFor60() {
	    //REQUIRES: angle == 60
	    //MODIFIES: line1 and line2
	    //EFFECTS:  checks if the angle between two lines is 60.

		Line2D line1 = new Line2D.Double(0, 0, 0, 1);
		Line2D line2 = new Line2D.Double(0, 0, Math.sqrt(3), 1);

		assertFalse(Math.toDegrees(collision.angleBetween2Lines(line1, line2)) == 30);
		assertTrue((int) Math.toDegrees(collision.angleBetween2Lines(line1, line2)) == 60);

	}

	@Test
	void testFor210() {
	    //REQUIRES: angle == 210
	    //MODIFIES: line1 and line2
	    //EFFECTS:  checks if the angle between two lines is 210.

		Line2D line1 = new Line2D.Double(0, 0, Math.sqrt(3), 0);
		Line2D line2 = new Line2D.Double(0, 0, -Math.sqrt(3), 1);

		assertFalse(Math.toDegrees(collision.angleBetween2Lines(line1, line2)) == 15);
		assertTrue((int)Math.toDegrees(collision.angleBetween2Lines(line1, line2)) == 210);
		

	}

}

