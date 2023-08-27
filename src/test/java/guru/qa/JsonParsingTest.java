package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import guru.qa.model.ProductModel;
import java.io.InputStream;


import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class JsonParsingTest {

    ClassLoader cl = JsonParsingTest.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Разбор json файла")
    void jsonTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("productJsonFile.json")) {
            ProductModel product = objectMapper.readValue(stream, ProductModel.class);

            assertThat(product.getName()).isEqualTo("Сок");
            assertThat(product.isAllowToSell()).isEqualTo(true);
            assertThat(product.getCostPrice()).isEqualTo("150");
            assertThat(product.getSubject().getNameSubject()).isEqualTo("Натуральный");
            assertThat(product.getSubject().getDescriptionSubject()).isEqualTo("Осветленный");

        }
    }
}
