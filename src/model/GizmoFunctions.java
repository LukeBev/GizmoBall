package model;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

import physics.Vect;
import controller.MouseEventListener;
import view.BallPropertiesWindow;
import view.BuildModeGUI;

// this class will have a lot of different methods to change info about gizmos
public class GizmoFunctions {
	private static Model m;
	int index;
	int count = -1;
	// public static ArrayList<Gizmo> g = Model.gizmos;
	public static String connect_id_1 = "none";
	public static String connect_id_2 = "none";

	public GizmoFunctions(Model model) {
		this.m = model;
	}

	public void findGizmo(int x, int y) {
		boolean found = false;
		System.out.println("X passed: " + x);
		System.out.println("Y passed: " + y);
		for (Gizmo g : Model.gizmos) {
			count++;
			if (!g.getOpCode().equals("Circle")
					&& !g.getOpCode().equals("Ball")) {
				if (g.getX1() <= x && x < (g.getX1() + g.getWidth())
						&& g.getY1() <= y && y < (g.getY1() + g.getHeight())) {
					System.out.println("Gizmo Found");
					MouseEventListener.gizmo_found = true;
					MouseEventListener.gizmo_index = count;
					System.out.println("gizmo in location... GizmoFunctions");
					found = true;
					// BuildShapes.draw_shape = false;
					// return true;
				}

			} else {
				System.out.println("x = " + g.getX1());

				if (g.getX1() - 12.5 <= x
						&& x < ((g.getX1() - 12.5) + g.getWidth())
						&& g.getY1() - 12.5 <= y
						&& y < ((g.getY1() - 12.5) + g.getHeight())) {
					System.out.println("Gizmo Found");
					MouseEventListener.gizmo_found = true;
					MouseEventListener.gizmo_index = count;
					found = true;
					// BuildShapes.draw_shape = false;
					// return true;
				}
			}
			if (found) {
				System.out.println("Gizmo functions... gizmo found");
				BuildShapes.draw_shape = false;
			} else {
				System.out.println("Gizmo functions... not found");
				BuildShapes.draw_shape = true;
			}
		}

		// BuildShapes.draw_shape = true;
		// return false;
	}

	public void findConnectGizmos(int x, int y) {
		System.out.println("X passed: " + x);
		System.out.println("Y passed: " + y);
		for (Gizmo g : Model.gizmos) {
			count++;
			if (!g.getOpCode().equals("Circle")) {
				if (g.getX1() <= x && x < (g.getX1() + g.getWidth())
						&& g.getY1() <= y && y < (g.getY1() + g.getHeight())) {
					System.out.println("Gizmo Found");
					MouseEventListener.gizmo_found = true;
					// MouseEventListener.gizmo_index = count;
					if (connect_id_1.equals("none")) {
						connect_id_1 = g.getID();
					} else {
						connect_id_2 = g.getID();
					}
				}

			} else {
				System.out.println("x = " + g.getX1());

				if (g.getX1() - 12.5 <= x
						&& x < ((g.getX1() - 12.5) + g.getWidth())
						&& g.getY1() - 12.5 <= y
						&& y < ((g.getY1() - 12.5) + g.getHeight())) {
					System.out.println("Gizmo Found");
					MouseEventListener.gizmo_found = true;
					// MouseEventListener.gizmo_index = count;
					if (connect_id_1.equals("none")) {
						connect_id_1 = g.getID();
					} else {
						connect_id_2 = g.getID();
					}
				}
			}

		}
	}

	public void moveGizmo(int x, int y, int index) {
		System.out.println("Index = " + index);
		boolean available = BuildShapes.bl.isAvailable(x, y);
		if (available) {
			if (!Model.gizmos.get(index).getOpCode().equals("Circle")
					&& !Model.gizmos.get(index).getOpCode().equals("Ball")) {
				// this is where the problem with the moving traingles is here
				Model.gizmos.get(index).setX1(x);
				Model.gizmos.get(index).setY1(y);
			} else {
				Model.gizmos.get(index).setX1(x + 12.5);
				Model.gizmos.get(index).setY1(y + 12.5);
			}
		} else {
			BuildModeGUI.info_bar.setForeground(Color.red);
			BuildModeGUI.info_bar.setText("Can't move gizmo to this location");
		}

		BuildModeGUI.info_bar.setForeground(Color.blue);
		BuildModeGUI.info_bar.setText("Moving "
				+ Model.gizmos.get(index).getOpCode() + " gizmo");
		BuildModeGUI.window.repaint();
	}

	public void deletedGizmo(int index) {
		System.out.println("Array Size: " + Model.gizmos.size());
		// System.out.println("Deleting Gizmo: " +
		// Model.gizmos.get(index).getID());
		System.out.println("Remove Index: " + index);
		BuildModeGUI.info_bar.setText("Deleteing "
				+ Model.gizmos.get(index).getOpCode() + " gizmo");
		Model.gizmos.remove(index);
		MouseEventListener.gizmo_found = false;
		BuildModeGUI.info_bar.setForeground(Color.blue);

		/*
		 * Last resort Model.gizmos.get(index).setX1(1000);
		 */

		System.out.println("Array Size: " + Model.gizmos.size());

		BuildModeGUI.window.repaint();
		// BuildModeGUI.window.dispose();
		// BuildModeGUI bmg = new BuildModeGUI(model);
	}

	public void rotateGizmo(int index, int xpos, int ypos) {
		String to_rotate = Model.gizmos.get(index).getID();
		System.out.println("Rotating gizmo: " + to_rotate);
		for (TriangleGizmo t : m.getTriangle()) {
			if (t.getX1() <= xpos && xpos <= t.getX1() + t.getWidth()
					&& t.getY1() <= ypos && ypos <= t.getY1() + t.getHeight()) {
				t.rotate();
				Rotation r = new Rotation(t.getID());
				m.addRotation(r);
				System.out.println(r.getID());
			}

			/*
			 * if(Model.gizmos.get(index).getOpCode().equals("Triangle")){
			 * for(int i = 0; i < Model.triangles.size(); i ++){
			 * if(Model.triangles.get(i).getID().equals(to_rotate)){
			 * System.out.println("Size: " + Model.gizmos.size());
			 * System.out.println("Triangle size: " + m.getTriangle().size()) ;
			 * BuildModeGUI.info_bar.setForeground(Color.blue);
			 * BuildModeGUI.info_bar.setText("Rotating Triange Gizmo");
			 * Model.triangles.get(i).rotate(); m.addRotation(new
			 * Rotation(Model.gizmos.get(i).getID()));
			 * System.out.println(m.getRotations
			 * ().get(m.getRotations().size()-1).getID());
			 * //Model.gizmos.remove(index); System.out.println("Size: " +
			 * Model.gizmos.size()); System.out.println("Triangle size: " +
			 * m.getTriangle().size()) ;
			 */

			else {
				BuildModeGUI.info_bar.setForeground(Color.red);
				BuildModeGUI.info_bar.setText("Cant rotate this gizmo");
				System.out.println("Can't rotate this gizmo");
			}
		}
		BuildModeGUI.window.repaint();
	}

	public void changeGizmoColour(int index, int xpos, int ypos, Color c) {

		String OpCode = Model.gizmos.get(index).getOpCode();
		if (OpCode.equals("Square")) {
			for (SquareGizmo s : m.getSquare()) {
				if (s.getX1() <= xpos && xpos <= s.getX1() + s.getWidth()
						&& s.getY1() <= ypos
						&& ypos <= s.getY1() + s.getHeight()) {
					s.setColor(c);
					s.setDefaultColour(c);
				}
			}
		} else if (OpCode.equals("Triangle")) {
			for (TriangleGizmo t : m.getTriangle()) {
				if (t.getX1() <= xpos && xpos <= t.getX1() + t.getWidth()
						&& t.getY1() <= ypos
						&& ypos <= t.getY1() + t.getHeight()) {
					t.setColor(c);
					t.setDefaultColour(c);
				}
			}
		} else if (OpCode.equals("Circle")) {
			for (CircleGizmo cr : m.getCircle()) {
				if (cr.getX1() - 12.5 <= xpos
						&& xpos <= (cr.getX1() - 12.5) + cr.getWidth()
						&& cr.getY1() - 12.5 <= ypos
						&& ypos <= (cr.getY1() - 12.5) + cr.getHeight()) {
					cr.setColor(c);
					cr.setDefaultColour(c);
				}
			}
		} else if (OpCode.equals("Absorber")) {
			for (AbsorberGizmo a : m.getAbs()) {
				if (a.getX1() <= xpos && xpos <= a.getX1() + a.getWidth()
						&& a.getY1() <= ypos
						&& ypos <= a.getY1() + a.getHeight()) {
					a.setColor(c);
				}
			}
		} else if (OpCode.equals("LeftFlipper")) {
			for (Flipper f : m.getFlipper()) {
				if (f.getX1() <= xpos && xpos <= f.getX1() + f.getWidth()
						&& f.getY1() <= ypos
						&& ypos <= f.getY1() + f.getHeight()) {
					f.setColor(c);
				}
			}
		} else if (OpCode.equals("RightFlipper")) {
			for (Flipper fl : m.getFlipper()) {
				if (fl.getX1() <= xpos && xpos <= fl.getX1() + fl.getWidth()
						&& fl.getY1() <= ypos
						&& ypos <= fl.getY1() + fl.getHeight()) {
					fl.setColor(c);
				}
			}
		} else if (OpCode.equals("Ball")) {
			for (Ball b : m.getBall()) {
				if (b.getX1() - 12.5 <= xpos
						&& xpos <= (b.getX1() - 12.5) + b.getWidth()
						&& b.getY1() - 12.5 <= ypos
						&& ypos <= (b.getY1() - 12.5) + b.getHeight()) {
					b.setColor(c);
				}
			}
		}
	}

	public static void saveBoard() throws IOException {
		// System.out.println("Method has been called");
		JFileChooser anotherfc = new JFileChooser();

		File f = new File("TestInput");
		PrintWriter pw = new PrintWriter(f);
//		BufferedWriter bw = new BufferedWriter(fw);

		GizmoColours gc = new GizmoColours();

		// f = new File("anotherfc.getSelectedFile()");
		// fw = new FileWriter(f.getAbsoluteFile());
		// bw = new BufferedWriter(fw);

		System.out.println("Waiting...");
//			while (anotherfc.getSelectedFile() != null) {
			System.out.println("Absolute file: " + anotherfc.getSelectedFile());
			System.out.println("Absolute file: " + f.getAbsoluteFile());

			System.out.println("This is before the write statments.===========================");
			pw.print("test");
			pw.print("hey");
			pw.print("myFile");
			pw.print("BLAHAHAHAHAHA");
			pw.print("MUWHAHAH");
			pw.print("hmmAnotherStatemnt?");
			pw.print("theendof theeeee file statements");
			System.out.println("This is after the write statements.===========================");
		
		
		

		int retrival = anotherfc.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			System.out.println("MADE IT INTO THE IF STATEMENT.===========================");

			for (int i = 0; i < Model.gizmos.size(); i++) {
				Gizmo g = Model.gizmos.get(i);
				pw.print(g.getOpCode() + " ");
				pw.print(g.getID() + " ");
				pw.print((int) g.getX1() / 25 + " ");
				pw.print((int) g.getY1() / 25 + " ");
				if (g.getOpCode().equals("Absorber")) {
					pw.print((int) (g.getX1() + 500) / 25 + " ");
					pw.print((int) (g.getY1() + 50) / 25 + " ");
				}
//					if (g.getOpCode().equals("Ball")) {
//						for (Ball b : m.getBall()) {
//							if (b.getX1() == g.getX1()
//									&& b.getY1() == g.getY1()) {
//								pw.print(b.getXV() + " ");
//								pw.print(b.getYV() + " ");
//							}
//						}
//					}
				// pw.print(g.getX2() + " ");
				pw.print(gc.getStringFromColor(g.getColor()));

			}

//				for (int i = 0; i < m.getRotations().size(); i++) {
//					pw.print("Rotate ");
//					pw.print(m.getRotations().get(i).getID());
//				}

//				for (int i = 0; i < m.getConnections().size(); i++) {
//					pw.print("Connect ");
//					pw.print(m.getConnections().get(i).getID1());
//					pw.print(m.getConnections().get(i).getID2());
//				}

			BuildModeGUI.info_bar.setForeground(Color.BLUE);
			BuildModeGUI.info_bar.setText("GizmoBall board has been saved");

			pw.close();
			BuildModeGUI.window.repaint();
		}

	}

	public static void restartGame() {
		for (Gizmo g : m.gizmos) {
			if (g.getOpCode().equals("Ball")) {
				System.out.println("x = " + g.getDefaultX() + " and " + g.getX1());
				g.setX1(g.getDefaultX());
				g.setY1(g.getDefaultY());
			}
		}

		BuildModeGUI.window.repaint();
	}

	public void connectGizmos() {
		System.out.println("ID1 = " + connect_id_1);
		System.out.println("ID2 = " + connect_id_2);
		BuildModeGUI.info_bar.setForeground(Color.blue);
		BuildModeGUI.info_bar.setText("Connecting gizmos together");
		Connection c = new Connection(connect_id_1, connect_id_2);
		m.addConnection(c);
		connect_id_1 = "none";
		connect_id_2 = "none";
	}

	public void disconnectGizmos() {
		System.out.println("Disconnect ID1 = " + connect_id_1);
		System.out.println("Disconnect ID2 = " + connect_id_2);
		BuildModeGUI.info_bar.setForeground(Color.blue);
		BuildModeGUI.info_bar.setText("Disconnecting gizmos");
		for (int i = 0; i < m.getConnections().size(); i++) {
			if (m.getConnections().get(i).getID1().equals(connect_id_1)
					&& m.getConnections().get(i).getID2().equals(connect_id_2)) {
				m.getConnections().remove(i);
			}
		}
		connect_id_1 = "none";
		connect_id_2 = "none";
	}

	/*
	 * Is a bit temperamental, works sometimes, other times throws a null
	 * pointer exception, not sure why
	 */
	public static void ballProperties() {
		if (!m.getBall().isEmpty()) {
			for (Ball b : m.getBall()) {
				Vect v = new Vect(BallPropertiesWindow.f1slider.getValue(),
						BallPropertiesWindow.f2slider.getValue());
				b.setVelo(v);
			}
		}
	}

}
