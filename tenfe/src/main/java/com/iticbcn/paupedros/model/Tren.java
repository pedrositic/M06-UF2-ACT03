package com.iticbcn.paupedros.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

  public List<Trajecte> getTrajectes() {
    return trajectes;
  }

  public void setTrajectes(List<Trajecte> trajectes) {
    this.trajectes = trajectes;
  }
}
