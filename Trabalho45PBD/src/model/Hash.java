package model;

import model.Usuario;

/**
 *
 * @author GUSTAVO JUNG
 */
public class Hash {
    
    private Usuario usuario;
    private Hash proximo;
    private int registroH;

    
    public Hash() {
    }

    public int getRegistroH() {
        return registroH;
    }

    public void setRegistroH(int registroH) {
        this.registroH = registroH;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Hash getProximo() {
        return proximo;
    }

    public void setProximo(Hash proximo) {
        this.proximo = proximo;
    }
    
    
}
