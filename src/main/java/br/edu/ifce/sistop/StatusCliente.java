package br.edu.ifce.sistop;

enum StatusCliente {
  NaFila("F"), EmAtendimento("E"), Atendido("A");
  
  private String stat;

  private StatusCliente(String stat) {
    this.stat = stat;
  }

  @Override
  public String toString() {
    return stat;
  }
}