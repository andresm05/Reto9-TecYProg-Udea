/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.co.udea.reto9;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author HOME
 */
public interface Varios {

    /*La interfaz contiene los métodos que se necesitan para el desarrollo del reto*/
    public static List<Double> elevarCuadrado(List<Double> listaIn) {

        return listaIn.stream().map(x -> x * x).collect(Collectors.toList());

    }

    public static List<String> cadenasMayorACinco(Set<String> conjunto) {

        return conjunto.stream().filter(c -> c.length() >= 5).collect(Collectors.toList());
    }

    public static String precioApertura(double precio) {
        if (precio < 1200) {
            return "MUY BAJO";
        }
        if (precio >= 1200 && precio < 2100) {
            return "BAJO";
        }
        if (precio >= 2100 && precio < 3100) {
            return "MEDIO";
        }
        if (precio >= 3100 && precio < 4600) {
            return "ALTO";
        }
        if (precio >= 4600) {
            return "MUY ALTO";
        }
        return "OUT OF RANGE";
    }

    public static double calcularPromedio(List<Double> elements) {
        double suma = 0;
        for (double elem : elements) {
            suma += elem;
        }
        return suma / elements.size();
    }

    public static double calcularDesvEstandar(List<Double> elements) {
        double promedio = calcularPromedio(elements);
        double sumatoria = 0;
        for (double elem : elements) {
            sumatoria += Math.pow(elem - promedio, 2);
        }

        return Math.sqrt(sumatoria / (elements.size() - 1));
    }

    public static int buscarPosicion(double valor, List<Double> elements) {
        int indx = -1;
        for (double elem : elements) {
            indx++;
            if (elem == valor) {
                return indx;
            }
        }
        return -1;
    }
}
