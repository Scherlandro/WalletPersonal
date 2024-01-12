    package com.walletapi.controller;

    import com.walletapi.model.Produto;
    import com.walletapi.repository.ProdutoRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.Arrays;
    import java.util.List;


    @CrossOrigin(origins = "*")
    @RestController
    @RequestMapping("/api/produtos")
    public class ProdutoController {

        @Autowired
        private ProdutoRepository repository;

        // @PreAuthorize("hasRole('ADMIN')")
        @GetMapping(path = "/all")
        public ResponseEntity<List<Produto>> listarProdutos() {
            return ResponseEntity.ok(repository.findAll());
        }

        @GetMapping(path = "/{id_produto}")
        public ResponseEntity consultar(@PathVariable("id_produto") Integer id_produto) {
            return repository.findById(id_produto).map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());
        }

        @GetMapping(value = "/buscarPorNome")
        public ResponseEntity consultarPorNome(@RequestParam(name = "nome_produto") String nome_produto) {
            return Arrays.stream(repository.buscarPorNome(nome_produto).toArray()).findFirst().map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping(path = "/salvar")
        @ResponseStatus(HttpStatus.CREATED)
        public Produto salvar(@RequestBody Produto produto) {
            return repository.save(produto);
        }

        @PutMapping(path = "/editar/{id_produto}")
        public ResponseEntity<Produto> editar(@PathVariable("id_produto") Integer idprod,
                                              @RequestBody Produto produto) {
            if (!repository.existsById(idprod)) {
                return ResponseEntity.notFound().build();
            }
                produto.setId_produto(idprod);
                produto = repository.save(produto);
                return ResponseEntity.ok(produto);
        }

        @DeleteMapping(path = "/delete/{id_produto}")
        public void excluir(@PathVariable("id_produto") Integer id_produto) {
            repository.deleteById(id_produto);
        }
    }

