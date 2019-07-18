/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.Random;

/**
 *
 * @author josef
 */
public class Pendiente {

    private static Pendiente pendiente = null;
    private double m=0.0;
    private double b=0.0;
    private final Pendiente.Point initPoint;
    private final Pendiente.Point finishPoint;

    private Pendiente(Point initPoint, Point finishPoint) {
        this.initPoint = initPoint;
        this.finishPoint = finishPoint;
        calculate();
    }

    public static Pendiente getInstacePendiente(Point initPoint, Point finishPoint) {
        if (pendiente == null) {
            pendiente = new Pendiente(initPoint, finishPoint);
        }
        return pendiente;
    }

    private void calculate() {
        calculateM();
        calculateB();
    }

    private double calculateM() {
        this.m = differentY(initPoint, finishPoint) / differentX(initPoint, finishPoint);
        return this.m;
    }

    public double differentY() {
        return finishPoint.getY() - initPoint.getY();
    }

    public double differentX() {
        return finishPoint.getX() - initPoint.getX();
    }

    public static double differentY(Point initPoint, Point finishPoint) {
        return finishPoint.getY() - initPoint.getY();
    }

    public static double differentX(Point initPoint, Point finishPoint) {
        return finishPoint.getX() - initPoint.getX();
    }

    private double calculateB() {
        Point point = new Random().nextInt(2) == 0 ? initPoint : finishPoint;
        this.b = point.getY() - (this.m * point.getX());
        return b;
    }

    public double getM() {
        return this.m;
    }

    public double getB() {
        return this.b;
    }

    public String getEcuation() {
        
        if(this.b == 0){
            return String.format("y = %.2fx", this.m);
        }
        
        return String.format("y = %.2fx+ %.2f", this.m, this.b);
    }

    public static void clear(){
        pendiente = null;
    }
    
    @Override
    public String toString() {
        return "Pendiente{" + "m=" + m + ", b=" + b + '}';
    }

    public static class Point {

        private final double x;
        private final double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return this.x;
        }

        public double getY() {
            return this.y;
        }

        @Override
        public String toString() {
            return "Point{" + "x=" + x + ", y=" + y + '}';
        }

    }

}
