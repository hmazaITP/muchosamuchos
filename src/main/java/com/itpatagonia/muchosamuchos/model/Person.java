/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itpatagonia.muchosamuchos.model;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hmaza
 */
@Entity
@Table(name = "person")
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surename;
    private String name;
    private LocalDate birth;
    private String dnu;
    private String address;
    @JoinColumn(name = "cityid", referencedColumnName = "id")
    @ManyToOne
    private City cityid;

    @ManyToMany
    @JoinTable(name = "courseperson",
            joinColumns = @JoinColumn(name = "personid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "courseid", referencedColumnName = "id"))
    private List<Course> courses;


}
