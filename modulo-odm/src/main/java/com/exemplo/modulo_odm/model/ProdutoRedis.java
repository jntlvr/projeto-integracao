package com.exemplo.modulo_odm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Produto")
public class ProdutoRedis implements Serializable {

    @Id
    private String id;
    private String nome;
    private double preco;

    // Construtor, Getters e Setters
    public ProdutoRedis() {}

    public ProdutoRedis(String id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}