package com.artificer.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificer.algafood.domain.exception.NegocioException;
import com.artificer.algafood.domain.exception.PedidoNaoEncontradoException;
import com.artificer.algafood.domain.model.Cidade;
import com.artificer.algafood.domain.model.FormaPagamento;
import com.artificer.algafood.domain.model.Pedido;
import com.artificer.algafood.domain.model.Produto;
import com.artificer.algafood.domain.model.Restaurante;
import com.artificer.algafood.domain.model.Usuario;
import com.artificer.algafood.domain.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CadastroProdutoService cadastroProduto;

	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;

	public Pedido buscar(String codigoPedido) {
		return pedidoRepository.findByCodigo(codigoPedido).orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
	}

	@Transactional
	public Pedido emitir(Pedido pedido) {
		validar(pedido);
		validarItens(pedido);
		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.calcularValorTotal();

		return pedidoRepository.save(pedido);
	}

	private void validarItens(Pedido pedido) {
		Cidade cidade = cadastroCidade.buscar(pedido.getEndereco().getCidade().getId());
		Restaurante restaurante = cadastroRestaurante.buscar(pedido.getRestaurante().getId());
		Usuario usuario = cadastroUsuario.buscar(pedido.getCliente().getId());
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscar(pedido.getFormaPagamento().getId());

		pedido.setRestaurante(restaurante);
		pedido.getEndereco().setCidade(cidade);
		pedido.setFormaPagamento(formaPagamento);
		pedido.setCliente(usuario);

		if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
			throw new NegocioException(String.format("O restaurante infomado não aceita %s como forma de pagamento!",
					formaPagamento.getDescricao()));
		}

	}

	private void validar(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			Produto produto = cadastroProduto.buscarOuFalhar(pedido.getRestaurante().getId(),
					item.getProduto().getId());
			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getPreco());
		});

	}

}
