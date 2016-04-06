package br.com.eurekasoftwares.tablet.vo;

import java.util.Random;

import org.droidpersistence.annotation.Column;
import org.droidpersistence.annotation.PrimaryKey;
import org.droidpersistence.annotation.Table;

@Table(name="LOGIN")
public class LoginVO {
	
	@PrimaryKey
    @Column(name="ID")
    private long id = new Random().nextLong();
	@Column(name="USER")
    private String user = null;
	@Column(name="PASS")
    private String pass = null;
	
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
}
