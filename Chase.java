import java.applet.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Chase extends Applet implements Runnable, MouseMotionListener
{
  private static final int NUMBER_OF_DOTS=1;
  private Thread workThread;
  private boolean running;

  private int currentMouseX=0;
  private int currentMouseY=0;
  
  private Vector dots;
  
//Where instance variables are declared:
          Dimension offDimension;
          Image offImage;
          Graphics offGraphics;

   public void init()
  {
    System.out.println("Inited");
    running = false;

    dots = new Vector();
    for(int i=0; i<NUMBER_OF_DOTS; i++) {
      dots.addElement(new Dot());
    }

    addMouseMotionListener(this);
  }
  
  public void start()
  {
    System.out.println("Started");
    if(!running) {
      running = true;
      workThread = new Thread(this);
      workThread.start();
    }
  }

  public void stop()
  {
    System.out.println("Stopped");
    running = false;
  }
  
  public void destroy()
  {
    System.out.println("Destroyed");
    
  }
  
  public void run()
  {
    Dot d;
    System.out.println("Entering thread");
    while(running) {
      
      try{
	Thread.sleep(1000);
      } catch (InterruptedException ie) {}
	System.out.println("Pip");
	repaint();
    }

    workThread = null;
    System.out.println("Leaving thread");
  }


  public void update(Graphics g) {
    //In the update() method, where d holds the size of the 
    //onscreen drawing area:
    Dimension dim = getSize();
    if ( (offGraphics == null)
	 || (dim.width != offDimension.width)
	 || (dim.height != offDimension.height) ) {
      offDimension = dim;
      offImage = createImage(dim.width, dim.height);
      offGraphics = offImage.getGraphics();
    } 

    offGraphics.setColor(getBackground());
    offGraphics.fillRect(0, 0, dim.width, dim.height);
    offGraphics.setColor(Color.black);
    // Draw each dot
    Dot d;
    for (Enumeration e = dots.elements() ; e.hasMoreElements() ;) {
      d = (Dot)e.nextElement();
      d.updateDot(currentMouseX, currentMouseY);
      offGraphics.drawLine(d.getX(), d.getY(), d.getX(), d.getY());
    }     
    
    g.drawImage(offImage, 0, 0, this);
  }

  public void mouseDragged(MouseEvent e)
  {
    // Potential bug. Mouseposition may be outside the canvas.
    System.out.println("Tút");
    currentMouseX=e.getX();
    currentMouseY=e.getY();
  }

  public void mouseMoved(MouseEvent e)
  {
    System.out.println("Tut");
    currentMouseX=e.getX();
    currentMouseY=e.getY();
  }

}

