package com.dws.web;

public class Cliente {
    //Change
    private long idCliente;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String passwd;
    private String direccion;

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public long getIdCliente() {
        return this.idCliente;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public String getDireccion() {
        return this.direccion;
    }

    @Override
    public String toString() {
        return "Datos del cliente :" + "\n" +
                "idCliente = " + this.idCliente + "\n" +
                "Nombre : " + this.nombre + "\n" +
                "Apellido : " + this.apellido + "\n" +
                "Email : " + this.email + "\n" +
                "Telefono : " + this.telefono + "\n" +
                "Password : " + this.passwd + "\n" +
                "Direccion : " + this.direccion + "\n";
    }
}
