package com.telcel;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class CsvToGzipConverter {

    public static void main(String[] args) {
        String csvFilePath = "C:\\Users\\VING00G\\Documents\\bitacorasAenviar\\bitWS-20241124.csv"; // Reemplaza con la ruta de tu archivo CSV
        String gzipFilePath = "C:\\Users\\VING00G\\Documents\\bitacorasAenviar\\bitWS-20241124.csv.gz"; // Reemplaza con la ruta deseada para el archivo GZIP

        try (FileOutputStream fos = new FileOutputStream(gzipFilePath);
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fos);
             BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

            // Obtener el tamaño del archivo CSV (estimado)
            long fileSize = new File(csvFilePath).length();
            long bytesProcessed = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                byte[] data = line.getBytes();
                gzipOutputStream.write(data);
                gzipOutputStream.write('\n'); // Agrega un salto de línea al archivo GZIP

                bytesProcessed += data.length + 1; // Incluir el salto de línea

                // Calcular el porcentaje y actualizar la barra de progreso
                int percentCompleted = (int) ((bytesProcessed * 100) / fileSize);
                printProgressBar(percentCompleted);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void printProgressBar(int percentComplete) {
        // Ajusta la longitud de la barra según tus preferencias
        int barLength = 50;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < barLength; i++) {
            if (i < (percentComplete * barLength / 100)) {
                bar.append("=");
            } else if (i == (percentComplete * barLength / 100)) {
                bar.append(">");
            } else {
                bar.append(" ");
            }
        }
        bar.append("] ").append(percentComplete).append("%");
        System.out.print("\r" + bar);
        System.out.flush();
    }
}
