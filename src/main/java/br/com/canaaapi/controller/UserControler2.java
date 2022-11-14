package br.com.canaaapi.controller;

import br.com.canaaapi.estrutura.Resposta;
import br.com.canaaapi.estrutura.Retorno;
import br.com.canaaapi.model.usuario.VOUsuario;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserControler2 {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @GetMapping(path = "/listar/Todos")
    /*public ResponseEntity<List<User>> getAllUsers(){
        List<User> usuarioList = jdbcTemplate.query("select * from jr_usuarios ;", new BeanPropertyRowMapper<>(User.class));
        return new ResponseEntity<>(usuarioList, HttpStatus.OK);
    }*/

    public  ResponseEntity<VOUsuario> getAllUsers(){
        final Retorno<VOUsuario> retorno = new Retorno<>();
        List<VOUsuario> user = new ArrayList<>();

        user =   jdbcTemplate.query("select * from jr_usuarios ", new BeanPropertyRowMapper<>(VOUsuario.class));


     //   return new ResponseEntity<>(Resposta.get(true, retorno)).build();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
