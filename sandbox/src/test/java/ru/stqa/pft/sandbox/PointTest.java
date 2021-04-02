package ru.stqa.pft.sandbox;

import org.junit.Test;
import org.testng.Assert;

public class PointTest {

  @Test
  public void testDistance(){
    Point p1 = new Point(5, 6);
    Point p2 = new Point(15, 20);
    Assert.assertEquals(p1.distance(p2), 17.204650534085253);
  }
}
