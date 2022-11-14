package br.com.canaaapi.model;

import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name ="usuario")
public class VOUser {
    @Id
    public Integer codigo;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 10)
    private String login;
    @Column(nullable = false, length = 10)
    private  String senha;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
