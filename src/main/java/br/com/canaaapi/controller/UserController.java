package br.com.canaaapi.controller;

import br.com.canaaapi.model.usuario.VOUsuario;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/userController")
public class UserController {
    @Autowired
    userRepository userRepository;
    @GetMapping
    public String check(){
        return "welcan to java ";
    }
    @GetMapping(path = "/name")
    public List<String> getAllUserNames(){
        return userRepository.getAllUserNames();
    }
 /*   @Autowired
    JdbcTemplate jdbcTemplate;
    @GetMapping(path = "/listar")
    public  ResponseEntity<VOUsuario> getAllUsers(){
        List<VOUsuario> user = new ArrayList<>();
        user =   jdbcTemplate.query("select * from jr_usuarios ", new BeanPropertyRowMapper<>(VOUsuario.class));

        return new ResponseEntity<>(null,HttpStatus.OK);
    }*/


}
