package ru.stqa.pft.sandbox;

import org.junit.Test;
import org.testng.Assert;
import ru.Square;

public class SquareTest {

  @Test
  public void testArea() {
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 25.0);
  }

}
