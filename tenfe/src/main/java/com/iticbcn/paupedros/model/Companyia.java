package com.iticbcn.paupedros.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

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

  public Set<Tren> getTrens() {
    return trens;
  }

  public void setTrens(Set<Tren> trens) {
    this.trens = trens;
  }
}