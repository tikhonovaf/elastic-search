package ru.tikhonovaf.elastic;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.TestPropertySource;
import ru.tikhonovaf.elastic.document.AddressDocument;
import ru.tikhonovaf.elastic.repository.AddressDocumentRepository;
import ru.tikhonovaf.elastic.service.AddressService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.elasticsearch.uris=http://localhost:9200"
})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressServiceIntegrationTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private ElasticsearchOperations operations;

    @Autowired
    private AddressDocumentRepository repository;

    @BeforeEach
    void cleanUp() {
        repository.deleteAll();
        operations.indexOps(AddressDocument.class).refresh();
    }

    @Test
    @Order(1)
    void testSaveAndSearch() {
        addressService.save(1L, "Москва Зеленоград корп 138");

        operations.indexOps(AddressDocument.class).refresh(); // обновление индекса

        List<AddressDocument> results = addressService.search("Зеленоград корп 138");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getFullAddress()).containsIgnoringCase("Зеленоград");
    }
}
