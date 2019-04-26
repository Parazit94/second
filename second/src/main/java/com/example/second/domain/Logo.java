package com.example.second.domain;

import javax.persistence.*;

@Entity
public class Logo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "imgName", nullable = false)
    private String imgName;
    @Column(name = "path", nullable = false)
    private String path;


    public Logo() {
    }

    public Logo(String imgName, String path) {
        this.imgName = imgName;
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
