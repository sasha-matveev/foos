package org.am.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IFoosCollection extends ReactiveMongoRepository<FooDocument, String> {
}
