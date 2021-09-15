package shutils.gui;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * This class allows to draw an image and to automatically resize the image to the dimensions of this component. The original image is stretched to fill the entire area of the component. Even if this component inherits from JLabel, the text functionality of the component is not granted to work if the image is displayed.
 * @author Matteo Nardini
 *
 */

public class AvatarImageDisplay extends JLabel
{
	/**
	 *
	 */
	private static final long serialVersionUID = 346336364175608789L;
	/**
	 * The image to be displayed and resized.
	 */
	private ImageIcon img;

	/**
	 * Creates a new AvatarImageDisplay with display image set to {@code null};
	 */
	public AvatarImageDisplay()
	{
		this(null);
	}

	/**
	 * Creates a new AvatarImageDisplay with a given image.
	 * @param icon The image to be displayed and resized in the component.
	 */
	public AvatarImageDisplay(ImageIcon icon)
	{
		super();
		this.img = icon;
	}

	/**
	 * Paints the component, filling it with a resized version of the image. The original image is stretched to fill the entire area of the component.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		if (this.img != null)
		{
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g.drawImage(this.img.getImage(), 0, 0, getWidth(), getHeight(), this);
		}
	}

	/**
	 * Allows to update the current image being displayed by the component.
	 * @param i The new image to be displayed by the component.
	 */
	public void setIcon(ImageIcon i)
	{
		this.img = i;
		super.setIcon(i);
	}
}
