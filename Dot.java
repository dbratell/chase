public class Dot
{
  private static final int MAXDX=5;
  private static final int MAXDY=5;
  private double x;
  private double y;
  private double dx;
  private double dy;

  public Dot()
  {
    x=0.0;
    y=0.0;
    dx=0.0;
    dy=0.0;
  }

  public int getX() { return (int)(x+0.5); }
  public int getY() { return (int)(y+0.5); }

  /**
   * TODO: Randomize somewhat.
   */
  public void updateDot(int targetX, int targetY) {
    System.out.print("Was: "+x+","+y);
    dx += (targetX-x)*0.1;
    dy += (targetY-y)*0.1;
    x += dx;
    y += dy;
    System.out.print("  is: "+x+","+y);
  }
}
