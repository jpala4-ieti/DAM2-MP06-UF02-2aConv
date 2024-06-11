package com.project.juny2024;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent, true));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainOutput() {
        Main.main(new String[]{});
        String expectedOutput = "Departaments:\r\n" + //
                "Departament{id=1, nom='Departament de Tecnologia', empleats=Anna Garcia, Pere Martinez}\r\n" + //
                "Departament{id=2, nom='Departament de Recursos Humans', empleats=Maria Lopez, Joan Sanchez}\r\n" + //
                "Empleats:\r\n" + //
                "Empleat{id=1, nom='Anna Garcia', departament=Departament de Tecnologia}\r\n" + //
                "Empleat{id=2, nom='Pere Martinez', departament=Departament de Tecnologia}\r\n" + //
                "Empleat{id=3, nom='Maria Lopez', departament=Departament de Recursos Humans}\r\n" + //
                "Empleat{id=4, nom='Joan Sanchez', departament=Departament de Recursos Humans}\r\n" + //
                "Projectes:\r\n" + //
                "Projecte{id=1, nom='Projecte A', dataInici='2024-01-01', dataFinal='2024-06-01', empleats=Anna Garcia, Maria Lopez}\r\n" + //
                "Projecte{id=2, nom='Projecte B', dataInici='2024-02-01', dataFinal='2024-07-01', empleats=Pere Martinez, Joan Sanchez}\r\n" + //
                "Projecte{id=3, nom='Projecte C', dataInici='2024-03-01', dataFinal='2024-08-01', empleats=Anna Garcia, Joan Sanchez}\r\n" + //
                "Projecte{id=4, nom='Projecte D', dataInici='2024-04-01', dataFinal='2024-09-01', empleats=Pere Martinez, Maria Lopez}";

        // Normalitzar els salts de linia entre el resultat esperat i el resultat obtingut
        String normalizedExpectedOutput = normalizeNewlines(expectedOutput);
        String normalizedActualOutput = normalizeNewlines(outContent.toString().trim());

        // Guardar la sortida esperada i la sortida real
        String baseDir = System.getProperty("user.dir");
        saveToFile(normalizedExpectedOutput, baseDir + File.separator + "data" + File.separator + "test-MainTest-SortidaEsperada.txt");
        saveToFile(normalizedActualOutput, baseDir + File.separator + "data" + File.separator + "test-MainTest-SortidaObtinguda.txt");

        // Comparar els resultats normalitzats
        assertEquals(normalizedExpectedOutput, normalizedActualOutput);
    }

    private String normalizeNewlines(String input) {
        return input.replace("\r\n", "\n").replace("\r", "\n");
    }

    private void saveToFile(String content, String filePath) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Crear directoris si no existeixen
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
                writer.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
