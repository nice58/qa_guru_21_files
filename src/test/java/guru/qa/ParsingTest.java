package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ParsingTest {

    ClassLoader cl = ParsingTest.class.getClassLoader();

    @Test
    @DisplayName("Проверка и чтение pdf из zip-архива")
    void pdfTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("archive.zip")) {
            assert stream != null;
            try (ZipInputStream zis = new ZipInputStream(stream)) {

                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    final String name = entry.getName();
                    if (name.contains("pdfFile.pdf")) {
                        PDF pdf = new PDF(zis);
                        Assertions.assertTrue(pdf.text.contains("A Simple PDF File"));
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка и чтение xlsx из zip-архива")
    void xlsxfTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("archive.zip")) {
            assert stream != null;
            try (ZipInputStream zis = new ZipInputStream(stream)) {

                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    final String name = entry.getName();
                    if (name.contains("table.xlsx")) {
                        XLS xls = new XLS(zis);
                        Assertions.assertEquals("август", xls.excel.getSheetAt(0).
                                getRow(7)
                                .getCell(0)
                                .getStringCellValue());
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка и чтение csv из zip-архива")
    void csvTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("archive.zip")) {
            assert stream != null;
            try (ZipInputStream zis = new ZipInputStream(stream)) {

                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    final String name = entry.getName();
                    if (name.contains("csvFile.csv")) {
                        Reader reader = new InputStreamReader(zis);
                        CSVReader csvReader = new CSVReader(reader);
                        List<String[]> content = csvReader.readAll();

                        Assertions.assertEquals(2, content.size());

                        final String[] firstRow = content.get(0);
                        final String[] secondRow = content.get(1);

                        Assertions.assertArrayEquals(new String[] {"Lesson"," 10"}, firstRow);
                        Assertions.assertArrayEquals(new String[] {"Files", " tables"}, secondRow);
                    }
                }
            }
        }
    }
}



