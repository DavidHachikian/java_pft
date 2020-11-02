package ru.stqa.pft.sandbox;

import ru.Square;

public class MyFirstProgram {

  public static void main(String[] args) {

    Point p1 = new Point(5, 6);
    Point p2 = new Point(15, 20);
    System.out.println("Расстояние между двумя точками (" + p1.x + ";" + p1.y + ") и (" + p2.x + ";" + p2.y + ") = " + p1.distance(p2));



    /*hello("world");
    hello("user");
    hello("David");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь премоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

  }

  public static void hello (String somebody) {
    System.out.println("Hello, " + somebody + "!");

  }*/

  }
}