package br.com.canaaapi.model.usuario;

import br.com.canaaapi.estrutura.Filtro;
import br.com.canaaapi.estrutura.Retorno;
import br.com.canaaapi.model.VOUSUARIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Repository
@RequestMapping(value = "/api/usuarios")
public class Usuarios {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping(value = "/listar")
    public List<VOUSUARIO> listar(){
        List <VOUSUARIO> usuario = new ArrayList<>();
        usuario = jdbcTemplate.query("select * from jr_usuarios ",
           (rs,rowNum)-> new
                 VOUSUARIO(
                   rs.getInt("id"),
                   rs.getString("name"),
                   rs.getString("avatarUrl"),
                   rs.getString("company"),
                   rs.getString("role"),
                   rs.getString("status"),
                   rs.getInt("isVerified") ));

        return usuario;
    }

}
