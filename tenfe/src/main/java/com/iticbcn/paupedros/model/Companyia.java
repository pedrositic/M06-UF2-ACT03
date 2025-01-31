package com.iticbcn.paupedros.model;

import java.util.HashSet;
import java.util.Set;


public class Companyia {

  private Long id;

  private String nom;

  private Set<Tren> trens = new HashSet<>();

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

  public Set<Tren> getTrens() {
    return trens;
  }

  public void setTrens(Set<Tren> trens) {
    this.trens = trens;
  }
}