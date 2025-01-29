package com.iticbcn.paupedros.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Tren {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String model;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "companyia_id")
  private Companyia companyia;

  @OneToMany(mappedBy = "tren", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Trajecte> trajectes = new HashSet<>();

  // Getters and Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Companyia getCompanyia() {
    return companyia;
  }

  public void setCompanyia(Companyia companyia) {
    this.companyia = companyia;
  }

  public Set<Trajecte> getTrajectes() {
    return trajectes;
  }

  public void setTrajectes(Set<Trajecte> trajectes) {
    this.trajectes = trajectes;
  }
}
