package com.exemplo.modulo_odm.repository;

import com.exemplo.modulo_odm.model.ProdutoRedis;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRedisRepository extends CrudRepository<ProdutoRedis, String> {
}