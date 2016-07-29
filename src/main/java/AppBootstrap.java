import br.edu.ifce.sistop.AgenciaBancaria;
import br.edu.ifce.sistop.Cliente;

public class AppBootstrap {

  public static void main(String... args) {
    // System.out.println("Hello");
    AgenciaBancaria ab = new AgenciaBancaria(5);

    int i = 100;
    while (i-- > 0) {
      Cliente c = new Cliente(i,(int) (Math.random()*30000));
      ab.recebeCliente(c);
    }

  }
}