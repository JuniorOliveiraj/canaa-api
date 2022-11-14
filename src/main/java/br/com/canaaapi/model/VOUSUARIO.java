package br.com.canaaapi.model;

public class VOUSUARIO {
    private int id;
    private String avatarUrl;
    private String company;

    private String name;
    private String role;
    private String status;

    private int isVerified;

    public VOUSUARIO(int id, String avatarUrl, String company, String name, String role, String status, int isVerified) {
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.company = company;
        this.name = name;
        this.role = role;
        this.status = status;
        this.isVerified = isVerified;
    }

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
        return "VOUSUARIO{" +
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
