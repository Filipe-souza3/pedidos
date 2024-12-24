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
            ProdutoDTO produtoDTO = new ProdutoDTO();

            if (pedidos.get(i).getPedidoId() == id) {
                PedidoDTO dto = listPedidos.get(listPedidos.size()-1);
                dto.addListProdutos(produtoDTO.produtoModeltoDto(pedidos.get(i).getProduto(), pedidos.get(i).getQuantidade()));
                listPedidos.set((listPedidos.size()-1), dto);

            } else {
                pedidoDTO.setPedidoId(pedidos.get(i).getPedidoId());
                pedidoDTO.setCliente(pedidos.get(i).getCliente());
                pedidoDTO.setDataPedido(pedidos.get(i).getDataPedido());

                pedidoDTO.addListProdutos(produtoDTO.produtoModeltoDto(pedidos.get(i).getProduto(), pedidos.get(i).getQuantidade()));
                listPedidos.add(pedidoDTO);

                pedidoDTO = new PedidoDTO();
            }
            id = pedidos.get(i).getPedidoId();

        }
       
        return listPedidos;
    }

}
