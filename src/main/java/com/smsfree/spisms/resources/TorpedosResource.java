package com.smsfree.spisms.resources;

import com.smsfree.spisms.controller.TorpedosController;
import com.smsfree.spisms.model.Torpedos;
import com.smsfree.spisms.model.Users;
import com.smsfree.spisms.smsconnections.UtilToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

@RestController
@RequestMapping(value = "/sms/v1")
@CrossOrigin(origins ="*")
public class TorpedosResource {

    @Autowired
    TorpedosController torpedosController;

    @PostMapping("/torpedos")
    public ResponseEntity<?> save(@RequestHeader(value = "Authorization")String token, @RequestBody Torpedos torpedos)  {

        Hashtable retorno = new Hashtable();
        try {
            Users us = UtilToken.decode(token);
            if(us.getId() == 0){
                retorno.put("ret", "unsuccess");
                retorno.put("motivo", "Token Inválido");
            } else {
                torpedosController.save(torpedos);
                retorno.put("ret", "success");
                retorno.put("motivo", "OK");
                retorno.put("obj", torpedos);
            }
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

    @GetMapping("/torpedos/{id}")
    public ResponseEntity<?> get(@RequestHeader(value = "Authorization")String token, @PathVariable(value="id") int id) throws SQLException {

        Hashtable retorno = new Hashtable();
        try {
            Users us = UtilToken.decode(token);
            if(us.getId() == 0){
                retorno.put("ret", "unsuccess");
                retorno.put("motivo", "Token Inválido");
            } else {
                retorno.put("obj", torpedosController.getById(id));
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

    @GetMapping("/checkshipping")
    public ResponseEntity<?> checkshipping(@RequestHeader(value = "Authorization")String token)  {

        Hashtable retorno = new Hashtable();
        try {
            Users us = UtilToken.decode(token);
            if(us.getId() == 0){
                retorno.put("ret", "unsuccess");
                retorno.put("motivo", "Token Inválido");
            } else {
                retorno.put("ret", "success");
                retorno.put("motivo", "OK");
                retorno.put("obj", torpedosController.checkShipping(us));
            }
        }
        catch (SQLException e ) {
            retorno.put("ret", "unsuccess");
            retorno.put("motivo",e.getMessage());
        }

        return ResponseEntity.ok().body(retorno);
    }

    @PostMapping("/checkshipping")
    public ResponseEntity<?> checkshipping(@RequestBody List<Torpedos> torpedos)  {

        Hashtable retorno = new Hashtable();
        try {
            retorno.put("ret", "success");
            retorno.put("motivo", "OK");
            retorno.put("obj", torpedosController.updateShipping(torpedos));
        }
        catch (SQLException | IllegalAccessException e ) {
            retorno.put("ret", "unsuccess");
            retorno.put("motivo",e.getMessage());
        }

        return ResponseEntity.ok().body(retorno);
    }
}
