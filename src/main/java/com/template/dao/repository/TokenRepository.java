package com.template.dao.repository;

import com.template.model.entity.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token,String> {
    Optional<Token> findByUserId(String id);
}
