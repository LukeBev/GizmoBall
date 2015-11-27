package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import physics.LineSegment;
import physics.Vect;
import model.AbsorberGizmo;
import model.Ball;
import model.CircleGizmo;
import model.Flipper;
import model.Praser;
import model.Model;
import model.SquareGizmo;
import model.TriangleGizmo;


public  class Board extends JPanel implements Observer {
	 int x1,x2,y1,y2;

	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected Model gm;
	private AffineTransform transform = new AffineTransform();

	public Board(int w, int h, Model m) {
		// Observe changes in Model
		m.addObserver(this);
		width = w;
		height = h;
		gm = m;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	// Fix onscreen size
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		int s;
		int rows = 20;
		int cols = 20;
		int rowHeight = height/(rows);
		int colWidth = width/(cols);

		for(s=0;s<=rows;s++) {
			g2.drawLine(0, s*rowHeight, width, s*rowHeight);
			g2.setColor(Color.BLACK);
		}

		for(s=0;s<=cols;s++) {
			g2.drawLine(s*colWidth, 0, s*colWidth, height);
		}
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		//g2.drawLine(x1,y1,x2,y1);
		//g2.drawLine(x1,y1,x1,y2);
		//g2.drawLine(x2,y2,x1,y2);
		//g2.drawLine(x2,y2,x2,y1);

		// Draw all triangles
		for (TriangleGizmo tr : gm.getTriangle()) {
			for (int i = 0; i < Model.gizmos.size(); i++) {
				if (Model.gizmos.get(i).getID().equals(tr.getID())) {
					System.out.println("Drawing Triangle: " + tr.getID());
					g2.setColor(tr.getColor());
					ArrayList<LineSegment> lines = tr.getLSegments();
					System.out.println("								Drawing line" + lines);
					Polygon poly = new Polygon();
					for (LineSegment aLine : lines) {
						Vect a = aLine.p1();
						Vect b = aLine.p2();
						poly.addPoint((int) a.x(), (int) a.y());
						g2.fillPolygon(poly);

					}
				}
			}
		}
	
		// Draw left and right flipper
		for (Flipper rf : gm.getFlipper()) {
			for (int i = 0; i < Model.gizmos.size(); i++) {
				if (Model.gizmos.get(i).getID().equals(rf.getID())) {
					// gm.getFlipper().get(i).paint(g);
					if (rf.getOpCode().equals("RightFlipper")) {
						g2.setColor(rf.getColor());
						System.out.println("trans up rF");
						rf.rotateFlipper();
						double x1pos = rf.getX1();
						double y1pos = rf.getY1();
						double x2pos = (int) x1pos + 35;
						double y2pos = (int) y1pos + 40;
						transform = rf.getTrans();
						Area shape = new Area(new Rectangle2D.Double(
								x1pos + 35, y1pos + 5, 16, 40));
						Area pivotedCircle = new Area(new Ellipse2D.Double(
								x2pos, y2pos, 16, 16));
						shape.transform(transform);
						pivotedCircle.transform(transform);
						Graphics2D g2d = (Graphics2D) g;
						g2d.setColor(rf.getColor());
						g2d.fill(shape);
						g2d.setColor(Color.BLUE);
						// g2d.fill(pivotedCircle);
						g2d.setColor(Color.BLACK);
						g2d.fillOval((int) x1pos + 35, (int) y1pos, 16, 16);
					} else if (rf.getOpCode().equals("LeftFlipper")) {
						
						System.out.println("trans up LF");
						rf.rotateFlipper();
						double x1pos = rf.getX1();
						double y1pos = rf.getY1();
						double x2pos = (int) x1pos;
						double y2pos = (int) y1pos + 40;
						transform = rf.getTrans();
						Area shape = new Area(new Rectangle2D.Double(x1pos,
								y1pos + 5, 16, 40));
						Area pivotedCircle = new Area(new Ellipse2D.Double(
								x2pos, y2pos, 16, 16));
						shape.transform(transform);
						pivotedCircle.transform(transform);
						Graphics2D g2d = (Graphics2D) g;
						g2d.setColor(rf.getColor());
						g2d.fill(shape);
						g2d.setColor(Color.BLUE);
						// g2d.fill(pivotedCircle);
						g2d.setColor(Color.BLACK);
						g2d.fillOval((int) x1pos, (int) y1pos, 16, 16);
					}
				}
			}

		}

		// Draw all squares
		for (SquareGizmo sq : gm.getSquare()) {
		for(int i = 0; i < Model.gizmos.size(); i++){
				if(Model.gizmos.get(i).getID().equals(sq.getID())){
					g2.setColor(sq.getColor());
					g2.fillRect((int)sq.getX1(), (int)sq.getY1(), (int)sq.getH(), (int)sq.getH());
				}
			}
		}

		// Draw all absorber
		for (AbsorberGizmo abs : gm.getAbs()) {
			for (int i = 0; i < Model.gizmos.size(); i++) {
				if (Model.gizmos.get(i).getID().equals(abs.getID())) {
					g2.setColor(abs.getColor());
					g2.fillRect(0, (int) abs.getY1(),
							(int) abs.getX2(), (int) abs.getY2());
				}
			}
		}

		// Draw all circle
		for (CircleGizmo ci : gm.getCircle()) {
			for (int i = 0; i < Model.gizmos.size(); i++) {
				if (Model.gizmos.get(i).getID().equals(ci.getID())) {
					g2.setColor(ci.getColor());
					int x = (int) (ci.getX1() - ci.getRadius());
					int y = (int) (ci.getY1() - ci.getRadius());
					int r = (int) (2 * ci.getRadius());
					g2.fillOval(x, y, r, r);
				}
			}
		}


		// Draw balls
		for (Ball b : gm.getBall()) {
			for (int i = 0; i < Model.gizmos.size(); i++) {
				if (Model.gizmos.get(i).getID().equals(b.getID())) {
					if (b != null) {
						g2.setColor(b.getColor());
						int x = (int) (b.getX1() - b.getRadius());
						int y = (int) (b.getY1() - b.getRadius());
						int r = (int) (2 * b.getRadius());
						g2.fillOval(x, y, r, r);
					}
				}
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}
