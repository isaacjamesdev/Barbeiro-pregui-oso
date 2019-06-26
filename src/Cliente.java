public class Cliente extends Thread {
    
    private final int id;
    private static Estado estado;
    
    public Cliente(int id){
        this.id = id;
        estado = Estado.CHEGOU;
    }
    
    @Override
    public void run() {
        try {
            
            // Variavel que controla quando a Thread deve ser finalizada
            boolean ativo = true;
            
            // Loop que permanece em execucao enquanto a variavel ativo for verdadeira
            while(ativo) {
                
                switch(estado) {
                    case CHEGOU:
                        imprime();
                        // Verifica se as cadeiras estao cheias, se nao estiverem o cliente aguarda, caso contrario desiste
                        if(BarbeiroDorminhoco.aguardando < BarbeiroDorminhoco.quantidadeCadeiras) {
                            // Altera o estado para aguardando
                            estado = Estado.AGUARDANDO;
                        } else {
                            // Altera o estado para desistiu
                            estado = Estado.DESISTIU;
                        }
                        break;
                    
                    case AGUARDANDO:
                        // Executa o metodo que faz a impressao
                        imprime();
                        // Acrescenta o cliente ao contador de clientes aguardando
                        BarbeiroDorminhoco.aguardando++;
                        // Tenta fazer o acesso a exclusao mutua do barbeiro
                        Barbeiro.barbeiro.acquire();
                        // Ao ser liberado altera o estado para cortando
                        estado = Estado.CORTANDO;
                        break;
                    
                    case CORTANDO:
                        // Executa o metodo que faz a impressao
                        imprime();
                        // Subtrai o cliente do contador de clientes aguardando
                        BarbeiroDorminhoco.aguardando--;
                        // Libera o cliente´para o barbeiro fazer o corte
                        BarbeiroDorminhoco.proximoCliente.release();
                        // Tenta acessar o mutex que controla quando o barbeiro terminou o corte
                        Barbeiro.cortando.acquire();
                        // Ao finalizar altera o estado para terminou
                        estado = Estado.TERMINOU;
                        break;
                    
                    case TERMINOU:
                        // Executa o metodo que faz a impressao
                        imprime();
                        // Libera o semaforo do barbeiro para que faca o proximo corte
                        Barbeiro.barbeiro.release();
                        // Altera a variavel ativo para que a Thread seja finalizada
                        ativo = false;
                        break;
                        
                    case DESISTIU:
                        // Executa o metodo que faz a impressao
                        imprime();
                        // Altera a variavel ativo para que a Thread seja finalizada
                        ativo = false;
                        break;
                    
                }
                
            }
            
        } catch (InterruptedException ex) {}
    }
    
    // Método que faz a impressao
    private void imprime() {
        System.out.println("O cliente " + this.id + estado.getDescricao());
    }
    
}