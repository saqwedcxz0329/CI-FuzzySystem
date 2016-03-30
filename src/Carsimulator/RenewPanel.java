package Carsimulator;

import java.awt.Graphics;

import javax.swing.JPanel;

public class RenewPanel extends JPanel {
	private double carTopX, carTopy;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		carTopX = Car.getRadius() * Math.cos(Math.toRadians(Car.getPhi()));
		carTopy = Car.getRadius() * Math.sin(Math.toRadians(Car.getPhi()));

		g.drawOval((int) Car.getX() - Car.getRadius(), (int) Car.getY() - Car.getRadius(), 2 * Car.getRadius(), 2 * Car.getRadius());

		g.drawLine(booboo.x[8], booboo.y[8], booboo.x[1], booboo.y[1]);
		g.drawLine(booboo.x[1], booboo.y[1], booboo.x[2], booboo.y[2]);
		g.drawLine(booboo.x[2], booboo.y[2], booboo.x[9], booboo.y[9]);

		g.drawLine(booboo.x[10], booboo.y[10], booboo.x[5], booboo.y[5]);
		g.drawLine(booboo.x[5], booboo.y[5], booboo.x[6], booboo.y[6]);
		g.drawLine(booboo.x[6], booboo.y[6], booboo.x[11], booboo.y[11]);

		g.drawLine(booboo.x[0], booboo.y[0], booboo.x[4], booboo.y[4]);
		g.drawLine(booboo.x[3], booboo.y[3], booboo.x[7], booboo.y[7]);

		g.drawLine(booboo.x[8], booboo.y[8], booboo.x[10], booboo.y[10]);
		g.drawLine(booboo.x[9], booboo.y[9], booboo.x[11], booboo.y[11]);

		g.drawLine((int) Car.getX(), (int) Car.getY(),
				(int) FuzzySystem.min_InterPointR.getX(), (int) FuzzySystem.min_InterPointR.getY());
		g.drawLine((int) Car.getX(), (int) Car.getY(),
				(int) FuzzySystem.min_InterPointL.getX(), (int) FuzzySystem.min_InterPointL.getY());
		g.drawLine((int) Car.getX(), (int) Car.getY(),
				(int) FuzzySystem.min_InterPointT.getX(), (int) FuzzySystem.min_InterPointT.getY());
	}
}
