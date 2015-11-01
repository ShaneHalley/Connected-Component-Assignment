package imageprocessing;

import java.awt.Color;

import edu.princeton.cs.introcs.Picture;

public class ConnectedComponentImage implements ComponentImage {
	private String fileLocation;
	private int threshhold = 128;
	private int width;
	private int height;

	public ConnectedComponentImage(String fileLocation) // Initialise fields
	{
		this.fileLocation = ("images/stones.jpg");
	}

	private WeightedQuickUF checkUnion(Picture pic) {
		width = pic.width();
		height = pic.height();

		WeightedQuickUF wqu = new WeightedQuickUF(width * height);
		for (int y = 0; y < height; y++) // checks all y pixels
		{
			for (int x = 0; x < width; x++)// checks all x pixels
			{
				// checks the pixel to the top
				if (y + 1 < height && pic.get(x, y).equals(pic.get(x, y + 1))) {
					wqu.union(index(x, y), index(x, y + 1));
				}

				// checks the pixel to the right
				if (x + 1 < width && pic.get(x, y).equals(pic.get(x + 1, y))) {
					wqu.union(index(x, y), index(x + 1, y));
				}
			}
		}
		return wqu;
	}

	/**
	 * a method to return the unique index of each pixel in an image
	 * 
	 * @param x
	 *            - the x value on the grid
	 * @param y
	 *            - the y value on the grid
	 * 
	 * @return the unique index of each pixel
	 */

	public int index(int x, int y) {
		return (y * (width)) + x;
	}

	public int countComponents() {
		WeightedQuickUF wqu = checkUnion(binaryComponentImage());
		return wqu.count() - 1; // -1 allows for the background root
	}

	public Picture binaryComponentImage() // Returns a binarised version of the
											// original image
	{
		Picture pic = new Picture(greyScale());

		int width = pic.width();
		int height = pic.height();
		// convert to binary
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color c = pic.get(x, y);

				if (Luminance.lum(c) >= threshhold) {
					pic.set(x, y, Color.BLACK);
				} else {
					pic.set(x, y, Color.WHITE);
				}
			}
		}

		return pic;
	}

	public Picture colourComponentImage() {

	}

	public Picture highlightComponentImage() {

	}

	public Picture greyScale() {
		Picture pic = new Picture(fileLocation);
		int width = pic.width();
		int height = pic.height();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color color = pic.get(x, y);
				Color gray = Luminance.toGray(color);
				pic.set(x, y, gray);
			}
		}
		return pic;
	}
}
