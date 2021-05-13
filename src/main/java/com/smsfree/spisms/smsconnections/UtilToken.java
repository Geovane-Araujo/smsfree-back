package com.smsfree.spisms.smsconnections;

import com.pain_crud.PainCrud;
import com.smsfree.spisms.model.Users;
import com.sun.jersey.core.util.Base64;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

public class UtilToken {

    private String criterios;

    private int pagina;

    private String route;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public String getCriterios() {
        return criterios;
    }

    public void setCriterios(String criterios) {
        this.criterios = criterios;
    }

    public static Users decode(String token) throws SQLException {

        PainCrud pc = new PainCrud();
        SmsConnections connections = new SmsConnections();
        Connection con = null;
        con = connections.getNewConnections("atmusinf_sms");
        Users us = (Users) pc.getOne(Users.class,con,"select * from users where token = '"+ token +"'");
        con.close();
        return us;
    }

    public static String encode(Users us){
        Random rand = new Random();
        String s = Long.toHexString(rand.nextLong());
        s += String.valueOf(us.getId());

        String encodedString = java.util.Base64.getEncoder().encodeToString((s).getBytes());
        return encodedString;
    }
}
