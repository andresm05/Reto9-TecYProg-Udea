package com.co.udea.reto9;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Jaime Muñoz Juan Vasquez
 */
public class Reto9 {

    public static void main(String[] args) {

        /*Asignar las rutas absolutas de los archivos a tratar*/
        String rutaOrigen = "C:\\Users\\HOME\\Desktop\\ETH-USD.csv";
        String rutaDestino = "C:\\Users\\HOME\\Desktop\\prueba.txt";

        /*PRUEBAS AL CÓDIGO*/
        /*Manejo De Archivos*/
        Object info[] = tratamiento(rutaOrigen, rutaDestino);
        System.out.println("Promedio de los precios de cierre de ETH durante el año: " + info[0]);
        System.out.println("Desviación Est. de los precios de cierre de ETH durante el año: " + info[1]);
        System.out.println("Volumen transado más alto de ETH durante el año: " + info[2]
                + "\t" + "fecha: " + info[3]);
        System.out.println("Volumen transado más bajo de ETH durante el año: " + info[4]
                + "\t" + "fecha: " + info[5]);

        /*Programación Funcional*/
        List<Double> numeros = new ArrayList<>();
        numeros.add(81.1);
        numeros.add(32.5);
        numeros.add(9.0);
        numeros.add(25.0);
        List<Double> numerosElevados = Varios.raizCuadrada(numeros);
        System.out.println("Lista Original");
        numeros.stream().forEach(num -> System.out.println(num));
        System.out.println("Nueva lista con la raíz cuadrada de los números");
        numerosElevados.stream().forEach(num -> System.out.println(num));

        Set<String> conjunto = new HashSet<>();
        conjunto.add("random");
        conjunto.add("edificio");
        conjunto.add("sala");
        conjunto.add("mora");
        conjunto.add("vivir");
        System.out.println("Conjunto original");
        conjunto.stream().forEach(cad -> System.out.println(cad));
        List<String> conjuntosFilter = Varios.cadenasMayorACinco(conjunto);
        System.out.println("Nuevo conjunto con las cadenas que tienen 5 o más caracteres");
        conjuntosFilter.stream().forEach(cad -> System.out.println(cad));

    }

    public static Object[] tratamiento(String rutaOrigen, String rutaDestino) {
        /*El vector a continuación guardará la información importante*/
        Object[] info = new Object[6];
        double promedio = 0;
        double desvEstandar = 0;
        List<String> fechas = new ArrayList<>();
        List<Double> volumes = new ArrayList<>();
        List<Double> preciosCierre = new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        Path rutaO = Paths.get(rutaOrigen);
        Path rutaD = Paths.get(rutaDestino);
        List<String> lineasArchivo;
        try ( BufferedWriter writer = Files.newBufferedWriter(rutaD, charset)) {
            /*Leer las líneas del archivo origen*/
            lineasArchivo = Files.readAllLines(rutaO);
            /*Se Elimina la columna 0 que contiene el header*/
            lineasArchivo.remove(0);
            /*Crear un header para el archivo de destino*/
            writer.write("fecha" + "\t" + "Open" + "\n");
            for (String linea : lineasArchivo) {
                String data[] = linea.split(",");
                String textoEnviar = data[0] + "\t" + Varios.precioApertura(Double.parseDouble(data[1])) + "\n";
                /*Insertar la informnación solicitada en el archivo destino*/
                writer.write(textoEnviar);
                fechas.add(data[0]);
                preciosCierre.add(Double.valueOf(data[4]));
                volumes.add(Double.valueOf(data[6]));
            }
            promedio = Varios.calcularPromedio(preciosCierre);
            info[0] = promedio;
            desvEstandar = Varios.calcularDesvEstandar(preciosCierre);
            info[1] = desvEstandar;
            int posicionMax = Varios.buscarPosicion(Collections.max(volumes), volumes);
            int posicionMin = Varios.buscarPosicion(Collections.min(volumes), volumes);
            String fechaMax = fechas.get(posicionMax);
            info[2] = Collections.max(volumes);
            info[3] = fechaMax;
            String fechaMin = fechas.get(posicionMin);
            info[4] = Collections.min(volumes);
            info[5] = fechaMin;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return info;
    }

}
