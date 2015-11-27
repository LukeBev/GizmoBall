package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import controller.RunListener;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
/**
 * @author Maciej Zajac GizmoBall 27/02/2015
 */

public class Model extends Observable {
	
	public ArrayList<Circle> endPoints;
	public ArrayList<AbsorberGizmo> abs;
	public ArrayList<Ball> balls;
	private ArrayList<CircleGizmo> circles;
	public ArrayList<Flipper> flippers;
	private ArrayList<SquareGizmo> squares;
	public static ArrayList<TriangleGizmo> triangles;
	private ArrayList<Praser> parser;
	public boolean rotateTri = false;
	public ArrayList<String> idsS,idsT,idsC,idsLF,idsRF,idsA, idsB;
	private Walls gws;
	private Boolean assOn = true;
	public static ArrayList<Gizmo> gizmos = new ArrayList<Gizmo>();
	private static GizmoColours gc = new GizmoColours();
	
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	private ArrayList<Rotation> rotates = new ArrayList<Rotation>();
	
	// might need to delete this
	Color colour = null;

	public Model() {

		//Array to store ids for gizmos
		idsS = new ArrayList<String>();
		idsT = new ArrayList<String>();
		idsC = new ArrayList<String>();
		idsLF = new ArrayList<String>();
		idsRF = new ArrayList<String>();
		idsA = new ArrayList<String>();
		idsB = new ArrayList<String>();

		// Wall size 500 x 500 pixels
		gws = new Walls(0, 0, 500, 500);
		
		abs = new ArrayList<AbsorberGizmo>();
		balls = new ArrayList<Ball>();
		flippers = new ArrayList<Flipper>();
		circles = new ArrayList<CircleGizmo>();
		squares =  new ArrayList<SquareGizmo>();
		triangles = new ArrayList<TriangleGizmo>();
		// Parser
		parser = new ArrayList<Praser>();

	}

	public void flipUp(){
		for(Flipper f : flippers){
			f.direction = 1;
		}
	}


	public void flipDown(){
		for(Flipper f : flippers){
			f.direction = 2;
		}
	}

	
	public void moveFlipper()
	{
		for(Flipper f : flippers){
			f.rotateDirection();
			this.setChanged();
			this.notifyObservers();
		}
	}/*
	//degrees - flipper rotation in tuc
	public void degrees() throws InterruptedException{

		if(keyPressed){
			while(deg < 90){
				deg += 15;
				System.out.println("Rotating UP " + opcode);
			}
		}
		else if(!keyPressed){
			while(deg > 0){
				deg -= 15;
				System.out.println("Rotating Down " + opcode);
			}
		}
	}//end degrees
*/

	public void moveBall() {

		double moveTime = 0.05; // 0.05 = 20 times per second as per Gizmoball

		for(Ball b : balls){
			if (b != null && !b.getStopped()) {

				CollisionDetails cd = timeUntilCollision();
				double tuc = cd.getTuc();
				if (tuc > moveTime) {
					grav_time = moveTime;
					// No collision ...
					balls = movelBallForTime(balls, moveTime);

					//moveTime =0;
				} else {
					grav_time=tuc;
					
					balls = movelBallForTime(balls, tuc);
					
				b.setVelo(cd.getVelo());
					
				}

				
				this.setChanged();
				this.notifyObservers();
			}
		}

	}


	private ArrayList<Ball> movelBallForTime(ArrayList<Ball> ball, double time) {
		for(Ball b : ball){
			double newX = 0.0;
			double newY = 0.0;
			double xVel = b.getVelo().x();
			double yVel = b.getVelo().y();
			newX = b.getX1() + (xVel * time);
			newY = b.getY1() + (yVel * time);
			b.setX1(newX);
			b.setY1(newY);
			//xVel = friction(xVel);
			//yVel = friction(yVel) + gravity();
			System.out.println("yVel = " + yVel);
			b.setVelo(new Vect(xVel, yVel));
			System.out.println("x pos "+b.getX1());
		}
		return ball;
	}


	private CollisionDetails timeUntilCollision() {

		for(int i = 0; i < balls.size();){
			Circle ballCircle = balls.get(i).getCircle();
			Vect ballVelocity = balls.get(i).getVelo();
			Vect newVelo = new Vect(0, 0);
			double shortestTime = Double.MAX_VALUE;
			double time = 0.0;
			double absTime = 0.0;
			
			//line
			
			/*int x1,x2,y1,y2;
			x1 = 50;
			x2 = 250;
			y1 = 50;
			y2 = 250;*/
			ArrayList<LineSegment>lines = new ArrayList<LineSegment>();
			/*lines.add(new LineSegment(x1,y1,x2,y1));
			lines.add(new LineSegment(x1,y1,x1,y2));
			lines.add(new LineSegment(x2,y2,x1,y2));
			lines.add(new LineSegment(x2,y2,x2,y1));*/
			for (LineSegment line : lines) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelo = Geometry.reflectWall(line, balls.get(i).getVelo());
				}
			}
			//flippers
			//rf Math.toRadians(deg), (xpos1+30), (ypos1-5)
			for(Flipper f : flippers){
				ArrayList<LineSegment> fls = f.getLs();
				for(LineSegment lineSeg : fls){
					//rf Math.toRadians(deg), (xpos1+30), (ypos1-5)
					if(f.getOpCode().equals("RightFlipper")){
						time = Geometry.timeUntilRotatingWallCollision(lineSeg, new Vect((f.getX1()+30), (f.getY1()-5)), Math.toRadians(90), ballCircle, ballVelocity);
					}
					//lf Math.toRadians(-deg), (xpos1+16), (ypos1)
					if(f.getOpCode().equals("LeftFlipper")){
						time = Geometry.timeUntilRotatingWallCollision(lineSeg, new Vect((f.getX1()+16), (f.getY1())), Math.toRadians(-90), ballCircle, ballVelocity);
					}
				
					//time = Geometry.timeUntilWallCollision(lineSeg, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectWall(lineSeg, balls.get(i).getVelo());
					}					
				}
				ArrayList<Circle> css = f.getSCirc();
				for(Circle cs : css){
					time = Geometry.timeUntilCircleCollision(cs, ballCircle, ballVelocity);
					if(time < shortestTime){
						shortestTime = time;
						newVelo = Geometry.reflectCircle(cs.getCenter(),balls.get(i).getCircle().getCenter(), balls.get(i).getVelo());
					}
				}
			}
			
			
			
			//walls
			ArrayList<LineSegment> lss = gws.getLSegments();
			for (LineSegment line : lss) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelo = Geometry.reflectWall(line, balls.get(i).getVelo());
				}				
			}
			//squares
			for (SquareGizmo sq : squares) {
				ArrayList<LineSegment> ls = sq.getLSegments();
				for(LineSegment lineSeg : ls){
					time = Geometry.timeUntilWallCollision(lineSeg, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectWall(lineSeg, balls.get(i).getVelo());
					}
					if(time < 0.05){
						System.out.println("Collision detected");
						if(!sq.getColor().equals(sq.getDefaultColour())){
							sq.setColor(sq.getDefaultColour());
							for(Connection c : connections){
								if(sq.getID().equals(c.getID1())){
									for(Gizmo g: gizmos){
										if(g.getID().equals(c.getID2())){
											String connection_colour = gc.collideColour();
											g.setColor(gc.getColourFromString(connection_colour));
										}
									}
								}
							}
						}
						else{
							String new_colour = gc.collideColour();
							while(new_colour.equals(sq.getColor())){
								new_colour = gc.collideColour();
							}
							sq.setColour(gc.getColourFromString(new_colour));
							for(Connection c : connections){
								if(sq.getID().equals(c.getID1())){
									for(Gizmo g: gizmos){
										if(g.getID().equals(c.getID2())){
											String connection_colour = gc.collideColour();
											g.setColor(gc.getColourFromString(connection_colour));
										}
									}
								}
							}
						}					
					}
				}
			}
		
		//square corners
			for (SquareGizmo gizS : squares) {
				ArrayList<Circle> endPoint = gizS.getCircles();
				for(Circle eP : endPoint){
					time = Geometry.timeUntilCircleCollision(eP, ballCircle, ballVelocity);
					if(time < shortestTime){
						shortestTime = time;
						newVelo = Geometry.reflectCircle(eP.getCenter(),balls.get(i).getCircle().getCenter(), balls.get(i).getVelo());
					}
				}
			}

			//triangle
			for(TriangleGizmo tri : triangles){
				ArrayList<LineSegment> ls = tri.getLSegments();
				for(LineSegment lineSeg : ls){
					time = Geometry.timeUntilWallCollision(lineSeg, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectWall(lineSeg, balls.get(i).getVelo());
					}
					if(time < 0.05){
						if(!tri.getColor().equals(tri.getDefaultColour())){
							tri.setColor(tri.getDefaultColour());
							for(Connection c : connections){
								if(tri.getID().equals(c.getID1())){
									for(Gizmo g: gizmos){
										if(g.getID().equals(c.getID2())){
											String connection_colour = gc.collideColour();
											g.setColor(gc.getColourFromString(connection_colour));
										}
									}
								}
							}
						}
						else{
							String new_colour = gc.collideColour();
							while(new_colour.equals(tri.getColor())){
								new_colour = gc.collideColour();
							}
							tri.setColour(gc.getColourFromString(new_colour));
							for(Connection c : connections){
								if(tri.getID().equals(c.getID1())){
									for(Gizmo g: gizmos){
										if(g.getID().equals(c.getID2())){
											String connection_colour = gc.collideColour();
											g.setColor(gc.getColourFromString(connection_colour));
										}
									}
								}
							}
						}
					}
				}
			}
/*
			// Triangle end points
			for (GizTriangle tri : triangle) {
				Circle[] endPoint = tri.getTriangleEndPointsArr();
				for(Circle eP : endPoint){
					time = Geometry.timeUntilCircleCollision(eP, ballCircle, ballVelocity);
					if(time < shortestTime){
						shortestTime = time;
						newVelo = Geometry.reflectCircle(eP.getCenter(),balls.get(i).getCircle().getCenter(), balls.get(i).getVelo());
					}
				}
			}
*/
			// Circle 
			for(CircleGizmo cir: circles){
				Circle circles = cir.getCircle();
				time = Geometry.timeUntilCircleCollision(circles, ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelo = Geometry.reflectCircle(circles.getCenter(),balls.get(i).getCircle().getCenter(), balls.get(i).getVelo());
				}
				if(time < 0.05){
					System.out.println("Collision detected");
					if(!cir.getColor().equals(cir.getDefaultColour())){
						cir.setColor(cir.getDefaultColour());
						for(Connection c : connections){
							if(cir.getID().equals(c.getID1())){
								for(Gizmo g: gizmos){
									if(g.getID().equals(c.getID2())){
										String connection_colour = gc.collideColour();
										g.setColor(gc.getColourFromString(connection_colour));
									}
								}
							}
						}
					}
					else{
						String new_colour = gc.collideColour();
						while(new_colour.equals(cir.getColor())){
							new_colour = gc.collideColour();
						}
						cir.setColour(gc.getColourFromString(new_colour));
						for(Connection c : connections){
							if(cir.getID().equals(c.getID1())){
								for(Gizmo g: gizmos){
									if(g.getID().equals(c.getID2())){
										String connection_colour = gc.collideColour();
										g.setColor(gc.getColourFromString(connection_colour));
									}
								}
							}
						}
					}
				}
			}

			// Absorber 
			for(AbsorberGizmo gabs: abs){
				ArrayList<LineSegment> abls = gabs.getLs();
				for(LineSegment als : abls ){
					absTime = Geometry.timeUntilWallCollision(als, ballCircle, ballVelocity);
					if (absTime < shortestTime) {
						shortestTime = absTime;
						
						//newVelo = new Vect(0, -50*25);
						//RunListener.timer.stop();
						//newVelo = Geometry.reflectWall(als, balls.get(i).getVelo(), 1.0);
					}	
					if(absTime<0.05&& assOn){
						//balls.get(i).setVelo(new Vect(0, 0));
					//	RunListener.timer.stop();
						balls.get(i).setVelo(new Vect(0, balls.get(i).getVelo().y()));
						balls.get(i).setStopped(true);
					balls.get(i).setX1(490);
					//balls.get(i).setVelo(new Vect(0, -50*25));
						//25 is L
						newVelo = new Vect(0, -50*25);
						

					}
				}
			}			
			return new CollisionDetails(shortestTime, newVelo);
		}
		return null;
	}


	public void addGizmo(String opcode, String id, int arg1, int arg2,
			int arg3,int arg4, int arg5, String colour) {
		Color c = null;
		//System.out.println("This is happening");

		// gets the correct colour for the gizmo
		if (!colour.equals("no colour")) {
			GizmoColours gc = new GizmoColours();
			c = gc.getColourFromString(colour);
			//System.out.println(c);
		}

		if(opcode.equals("Square")&&id.startsWith("S")){
			if (colour.equals("no colour")){
				c = Color.RED;
			}
			System.out.println("The colour of this gizmo is: " + c);
			idsS.add(id);
			SquareGizmo sq = new SquareGizmo(opcode, id,arg1, arg2, arg3, c);
			sq.setID(id);
			sq.setOpCode(opcode);
			addSquare(sq);
			gizmos.add(sq);
			gizmos.get(gizmos.size()-1).setHeight(25);
			gizmos.get(gizmos.size()-1).setWidth(25);
			System.out.println("Gizmos Array Size: " + gizmos.size());
			System.out.println("Squares Array Size: " + gizmos.size());
			System.out.println("square added");
		}
		else if(opcode.equals("Triangle")&&id.startsWith("T")){
			if (colour.equals("no colour")){
				c = Color.MAGENTA;
			}
			TriangleGizmo tr = new TriangleGizmo(opcode, id, arg1,arg2,arg3, c);
			tr.setID(id);
			tr.setOpCode(opcode);
			//tr.rotate();
			addTriangle(tr);
			gizmos.add(tr);
			gizmos.get(gizmos.size()-1).setHeight(25);
			gizmos.get(gizmos.size()-1).setWidth(25);
			System.out.println("tri added");
		}
		else if(opcode.equals("Circle")&&id.startsWith("C")){
			if (colour.equals("no colour")){
				c = Color.YELLOW;
			}
			idsC.add(id);
			CircleGizmo cr = new CircleGizmo(opcode, id, arg1, arg2, c);
			cr.setID(id);
			cr.setOpCode(opcode);
			addCircle(cr);
			gizmos.add(cr);
			gizmos.get(gizmos.size()-1).setHeight(25);
			gizmos.get(gizmos.size()-1).setWidth(25);
			System.out.println("circle added");
		}
		else if(opcode.equals("LeftFlipper")&&id.startsWith("L")){
			if (colour.equals("no colour")){
				c = Color.ORANGE;
			}
			idsLF.add(id);
			Flipper fl = new Flipper(opcode, id, arg1, arg2, c);
			fl.setOpCode(opcode);
			fl.setID(id);
			addFlipper(fl);
			gizmos.add(fl);
			gizmos.get(gizmos.size()-1).setHeight(50);
			gizmos.get(gizmos.size()-1).setWidth(50);
			System.out.println("flipper added");
		}
		else if(opcode.equals("RightFlipper")&&id.startsWith("R")){
			if (colour.equals("no colour")){
				c = Color.RED;
			}
			idsLF.add(id);
			Flipper fl = new Flipper(opcode, id, arg1, arg2, c);
			fl.setOpCode(opcode);
			fl.setID(id);
			addFlipper(fl);
			gizmos.add(fl);
			gizmos.get(gizmos.size()-1).setHeight(50);
			gizmos.get(gizmos.size()-1).setWidth(50);
			System.out.println("Rflipper added");
		}
		else if(opcode.equals("Absorber")&&id.startsWith("A")){
			if (colour.equals("no colour")){
				c = Color.GREEN;
			}
			idsA.add(id);
			AbsorberGizmo abs = new AbsorberGizmo(opcode, id, arg1, arg2,arg3,arg4, c);
			abs.setID(id);
			abs.setOpCode(opcode);
			addAbs(abs);
			gizmos.add(abs);
			double width = 500;
			double height = 50;
			System.out.println("Width = " + width);
			gizmos.get(gizmos.size()-1).setHeight(height);
			gizmos.get(gizmos.size()-1).setWidth(width);
			System.out.println("Absorber added");
		}
		else if(opcode.equals("Connect")){			 
			System.out.println("connect done");
			//System.out.println(opcode);
			System.out.println(id);
			System.out.println(Praser.connect_id);
			Connection connect = new Connection(id, Praser.connect_id);
			connections.add(connect);
		}
		else if(opcode.equals("KeyConnect")){
			System.out.println("keyconnect done");
		}
		else if(opcode.equals("Rotate")){
			rotateTri = true;
			for( TriangleGizmo tri : this.triangles){
				if(tri.getID().equals(id)){
				// for(int i =0; i<4; i++)
					 tri.rotate();
				}
			}

			System.out.println("rotation  done");
		}
		else if(opcode.equals("Ball")){
			if (colour.equals("no colour")){
				c = Color.GREEN;
			}
			Ball bl = new Ball(opcode, id, arg1, arg2, 500.0, 25.0, c);
			bl.setOpCode(opcode);
			bl.setID(id);
			addBall(bl);
			gizmos.add(bl);
			gizmos.get(gizmos.size()-1).setHeight(25);
			gizmos.get(gizmos.size()-1).setWidth(25);
			System.out.println("ball added");
		}
	}

	public ArrayList<Flipper> getFlipper() {
		return flippers;
	}

	public void addFlipper(Flipper flip) {
		flippers.add(flip);
	}

	public ArrayList<CircleGizmo> getCircle() {
		return circles;
	}

	public void addCircle(CircleGizmo cir) {
		circles.add(cir);
	}

	public ArrayList<TriangleGizmo> getTriangle() {
		return triangles;
	}

	public void addTriangle(TriangleGizmo tr) {
		triangles.add(tr);
	}


	public ArrayList<Praser> getParser() { 
		return parser;
	}

	public void addParser(Praser ps) {
		parser.add(ps);
	}

	public ArrayList<SquareGizmo> getSquare() {
		return squares;
	}

	public void addSquare(SquareGizmo sq) {
		squares.add(sq);
	}

	public ArrayList<AbsorberGizmo> getAbs(){
		return abs;
	}

	public void addAbs(AbsorberGizmo absorber) {
		abs.add(absorber);
	}

	int gravY;
	public void setBallSpeed(int x, int y) {
		for(int i = 0; i < balls.size(); i++){
			balls.get(i).setVelo(new Vect((x), (y)));
		}
	}

	public ArrayList<Ball> getBall() {
		return balls;
	}

	public void addBall(Ball ball){
		balls.add(ball);
	}
	
	public void addConnection(Connection c){
		connections.add(c);
	}
	
	public ArrayList<Connection> getConnections(){
		return connections;
	}
	
	public void addRotation(Rotation r){
		rotates.add(r);
	}
	
	public ArrayList<Rotation> getRotations(){
		return rotates;
	}

	double Vnew, mu, mu2, deltat;
	public double friction(double Vold) {
		mu2= 0.025/20;
		mu = 0.025;
		deltat = grav_time;
		Vnew = Vold*(1-mu * deltat - mu2 * (Math.abs(Vold))*deltat);

		return Vnew;
	}
	double grav, grav_time;

	public double gravity(){
		grav = 25*20*grav_time;
		System.out.println("grav = " + grav + "  Time = " + grav_time);
		return grav;
	}
}
