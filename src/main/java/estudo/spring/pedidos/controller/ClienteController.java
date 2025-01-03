package estudo.spring.pedidos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<ClienteModel> list(){
        return this.clienteService.list();
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> Register(@RequestBody @Valid ClienteInputDTO dto){
        ClienteModel model = this.clienteService.register(dto.clienteDTOToModel());
        return ResponseEntity.ok(new ClienteDTO().clienteModelToDTO(model));
    }
}
