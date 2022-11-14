package br.com.canaaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class userRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<String> getAllUserNames() {
        ResultSet rs =null;
        PreparedStatement stmt = null;

        final StringBuilder buf = new StringBuilder(500);

        List<String> UserNameList = new ArrayList<>();
        UserNameList.addAll(jdbcTemplate.queryForList("select name from jr_usuarios  ", String.class));

        return UserNameList;
    }
}
