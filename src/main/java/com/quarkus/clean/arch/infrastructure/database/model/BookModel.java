package com.quarkus.clean.arch.infrastructure.database.model;

import java.time.ZonedDateTime;

import javax.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BookModel")
public class BookModel extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private int pages;
    private boolean active;

    @Column(name = "CREATION_TIME")
    private ZonedDateTime creationTime;

    @Column(name = "UPDATE_TIME")
    private ZonedDateTime updateTime;
}