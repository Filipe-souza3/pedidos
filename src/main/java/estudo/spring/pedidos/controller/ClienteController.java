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

import estudo.spring.pedidos.dto.ClienteDTO;
import estudo.spring.pedidos.dto.input.ClienteInputDTO;
import estudo.spring.pedidos.modal.ClienteModel;
import estudo.spring.pedidos.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public Page<ClienteModel> list(
        @RequestParam(defaultValue = "0") Integer pagina,
        @RequestParam(defaultValue = "2") Integer qtdPagina,
        @RequestParam(required = false) Integer id,
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String endereco
        ){
        return this.clienteService.list(pagina, qtdPagina, id, nome, email, endereco);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> Register(@RequestBody @Valid ClienteInputDTO dto){
        ClienteModel model = this.clienteService.register(dto.clienteDTOToModel());
        return ResponseEntity.ok(new ClienteDTO().clienteModelToDTO(model));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@RequestBody @Valid ClienteInputDTO dto, @PathVariable Integer id){
        //fazer depois dto onde posso enviar somente um campo para atualizar, nao precisar enviar todos
    
        ClienteModel model = dto.clienteDTOToModel();
        model.setId(id);
        ClienteModel modelReturn = this.clienteService.update(model);
        return ResponseEntity.ok(new ClienteDTO().clienteModelToDTO(modelReturn));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){
        this.clienteService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
