package estudo.spring.pedidos.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import estudo.spring.pedidos.dto.ProdutoDTO;
import estudo.spring.pedidos.dto.input.ProdutoInputDTO;
import estudo.spring.pedidos.modal.ProdutoModel;
import estudo.spring.pedidos.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoModel>> list(
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "2") Integer qtdPagina,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) Double preco,
            @RequestParam(required = false) Integer estoque) {
        return ResponseEntity.ok(this.produtoService.list(pagina, qtdPagina, id, nome, descricao, preco, estoque));
    }

    @PostMapping()
    public ResponseEntity<ProdutoDTO> register(@RequestBody @Valid ProdutoInputDTO dto) {
        ProdutoModel model = this.produtoService.register(dto.produtoDTOToModel());
        return ResponseEntity.ok(new ProdutoDTO().produtoModeltoDto(model, model.getEstoque()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@RequestBody @Valid ProdutoInputDTO dto, @PathVariable Integer id) {
        // fazer depois dto onde posso enviar somente um campo para atualizar, nao
        // precisar enviar todos
        ProdutoModel model = dto.produtoDTOToModel();
        model.setId(id);
        ProdutoModel modelReturn = this.produtoService.update(model);
        return ResponseEntity.ok(new ProdutoDTO().produtoModeltoDto(modelReturn, null));
    }

    /**
     * 
     * para fazer
     * o deletar, update e inserir fazer update no estoque produtos
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        // if(!(this.produtoService.findById(id)).isPresent()){
        // return ResponseEntity.notFound().build();
        // }
        this.produtoService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
