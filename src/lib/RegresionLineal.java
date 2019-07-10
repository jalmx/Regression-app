/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.ArrayList;

/**
 *
 * @author JOSEF Clase para calcular la ecuacion lineal Y sobre X
 * Unidad 7 de estadistica 2
 */
public class RegresionLineal {

    private double my;//y sobre x
    private double by;//y sobre x
    private double mx;//x sobre y
    private double bx;//x sobre y
    private final ArrayList<Double> tablaX, tablaY;
    private int nDatos;
    private double r;
    private double r2;
    private double t; //prueba t para el coeficiente de correlacion -> ocupar tabla t-student para interpretar resultado

    public RegresionLineal(ArrayList<Double> tablaX, ArrayList<Double> tablaY) {
        this.tablaX = tablaX;
        this.tablaY = tablaY;
        this.nDatos = this.tablaX.size();
    }

    public ArrayList<Double> getTablaX() {
        return tablaX;
    }

    public ArrayList<Double> getTablaY() {
        return tablaY;
    }

    public int getN_datos() {
        return nDatos;
    }

    public Double getSumatoriaX() {
        double sx = 0;
        for (int x = 0; x < getN_datos(); x++) {
            sx += this.tablaX.get(x);
        }
        return sx;
    }

    public Double getSumatoriaY() {
        double sy = 0;
        for (int y = 0; y < getN_datos(); y++) {
            sy += this.tablaY.get(y);
        }
        return sy;
    }

    public Double getSumatoriaXY() {
        double xy = 0;
        for (int u = 0; u < getN_datos(); u++) {
            xy += (this.tablaX.get(u) * this.tablaY.get(u));
        }
        return xy;
    }

    public Double getSumatoriaX2() {
        double sx = 0;
        for (int x = 0; x < getN_datos(); x++) {
            sx += Math.pow(this.tablaX.get(x), 2);
        }
        return sx;
    }

    public Double getSumatoriaY2() {
        double sy = 0;
        for (int y = 0; y < getN_datos(); y++) {
            sy += Math.pow(this.tablaY.get(y), 2);
        }
        return sy;
    }

    public String getYsobreX() {
        String p, bp;
        double m1 = getSumatoriaXY() - ((this.getSumatoriaX() * getSumatoriaY()) / getN_datos());
        double m2 = getSumatoriaX2() - (Math.pow(getSumatoriaX(), 2) / getN_datos());
        setMx(m1 / m2);
        double b = (getSumatoriaY() / getN_datos()) - (getMx() * (getSumatoriaX() / getN_datos()));
        setBx(b);

        p = String.format("%.2f", getMx());
        bp = String.format("%.2f", getBx());

        if (getBx() > 0) {
            bp = "+" + bp;
        }

        return p + "X" + bp;
    }

    public String getXsobreY() {
        String p, bp;
        double m1 = getSumatoriaXY() - ((this.getSumatoriaX() * getSumatoriaY()) / getN_datos());
        double m2 = getSumatoriaY2() - (Math.pow(getSumatoriaY(), 2) / getN_datos());
        setMy(m1 / m2);
        double b = (getSumatoriaX() / getN_datos()) - (getMy() * (getSumatoriaY() / getN_datos()));
        setBy(b);

        p = String.format("%.2f", getMy());
        bp = String.format("%.2f", getBy());

        if (getBy() > 0) {
            bp = "+" + bp;
        }

        return p + "Y" + bp;
    }

    public double getMy() {
        return my;
    }

    public void setMy(double my) {
        this.my = my;
    }

    public double getBy() {
        return by;
    }

    public void setBy(double by) {
        this.by = by;
    }

    public double getMx() {
        return mx;
    }

    public void setMx(double mx) {
        this.mx = mx;
    }

    public double getBx() {
        return bx;
    }

    public void setBx(double bx) {
        this.bx = bx;
    }

    public void setR() {
        boolean isNegative = false;
        double a = getSumatoriaXY() - (getSumatoriaX() * getSumatoriaY() / getN_datos());
        if (a < 0) {
            a = Math.abs(a);
            isNegative = true;
        }
        double b = (getSumatoriaX2() - (Math.pow(getSumatoriaX(), 2) / getN_datos())) * (getSumatoriaY2() - (Math.pow(getSumatoriaY(), 2) / getN_datos()));
        if (b < 0) {
            b = Math.abs(b);
            isNegative = true;
        }
        double r = a / Math.sqrt(b);
        
        if(isNegative){
            r*=(-1);
        }
        this.r = r;
        setR2();
    }

    public double getR() {
        return r;
    }

    private void setR2() {
        this.r2 = Math.pow(getR(), 2);
    }

    public double getR2() {
        return r2;
    }

    public double getT() {
        return t;
    }

    public void setT() {
        double t1 = getR()*Math.sqrt(getN_datos()-2);
        double t2 = Math.sqrt(1-getR2());
        this.t = t1/t2;
    }
    
    public void setT(double r, int n_datos) {
        this.nDatos = n_datos;
        double t1 = r*Math.sqrt(n_datos-2);
        double t2 = Math.sqrt(1-Math.pow(r, 2));
        this.t = t1/t2;
    }
    
    public int valorTablaT_Student(){
        return getN_datos()-2;
    }
    
}
