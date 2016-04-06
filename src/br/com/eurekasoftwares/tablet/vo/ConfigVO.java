package br.com.eurekasoftwares.tablet.vo;

import org.droidpersistence.annotation.Column;
import org.droidpersistence.annotation.PrimaryKey;
import org.droidpersistence.annotation.Table;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author germano
 */
@Table(name="CONFIG")
public class ConfigVO {
    public ConfigVO(String url)
    {
        this.url = url;
    }
    public ConfigVO()
    {
    	super();
    }
    @PrimaryKey
    @Column(name="ID")
    private long id;
	@Column(name="URL")
    private String url;
	
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
