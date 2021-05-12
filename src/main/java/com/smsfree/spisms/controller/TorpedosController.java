package com.smsfree.spisms.controller;

import com.pain_crud.Alias;
import com.pain_crud.PainCrud;
import com.smsfree.spisms.model.*;
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

    public Object checkShipping(Users us) throws SQLException, IllegalAccessException {

        Connection con = null;
        con = connection.getNewConnections("sms_free");

        String sql = "select * from torpedos where idusuario = " + us.getId() + " and status = 0";

        List<Mensagem> torpedos = new ArrayList<>();
        Mensagem sms;


        for (Torpedos tp : (List<Torpedos>) pc.getAll(Torpedos.class,con,sql)) {
            sms = new Mensagem();
            sms.setId(tp.getId());
            sms.setMensagem(tp.getMensagem());
            sms.setDataAgendamento(tp.getDataagendamento());
            sms.setDestinatarios((List<Destino>) pc.getAll(Destino.class,con,"select * from destinatarios where idmensagem = " + tp.getId()));
            torpedos.add(sms);
            tp.setStatus(1);
            pc.editingOne(tp,Torpedos.class,con,tp.getId());
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

    public Object getSms(int id) throws SQLException {

        Object object = new Object();
        Connection con = null;
        con = connection.getNewConnections("sms_free");

        String sql = "select id, mensagem,COALEsCE(CAST(dataagendamento as varchar),'') as DataAgendamento,COALEsCE(CAST(dataenvio as varchar),'')as DataEnvio ,(select COUNT(*) from destinatarios where idmensagem = torpedos.id) as quantidade\n" +
                "from torpedos where idusuario = " + id;

        object =  (List<Object>) pc.getAll(Object.class,con,sql);
        con.close();
        return object;
    }
}
