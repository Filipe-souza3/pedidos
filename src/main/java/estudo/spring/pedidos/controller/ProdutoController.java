package estudo.spring.pedidos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<ProdutoModel> list() {
        return this.produtoService.list();
    }

    @PostMapping()
    public ResponseEntity<ProdutoDTO> register(@RequestBody @Valid ProdutoInputDTO dto) {
        ProdutoModel model = this.produtoService.register(dto.produtoDTOToModel());
        return ResponseEntity.ok(new ProdutoDTO().produtoModeltoDto(model,model.getEstoque()));
    }
}
