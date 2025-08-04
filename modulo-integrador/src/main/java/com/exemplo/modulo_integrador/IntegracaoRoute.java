package com.exemplo.modulo_integrador;

import com.exemplo.modulo_orm.model.Produto;
import com.exemplo.modulo_orm.repository.ProdutoRepository;
import com.exemplo.modulo_odm.model.ProdutoRedis;
import com.exemplo.modulo_odm.repository.ProdutoRedisRepository;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class IntegracaoRoute extends RouteBuilder {

    private final ProdutoRepository ormRepository;
    private final ProdutoRedisRepository odmRepository;

    public IntegracaoRoute(ProdutoRepository ormRepository, ProdutoRedisRepository odmRepository) {
        this.ormRepository = ormRepository;
        this.odmRepository = odmRepository;
    }

    @Override
    public void configure() throws Exception {
        // Rota de exemplo: a cada 5 segundos, busca produtos no ORM e salva no ODM
        from("timer:myTimer?period=5000")
            .log("Verificando produtos para sincronização...")
            .process(exchange -> {
                // Simula a busca no ORM
                Produto produtoORM = ormRepository.findById(1L).orElse(null);
                if (produtoORM != null) {
                    // Mapeia para o modelo canônico de dados (ProdutoRedis)
                    ProdutoRedis produtoODM = new ProdutoRedis(
                        String.valueOf(produtoORM.getId()),
                        produtoORM.getNome(),
                        produtoORM.getPreco()
                    );
                    exchange.getIn().setBody(produtoODM);
                } else {
                    exchange.getIn().setBody(null);
                }
            })
            .filter(body().isNotNull())
            .log("Produto encontrado: ${body.nome}")
            .to("direct:syncToRedis");

        // Rota para sincronizar com o Redis (Canal)
        from("direct:syncToRedis")
            .process(exchange -> {
                ProdutoRedis produto = exchange.getIn().getBody(ProdutoRedis.class);
                odmRepository.save(produto);
                log.info("Produto '${body.nome}' sincronizado com sucesso no Redis.");
            });
    }
}