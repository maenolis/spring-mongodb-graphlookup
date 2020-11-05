package com.github.kmandalas.mongodb.repository;

import com.github.kmandalas.mongodb.document.Node;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NodeRepository extends MongoRepository<Node, Object>, NodeGraphLookupRepository {

  List<Node> findDistinctByChangesetId(int changesetId);

  List<Node> deleteByChangesetId(int changesetId);

}