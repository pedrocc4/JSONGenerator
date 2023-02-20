package es.nfq.jsonconverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.*;
import java.util.Random;

@Configuration
public class Proceso {
    //    @Scheduled(fixedDelay = 600000)
    public void proceso() {
        String fileName = "archivos.json";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            String fileContent = sb.toString();
            String[] json = fileContent.split("},");
            for (int i = 0; i < json.length; i++) {
                try {
                    File file = new File("src/main/resources/consulta/division/pk/pkDivision" + (i + 1) + ".json");
                    FileWriter myWriter = new FileWriter(file);
                    String escritura = json[i].substring(json[i].indexOf("{"), json[i].length() - 2);
                    escritura += "}";
                    escritura = escritura.replace("%", "");
                    escritura = escritura.replace("]}", "");
                    myWriter.write(escritura);
                    myWriter.close();
                } catch (IOException e) {

                    System.err.println("Errrororororo: " + e.getLocalizedMessage());
                }
            }
        } catch (IOException e) {
            System.err.format("Error de E/S: %s%n", e);
        }
        System.out.println("fin");
    }

    @Scheduled(fixedDelay = 30000)

    public static void tipoGET() {
        String parteRuta = "https://mdmdev.prosegur.net/cmx/cs/gobdatd-GOBDAT_ORS/Tablon_analitica.json?action=query&filter=codigo=";
        String siguienteParteRuta = " and geografia=EMEA&outputView=TablonEBSView";
        long random1 = 22510986;
        long random2 = 22192236;
        String todos = "";
        StringBuilder all = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 200; i++) {
            long codigo = random.nextLong(random1 - random2) + random2;
            String escritura = parteRuta + codigo + siguienteParteRuta;
            all.append(parteRuta + codigo + siguienteParteRuta + "\n");
            try {
                File file = new File("src/main/resources/tablon/idServer/tablon" + (i + 1) + ".json");
                FileWriter myWriter = new FileWriter(file);
                myWriter.write(escritura);
                myWriter.close();
            } catch (IOException e) {
                System.err.println("Errrororororo: " + e.getLocalizedMessage());
            }
        }

        String parteRutaDos = "https://mdmdev.prosegur.net/cmx/cs/gobdatd-GOBDAT_ORS/Tablon_analitica.json?action=query&filter=empresa=EA9 " +
                "and cuenta=4320100 and delegacion=----- and division=---- and CentroDeCosto=----- and tercero=------------------ and " +
                "subtercero=----- and intercompany=";
        String siguienteParteRutaDos = " and proyecto=-- and geografia=EMEA&outputView=TablonEBSView";
        StringBuilder allDos = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader("ebs.txt"))) {
            String line;
            int total = 1;
            while ((line = br.readLine()) != null) {
                String codigo = line;
                String escritura = parteRutaDos + codigo + siguienteParteRutaDos;
                allDos.append(parteRuta + codigo + siguienteParteRuta + "\n");
                try {
                    File file = new File("src/main/resources/tablon/ebsServer/tablon" + total++ + ".json");
                    FileWriter myWriter = new FileWriter(file);
                    myWriter.write(escritura);
                    myWriter.close();
                } catch (IOException e) {
                    System.err.println("Errrororororo: " + e.getLocalizedMessage());
                }
            }
            System.out.println(all);
            System.out.println(allDos);
            try {
                File file1 = new File("src/main/resources/tablon/todos/idServer.json");
                FileWriter myWriter1 = new FileWriter(file1);
                myWriter1.write(all.toString());
            } catch (IOException e) {
                System.err.println("Errrororororo: " + e.getLocalizedMessage());
            }
            try {
                File file1 = new File("src/main/resources/tablon/todos/ebsServer.json");
                FileWriter myWriter1 = new FileWriter(file1);
                myWriter1.write(allDos.toString());
            } catch (IOException e) {
                System.err.println("Errrororororo: " + e.getLocalizedMessage());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        String[] separador = f..split("},");
//        File file = new File("src/java/main/resources/generated/primero." + ++total + ".json");
//        // Escritura
//        try {
//            FileWriter myWriter = new FileWriter(file);
//            myWriter.write(separador[]);
//            myWriter.close();
//        } catch (IOException e) {
//        }
//    }
        System.out.println("fin");
    }
}
