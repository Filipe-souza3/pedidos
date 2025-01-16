package estudo.spring.pedidos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import estudo.spring.pedidos.repository.PedidoRepository;
import estudo.spring.pedidos.service.PedidoService;

@SpringBootTest
class PedidosApplicationTests {

	@Autowired
	PedidoService pedidoService;

	@Autowired
	PedidoRepository pedidoRepository;


	@Test
	void contextLoads() {
	}

	@Test
	void testPedidosService(){
		
	}

}
