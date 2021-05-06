package com.smsfree.spisms.model;

import com.pain_crud.Alias;
import com.pain_crud.Ignore;
import com.pain_crud.ListObjectLocal;

import java.io.Serializable;
import java.util.List;

@Alias(value = "torpedos")
public class Torpedos implements Serializable {

    @Ignore
    private static final long serialVersionUID = 1L;

    @Ignore
    private boolean add;
    @Ignore
    private boolean edit;
    @Ignore
    private boolean del;

    private int id;

    private String mensagem;

    private int idusuario;
    @ListObjectLocal
    private List<Destinatarios> destinatarios;

    private int status = 0;

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
