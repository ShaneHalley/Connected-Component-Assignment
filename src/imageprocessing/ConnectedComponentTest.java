package imageprocessing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConnectedComponentTest {

	@Test
	public void testWeightedUnion() {
		WeightedQuickUF wqu = new WeightedQuickUF(10);
		wqu.union(1, 2);
		wqu.union(2, 3);
		assertTrue(wqu.connected(1, 3));
		assertFalse(wqu.connected(1, 8));
	}

}
