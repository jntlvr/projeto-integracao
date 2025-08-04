package com.exemplo.modulo_orm.repository;

import com.exemplo.modulo_orm.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}