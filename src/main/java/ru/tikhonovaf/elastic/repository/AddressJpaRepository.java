package ru.tikhonovaf.elastic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tikhonovaf.elastic.entity.AddressEntity;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {}
