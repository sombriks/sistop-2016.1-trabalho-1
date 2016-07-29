package br.edu.ifce.sistop;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgenciaBancaria {
  
	private List<Caixa> caixas = new ArrayList<Caixa>();
	private List<Cliente> filaClientes = new ArrayList<>();

	public AgenciaBancaria(int numCaixas) {
    log.info("Abrindo a agência");
  	while (numCaixas-- > 0)
  		caixas.add(new Caixa());
  }

  public void recebeCliente(Cliente novoCliente) {
	  log.info("Novo cliente: "+novoCliente);
	  filaClientes.add(novoCliente);
	}

}
