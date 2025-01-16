package estudo.spring.pedidos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonInclude;
import estudo.spring.pedidos.dto.ClienteDTO;
import estudo.spring.pedidos.dto.PedidoDTO;
import estudo.spring.pedidos.dto.PedidoDTOReturnRegister;
import estudo.spring.pedidos.dto.ProdutoDTO;
import estudo.spring.pedidos.dto.input.PedidoInputDTO;
import estudo.spring.pedidos.modal.PedidoModel;
import estudo.spring.pedidos.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<PedidoDTO> list() {
        List<PedidoModel> pedidos = this.pedidoService.list();
        List<PedidoDTO> listPedidos = new ArrayList<PedidoDTO>();

        PedidoDTO pedidoDTO = new PedidoDTO();
        Integer id = 0;

        for (int i = 0; i < (pedidos.size() - 1); i++) {
            PedidoModel p = pedidos.get(i);
            ProdutoDTO produtoDTO = new ProdutoDTO();

            if (p.getPedidoId() == id) {
                PedidoDTO dto = listPedidos.get(listPedidos.size() - 1);
                dto.addListProdutos(produtoDTO.produtoModeltoDto(p.getProduto(), p.getQuantidade()));
                listPedidos.set((listPedidos.size() - 1), dto);

            } else {
                pedidoDTO.setPedidoId(p.getPedidoId());
                pedidoDTO.setCliente(new ClienteDTO().clienteModelToDTO(p.getCliente()));
                pedidoDTO.setDataPedido(p.getDataPedido());

                pedidoDTO.addListProdutos(produtoDTO.produtoModeltoDto(p.getProduto(), p.getQuantidade()));
                listPedidos.add(pedidoDTO);

                pedidoDTO = new PedidoDTO();
            }
            id = p.getPedidoId();
        }
        return listPedidos;
    }

    @PostMapping
    public ResponseEntity<List<PedidoDTOReturnRegister>> register(@RequestBody List<PedidoInputDTO> dto) {
        List<PedidoModel> listModel = dto.stream().map(d -> d.pedidoDTOToModel()).collect(Collectors.toList());
        // PedidoDTO returnDTO = listModel.stream().map(e -> new
        // PedidoDTO(e.getPedidoId(), null, e.getCliente(), e.getDataPedido()));
        // PedidoDTO returnDTO = this.pedidoService.register(listModel).stream()
        // .map(e -> new PedidoDTO(e.getPedidoId(), null, new
        // ClienteDTO().clienteModelToDTO(e.getCliente()),
        // e.getDataPedido()))
        // .collect(Collectors
        // .toList());
        List<PedidoModel> reg = this.pedidoService.register(listModel);
        List<PedidoDTOReturnRegister> re2 = new ArrayList<PedidoDTOReturnRegister>();
        reg.forEach((e) -> {
            re2.add(new PedidoDTOReturnRegister(e.getId(), e.getPedidoId(), new ProdutoDTO().produtoModeltoDto(
                e.getProduto(), null), new ClienteDTO().clienteModelToDTOWithoutPhone(e.getCliente()),
                    e.getQuantidade(), e.getDataPedido()));
        });
        return ResponseEntity.ok(re2);
    }


    //fazer valid do pedido
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@RequestBody PedidoInputDTO dto, @PathVariable Integer id){

        Optional<PedidoModel> optional = this.pedidoService.findById(id);
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        PedidoModel model = dto.pedidoDTOToModel();
        model.setId(id);
        model.setPedidoId(optional.get().getPedidoId());
        PedidoModel modelReturn  = this.pedidoService.update(model);
        return ResponseEntity.ok(new PedidoDTO().pedidoModelToDTO(modelReturn));
    }

 

}
