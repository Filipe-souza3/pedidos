package estudo.spring.pedidos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudo.spring.pedidos.dto.PedidoDTO;
import estudo.spring.pedidos.dto.ProdutoDTO;
import estudo.spring.pedidos.modal.PedidoModel;
import estudo.spring.pedidos.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoDTO> list() {
        List<PedidoModel> pedidos = this.pedidoService.list();
        List<PedidoDTO> listPedidos = new ArrayList<PedidoDTO>();

        PedidoDTO pedidoDTO = new PedidoDTO();
        Integer id = 0;

        for (int i = 0; i < (pedidos.size() - 1); i++) {
            PedidoModel p = pedidos.get(i);
            ProdutoDTO produtoDTO = new ProdutoDTO();

            if (p.getPedidoId() == id) {
                PedidoDTO dto = listPedidos.get(listPedidos.size()-1);
                dto.addListProdutos(produtoDTO.produtoModeltoDto(p.getProduto(), p.getQuantidade()));
                listPedidos.set((listPedidos.size()-1), dto);

            } else {
                pedidoDTO.setPedidoId(p.getPedidoId());
                pedidoDTO.setCliente(p.getCliente());
                pedidoDTO.setDataPedido(p.getDataPedido());

                pedidoDTO.addListProdutos(produtoDTO.produtoModeltoDto(p.getProduto(), p.getQuantidade()));
                listPedidos.add(pedidoDTO);

                pedidoDTO = new PedidoDTO();
            }
            id = p.getPedidoId();
        }
        return listPedidos;
    }

}
