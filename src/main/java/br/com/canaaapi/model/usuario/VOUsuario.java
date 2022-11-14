package br.com.canaaapi.model.usuario;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name ="VOUsuario")
public class VOUsuario {
    @Id
    private int id;
    private String avatarUrl;
    private String company;

    private String name;
    private String role;
    private String status;

    private int isVerified;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    @Override
    public String toString() {
        return "VOUsuario{" +
                "id=" + id +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", isVerified=" + isVerified +
                '}';
    }
}
