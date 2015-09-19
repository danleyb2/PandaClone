/**
 * @author ndieks
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Bamboo extends Rectangle {

	public ArrayList<Bug> bugs = new ArrayList<Bug>(); // bugs on the bamboo

	public boolean hasPanda = false;
	static int position;

	public int bugSpace = 60;
	public int myPosition;

	private Bug lastBug;

	public Bamboo(int x) {
		this.x = x;
		this.y = 0;// constant
		this.width = 80;// constant
		this.height = PandaClone.CANVAS_HEIGHT;// constant
		position++;
		myPosition = position;
		this.addBug();// TODO: lots of redundant code
		this.addBug();
		this.addBug();
		this.addBug();
		this.addBug();
		this.addBug();
		this.addBug();
		this.addBug();
		this.addBug();
		this.addBug();

	}

	public void paint(Graphics2D graphics2d) {
		graphics2d.setColor(Color.GREEN);
		graphics2d.fillRect(this.x, this.y, this.width, this.height);
	}

	public void addPanda() {
		this.hasPanda = true;

	}

	public void addBug() {
		// get last bugs y....
		if (this.getLastBug() == null) {
			this.setLastBug(new Bug(this));
		} else {
			this.setLastBug(new Bug(this, this.getLastBug().y
					+ this.getLastBug().height + bugSpace));
		}
		boolean addBug = new Random().nextBoolean();

		// add or don't add the bug
		// TODO: increase probability of adding the bug
		// TODO: compensate for possibility of loosing all the bugs

		if (/* addBug */true)
			bugs.add(this.getLastBug());

	}

	public Bug getLastBug() {
		return lastBug;
	}

	public void setLastBug(Bug lastBug) {
		this.lastBug = lastBug;
	}
}
