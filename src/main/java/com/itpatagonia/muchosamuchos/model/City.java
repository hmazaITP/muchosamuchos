/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itpatagonia.muchosamuchos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Hmaza
 */
@Entity
@Table(name = "city")
@Data
public class City implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private Integer cp;
    @JoinColumns({
        @JoinColumn(name = "stateid", referencedColumnName = "id")})
    @ManyToOne
    private State state;
    @JsonIgnore
    @OneToMany(mappedBy = "cityid")
    private List<Person> personList;


}
