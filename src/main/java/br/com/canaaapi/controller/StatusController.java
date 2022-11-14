package br.com.canaaapi.controller;

import br.com.canaaapi.model.usuario.VOUsuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StatusController {
    @GetMapping(path = "/api/status")
    public String Status(){

        String ss = "seila ";
        return "online";
    }

}
