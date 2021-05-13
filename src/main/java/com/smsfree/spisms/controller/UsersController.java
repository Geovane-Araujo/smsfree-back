package com.smsfree.spisms.controller;

import com.pain_crud.Alias;
import com.pain_crud.PainCrud;
import com.smsfree.spisms.model.Users;
import com.smsfree.spisms.smsconnections.SmsConnections;
import com.smsfree.spisms.smsconnections.UtilToken;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
public class UsersController {

    SmsConnections connection = new SmsConnections();
    PainCrud pc = new PainCrud();

    public Users save(Users users) throws SQLException, IllegalAccessException {

        Connection con = null;
        PreparedStatement stmt = null;
        int scalar = 0;

        con = connection.getNewConnections("atmusinf_sms");

        con.setAutoCommit(false);

        if(users.isAdd()){
            users.setToken(UtilToken.encode(users));
            scalar = pc.insertedOne(users,Users.class,con);
        }
        else if(users.isEdit()){
            pc.editingOne(users,Users.class,con,users.getId());
        }
        else if(users.isDel()){
            pc.deleted(con, users.getId(), Users.class.getAnnotation(Alias.class).value());
        }
        con.commit();
        con.close();
        return users;
    }
    public Object getById(int id) throws SQLException {

        Object object = new Object();
        Connection con = null;
        con = connection.getNewConnections("atmusinf_sms");

        String sql = "select * from "+Users.class.getAnnotation(Alias.class).value()+" where id = " + id;

        object =  pc.getOne(Users.class,con,sql);

        return object;
    }
    public Users authLogin(Users us) throws SQLException {

        Users object = new Users();
        Connection con = null;
        con = connection.getNewConnections("atmusinf_sms");

        String sql = "select * from "+Users.class.getAnnotation(Alias.class).value()+" where login = '" + us.getLogin() + "' and senha = '" + us.getSenha() + "'";

        object = (Users) pc.getOne(Users.class,con,sql);

        return object;
    }
}
