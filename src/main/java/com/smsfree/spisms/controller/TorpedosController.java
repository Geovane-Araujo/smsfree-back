package com.smsfree.spisms.controller;

import com.pain_crud.Alias;
import com.pain_crud.PainCrud;
import com.smsfree.spisms.model.Destinatarios;
import com.smsfree.spisms.model.Torpedos;
import com.smsfree.spisms.model.Users;
import com.smsfree.spisms.smsconnections.SmsConnections;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TorpedosController {

    SmsConnections connection = new SmsConnections();
    PainCrud pc = new PainCrud();



    public Torpedos save(Torpedos torpedos) throws SQLException, IllegalAccessException {

        Connection con = null;
        PreparedStatement stmt = null;
        int scalar = 0;

        con = connection.getNewConnections("sms_free");

        con.setAutoCommit(false);

        if(torpedos.isAdd()){
            scalar = pc.insertedOne(torpedos,Torpedos.class,con);
            for (Destinatarios dest: torpedos.getDestinatarios()) {
                dest.setIdmensagem(scalar);
                pc.insertedOne(dest,Destinatarios.class,con);
            }
        }
        else if(torpedos.isEdit()){
            pc.editingOne(torpedos,Torpedos.class,con,torpedos.getId());
            for (Destinatarios dest: torpedos.getDestinatarios()) {
                pc.editingOne(dest,Destinatarios.class,con,dest.getId());
            }
        }
        else if(torpedos.isDel()){
            pc.deleted(con, torpedos.getId(), Torpedos.class.getAnnotation(Alias.class).value());
        }
        con.commit();
        con.close();
        return torpedos;
    }
    public Object getById(int id) throws SQLException {

        Torpedos object = new Torpedos();
        Connection con = null;
        con = connection.getNewConnections("sms_free");

        String sql = "select * from "+Torpedos.class.getAnnotation(Alias.class).value()+" where id = " + id;

        object = (Torpedos) pc.getOne(Torpedos.class,con,sql);
        object.setDestinatarios((List<Destinatarios>) pc.getAll(Destinatarios.class,con,"select * from destinatarios where idmensagem = " + object.getId()));
        con.close();
        return object;
    }

    public Object checkShipping(Users us) throws SQLException {

        Connection con = null;
        con = connection.getNewConnections("sms_free");

        String sql = "select * from torpedos where idusuario = " + us.getId() + " and status = 0";

        List<Torpedos> torpedos = new ArrayList<>();
        Torpedos torp;
        for (Torpedos tp : (List<Torpedos>) pc.getAll(Torpedos.class,con,sql)) {
            torp = new Torpedos();
            torp.setDestinatarios((List<Destinatarios>) pc.getAll(Destinatarios.class,con,"select * from destinatarios where idmensagem = " + torp.getId()));
            torpedos.add(torp);
        }
        con.close();
        return torpedos;
    }

    public Object updateShipping(List<Torpedos> torpedos) throws SQLException, IllegalAccessException {

        Connection con = null;
        con = connection.getNewConnections("sms_free");


        for (Torpedos ta:torpedos) {
            ta.setStatus(1);
            pc.editingOne(ta,Torpedos.class,con,ta.getId());
        }

        con.close();
        return torpedos;
    }
}
