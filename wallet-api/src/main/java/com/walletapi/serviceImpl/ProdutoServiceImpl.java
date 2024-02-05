package com.walletapi.serviceImpl;

import com.walletapi.model.Produto;
import com.walletapi.repository.ProdutoRepository;
import com.walletapi.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {

        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<Produto> listarProduto() {

        return produtoRepository.findAll();
    }

    @Override
    public Produto save(Produto produto) {

        return produtoRepository.save(produto);
    }

    @Override
    public Optional<Produto> litarProdutoPorCod(Integer id) {

        return Optional.empty();
    }

    @Override
    public List<Produto> listarProdutoPorNome(String nome) {

        return produtoRepository.listarProdutoPorNome(nome);
    }

    @Override
    public Optional<Produto> findById(Integer id) {

        return produtoRepository.findById(id);
    }

    @Override
    public Boolean existsById(Integer id) {

        return produtoRepository.existsById(id);
    }

    @Override
    public void delete(Integer id) {

        produtoRepository.deleteById(id);
    }
}
