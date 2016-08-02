import br.edu.ifce.sistop.AgenciaBancaria;



/**
 * Entry point
 * 
 * @author sombriks
 *
 */
public class AppBootstrap {

  public static void main(String... args) {
    AgenciaBancaria ab = new AgenciaBancaria(5);

    int i = 10;
    while (i-- > 0)
      ab.recebeCliente(i, (long) (5000 + Math.random() * 10000));

    i = 10;
    while (i-- > 0)
      ab.atendeProximoCliente();

    ab.fechaAgencia();
    
  }
}