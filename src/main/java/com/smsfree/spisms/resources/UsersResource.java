package com.smsfree.spisms.resources;

import com.smsfree.spisms.controller.UsersController;
import com.smsfree.spisms.model.Users;
import com.smsfree.spisms.smsconnections.UtilToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Hashtable;

@RestController
@RequestMapping(value = "/sms/v1")
@CrossOrigin(origins ="*")
public class UsersResource {

    @Autowired
    UsersController usersController;

    @PostMapping("/users")
    public ResponseEntity<?> save(@RequestBody Users users)  {

        Hashtable retorno = new Hashtable();
        try {
            usersController.save(users);
            retorno.put("ret", "success");
            retorno.put("motivo", "OK");
            retorno.put("obj", users);

        }
        catch (SQLException e ) {
            retorno.put("ret", "unsuccess");
            retorno.put("motivo",e.getMessage());
        } catch (IllegalAccessException ex) {
            retorno.put("ret", "unsuccess");
            retorno.put("motivo",ex.getMessage());
        }

        return ResponseEntity.ok().body(retorno);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users users)  {

        Hashtable retorno = new Hashtable();
        try {

            users = usersController.authLogin(users);
            if(users.getId() == 0){
                retorno.put("ret", "unsuccess");
                retorno.put("motivo", "Login ou senha inválidos !!!");
            }
            else{
                retorno.put("ret", "success");
                retorno.put("motivo", "OK");
                retorno.put("obj", users);
            }
        }
        catch (SQLException e ) {
            retorno.put("ret", "unsuccess");
            retorno.put("motivo",e.getMessage());
        }

        return ResponseEntity.ok().body(retorno);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> get(@RequestHeader(value = "Authorization")String token, @PathVariable(value="id") int id) throws SQLException {

        Hashtable retorno = new Hashtable();
        try {

            Users us =  UtilToken.decode(token);
            if(us.getId() == 0){
                retorno.put("ret", "unsuccess");
                retorno.put("motivo", "Token Inválido");
            }else{
                retorno.put("obj", usersController.getById(id));
                retorno.put("ret", "success");
                retorno.put("motivo", "OK");
            }
        }
        catch (SQLException e ) {
            retorno.put("ret", "unsuccess");
            retorno.put("motivo",e.getMessage());
        }

        return ResponseEntity.ok().body(retorno);
    }
}
