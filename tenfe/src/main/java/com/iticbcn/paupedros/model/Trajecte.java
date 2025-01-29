package com.iticbcn.paupedros.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trajecte {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String origen;
  private String desti;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tren_id")
  private Tren tren;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "trajecte_estacio", joinColumns = @JoinColumn(name = "trajecte_id"), inverseJoinColumns = @JoinColumn(name = "estacio_id"))
  private List<Estacio> estacions = new ArrayList<>();

  // Getters and Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOrigen() {
    return origen;
  }

  public void setOrigen(String origen) {
    this.origen = origen;
  }

  public String getDesti() {
    return desti;
  }

  public void setDesti(String desti) {
    this.desti = desti;
  }

  public Tren getTren() {
    return tren;
  }

  public void setTren(Tren tren) {
    this.tren = tren;
  }

  public List<Estacio> getEstacions() {
    return estacions;
  }

  public void setEstacions(List<Estacio> estacions) {
    this.estacions = estacions;
  }
}