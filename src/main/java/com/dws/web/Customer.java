package com.dws.web;

public class Customer {
    private long idCliente;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String passwd;
    private String address;

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public long getIdCliente() {
        return this.idCliente;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public String getAddress() {
        return this.address;
    }

    @Override
    public String toString() {
        return "Datos del cliente :" + "\n" +
                "idCliente = " + this.idCliente + "\n" +
                "Nombre : " + this.name + "\n" +
                "Apellido : " + this.surname + "\n" +
                "Email : " + this.email + "\n" +
                "Telefono : " + this.phoneNumber + "\n" +
                "Password : " + this.passwd + "\n" +
                "Direccion : " + this.address + "\n";
    }
}
