package com.danielsv.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.danielsv.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {
	
	private static final long serialVersionUID = 1L;

	private Date dataVencimento;
	private Date dataPagemento;
	
	public PagamentoComBoleto() {
		
	}

	public PagamentoComBoleto(Integer ig, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(ig, estado, pedido);
		this.dataPagemento = dataPagamento;
		this.dataVencimento = dataVencimento;
		// TODO Auto-generated constructor stub
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagemento() {
		return dataPagemento;
	}

	public void setDataPagemento(Date dataPagemento) {
		this.dataPagemento = dataPagemento;
	}
	
	
}
