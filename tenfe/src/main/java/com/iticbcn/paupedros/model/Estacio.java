package com.iticbcn.paupedros.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Estacio {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nom;

  @ManyToMany(mappedBy = "estacions")
  private List<Trajecte> trajectes = new ArrayList<>();

  // Getters and Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public List<Trajecte> getTrajectes() {
    return trajectes;
  }

  public void setTrajectes(List<Trajecte> trajectes) {
    this.trajectes = trajectes;
  }
}