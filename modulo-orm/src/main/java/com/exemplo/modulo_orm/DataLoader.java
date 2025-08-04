package com.exemplo.modulo_orm;

import com.exemplo.modulo_orm.model.Produto;
import com.exemplo.modulo_orm.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
public class DataLoader implements CommandLineRunner {

    private final ProdutoRepository repository;

    public DataLoader(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        Produto produto1 = new Produto();
        produto1.setNome("Notebook Dell");
        produto1.setPreco(5500.00);
        repository.save(produto1);

        System.out.println("-> Produto 'Notebook Dell' criado no banco de dados ORM (H2).");
    }
}