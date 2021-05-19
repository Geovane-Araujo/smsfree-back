package com.smsfree.spisms.model;

import com.pain_crud.Alias;
import com.pain_crud.Id;
import com.pain_crud.Ignore;
import com.pain_crud.ListObjectLocal;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Alias(value = "torpedos")
public class Torpedos implements Serializable {

    @Ignore
    private static final long serialVersionUID = 1L;

    @Ignore
    private boolean add = true;
    @Ignore
    private boolean edit = false;
    @Ignore
    private boolean del = false;
    @Id
    private int id;

    private String mensagem;

    private int idusuario;
    @ListObjectLocal
    private List<Destinatarios> destinatarios;

    private int status = 0;

    @Ignore
    private String dataAgendamento;

    private Timestamp dataagendamento;

    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Timestamp getDataagendamento() {
        return dataagendamento;
    }

    public void setDataagendamento(Timestamp dataagendamento) {
        this.dataagendamento = dataagendamento;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Destinatarios> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<Destinatarios> destinatarios) {
        this.destinatarios = destinatarios;
    }

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

    public String getMensagem() {
        return mensagem;
    }


    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getIdusuario() {
        return idusuario;
    }


    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }


}
