package Carsimulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class RenewPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Car car;

	public RenewPanel(Car car) {
		// TODO Auto-generated constructor stub
		this.car = car;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		float width = 2.0f;
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(width));

		g2.drawOval((int) car.getX() - car.getRadius(), (int) car.getY() - car.getRadius(), 2 * car.getRadius(), 2 * car.getRadius());

		g2.drawLine(booboo.x[8], booboo.y[8], booboo.x[1], booboo.y[1]);
		g2.drawLine(booboo.x[1], booboo.y[1], booboo.x[2], booboo.y[2]);
		g2.drawLine(booboo.x[2], booboo.y[2], booboo.x[9], booboo.y[9]);

		g2.drawLine(booboo.x[10], booboo.y[10], booboo.x[5], booboo.y[5]);
		g2.drawLine(booboo.x[5], booboo.y[5], booboo.x[6], booboo.y[6]);
		g2.drawLine(booboo.x[6], booboo.y[6], booboo.x[11], booboo.y[11]);

		g2.drawLine(booboo.x[8], booboo.y[8], booboo.x[10], booboo.y[10]);
		g2.drawLine(booboo.x[9], booboo.y[9], booboo.x[11], booboo.y[11]);

		g2.setColor(Color.RED);
		g2.drawLine(booboo.x[0], booboo.y[0], booboo.x[4], booboo.y[4]);
		g2.drawLine(booboo.x[3], booboo.y[3], booboo.x[7], booboo.y[7]);

		g2.setColor(Color.LIGHT_GRAY);
		g2.drawLine((int) car.getX(), (int) car.getY(),
				(int) CarStart.min_InterPointR.getX(), (int) CarStart.min_InterPointR.getY());
		g2.drawLine((int) car.getX(), (int) car.getY(),
				(int) CarStart.min_InterPointL.getX(), (int) CarStart.min_InterPointL.getY());
		g2.drawLine((int) car.getX(), (int) car.getY(),
				(int) CarStart.min_InterPointT.getX(), (int) CarStart.min_InterPointT.getY());
	}
}
