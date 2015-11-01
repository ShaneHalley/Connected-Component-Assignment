package imageprocessing;

import edu.princeton.cs.introcs.Picture;

public interface ComponentImage

{
	public int countComponents();

	public Picture binaryComponentImage();

	public Picture colourComponentImage();

	public Picture highlightComponentImage();
}
