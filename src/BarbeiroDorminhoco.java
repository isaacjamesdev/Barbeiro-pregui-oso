import java.util.concurrent.Semaphore;

public class BarbeiroDorminhoco {
    
    // Quantidade de cadeiras
    public static final int quantidadeCadeiras = 3;
    // Mutex para o proximo cliente que vai cortar o cabelo
    public static final Semaphore proximoCliente = new Semaphore(0);
    // Quantidade de clientes aguardando
    public static int aguardando = 0;
    

    public static void main(String[] args) {
        
        // Instanciacao do barbeiro
        Barbeiro barbeiro = new Barbeiro(1);
        // Inicializacao da Thread do barbeiro
        barbeiro.start();
        
        // Contador para o id de cliente
        int i = 0;
        
        // Loop para criacao dos clientes
        while(true) {
            
            // Executa o metodo que da uma pausa entre a criacao de clientes
            pausa();
            
            // Adiciona valor ao contador de id de cliente
            i++;
            
            // Instanciacao do cliente
            Cliente cliente = new Cliente(i);
            // Inicializacao da Thread do cliente
            cliente.start();
            
        }
        
    }
    
    // Definicao do tempo de pausa entre a criacao de clientes
    private static void pausa() {
        try {
            Thread.sleep((long) Math.round(Math.random() * 10000));
        } catch (InterruptedException e) {}
    }
    
}
