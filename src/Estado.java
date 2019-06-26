public enum Estado {
    
    DORMINDO(" estar dormindo"),
    CORTANDO(" estar cortando o cabelo"),
    DESISTIU(" desistiu porque a barbearia estar cheia"),
    CHEGOU(" chegou na barbearia"),
    TERMINOU(" terminou e estar indo embora"),
    AGUARDANDO(" aguardando sua vez");
    
    private String descricao;
 
    Estado(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
    
}
