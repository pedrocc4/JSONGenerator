package es.nfq.jsonconverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.*;

@Configuration
public class Proceso {
    @Scheduled(fixedDelay = 600000)
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
                    File file = new File("src/main/resources/consulta/division/codigo/codigoDivision" + (i + 1) + ".json");
                    FileWriter myWriter = new FileWriter(file);
                    String escritura = json[i].substring(json[i].indexOf("{"), json[i].length() - 2);
                    escritura += "}";
                    escritura = escritura.replace("%", "6");
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

}
