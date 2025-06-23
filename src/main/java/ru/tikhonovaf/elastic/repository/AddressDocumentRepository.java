package ru.tikhonovaf.elastic.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.tikhonovaf.elastic.document.AddressDocument;

public interface AddressDocumentRepository extends ElasticsearchRepository<AddressDocument, Long> {}
