/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testjava;

/**
 *
 * @author MOUMNI
 */
public class Client {
     private String nom;
     private String prenom;
     private int age;
     private String profession;
     private double salaire;

    @Override
    public String toString() {
        return "Client [nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", profession=" + profession
                + ", salaire=" + salaire + "]";
    }

    // public Client(String nom, String prenom, int age, String profession, double salaire) {
    //     this.nom = nom;
    //     this.prenom = prenom;
    //     this.age = age;
    //     this.profession = profession;
    //     this.salaire = salaire;
    // }

    public static Client create(String nom, String prenom, int age, String profession, double salaire) {
        Client c = new Client();
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setAge(age);
        c.setProfession(profession);
        c.setSalaire(salaire);
        return c;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }
    
}
