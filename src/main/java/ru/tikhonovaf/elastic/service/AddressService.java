package ru.tikhonovaf.elastic.service;

import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;
import ru.tikhonovaf.elastic.document.AddressDocument;
import org.springframework.data.elasticsearch.client.elc.Queries;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import ru.tikhonovaf.elastic.repository.AddressDocumentRepository;

import java.util.List;

@Service
public class AddressService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final AddressDocumentRepository repository;


    public AddressService(ElasticsearchOperations elasticsearchOperations, AddressDocumentRepository repository) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.repository = repository;
    }

    public void save(Long id, String fullAddress) {
        AddressDocument doc = new AddressDocument();
        doc.setId(id);
        doc.setFullAddress(fullAddress);
        repository.save(doc);
    }

    public List<AddressDocument> search(String text) {
        Query query = Queries.queryStringQuery("fullAddress", "Зеленоград корп 138", 1.3f)._toQuery();

        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(query)
                .build();

        return elasticsearchOperations
                .search(nativeQuery, AddressDocument.class)
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }
}
