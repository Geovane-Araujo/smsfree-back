package com.smsfree.spisms.model;

import com.pain_crud.Alias;
import com.pain_crud.Ignore;

import java.io.Serializable;
import java.sql.Timestamp;

@Alias(value = "destinatarios")
public class Destinatarios implements Serializable {

    @Ignore
    private static final long serialVersionUID = 1L;

    @Ignore
    private boolean add;
    @Ignore
    private boolean edit;
    @Ignore
    private boolean del;

    private int id;

    private int idmensagem;

    private String fone;

    private Timestamp dataAgendamento;

    private Timestamp dataEnvio;

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getIdmensagem() {
        return idmensagem;
    }


    public void setIdmensagem(int idmensagem) {
        this.idmensagem = idmensagem;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Timestamp getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Timestamp dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Timestamp getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Timestamp dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
