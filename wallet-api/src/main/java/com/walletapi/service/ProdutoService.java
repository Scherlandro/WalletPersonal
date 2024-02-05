package com.walletapi.service;

import com.walletapi.model.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    List<Produto> listarProduto();
    Produto save(Produto produto);
    Optional<Produto> litarProdutoPorCod(Integer id);
    List<Produto> listarProdutoPorNome(String nome);
    Optional<Produto> findById(Integer id);
    Boolean existsById(Integer id);
    void delete(Integer id);
}
