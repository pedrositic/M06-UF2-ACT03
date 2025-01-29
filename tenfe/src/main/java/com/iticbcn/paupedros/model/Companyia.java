package com.iticbcn.paupedros.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Companyia {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nom;

  @OneToMany(mappedBy = "companyia", cascade = CascadeType.ALL, orphanRemoval = true)
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

  public List<Tren> getTrens() {
    return trens;
  }

  public void setTrens(List<Tren> trens) {
    this.trens = trens;
  }
}