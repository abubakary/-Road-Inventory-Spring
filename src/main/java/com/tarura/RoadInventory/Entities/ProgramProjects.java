package com.tarura.RoadInventory.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trr_program_projects")
public class ProgramProjects implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid", unique = true, nullable = false)
    private int orderId;

    @Column(name="pname", nullable = true, unique = true)
    private String pName;

    @Column(name="fagency", nullable = true)
    private String fAgency;

    @Column(name="freference", nullable = true)
    private String fReference;

    @Column(name="pfullname", nullable = true)
    private String pFullName;

}
