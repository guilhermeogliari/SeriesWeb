package com.seriesweb.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class User extends People{
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@OneToMany(mappedBy = "user")
	private Set<Iteration> iterations;

	public User() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Iteration> getIterations() {
		if(this.iterations == null) {
			this.iterations = new HashSet<Iteration>();
		}
		return (Set<Iteration>) this.iterations;	
	}

	public void setIterations(Set<Iteration> iterations) {
		this.iterations = iterations;
	}
	
	public void addAllIteration(Set<Iteration> newIteracao) {
		if (this.iterations == null) {
			this.iterations = new HashSet<Iteration>();
		}
		for (Iteration tmp : newIteracao)
			tmp.setUser(this);
	}
	
	public void removeAllIteration(Set<Iteration> newIteracao) {
		if(this.iterations == null) {
			return;
		}
		
		this.iterations.removeAll(newIteracao);	
	}
	
	public void addIteration(Iteration newIteracao) {
		if(this.iterations == null) {
			this.iterations = new HashSet<Iteration>();
		}
		
		if (this.iterations.add(newIteracao))
			newIteracao.basicSetUser(this);	
	}
	
	public void removeIteration(Iteration oldIteracao) {
		if(this.iterations == null)
			return;
		
		if (this.iterations.remove(oldIteracao))
			oldIteracao.unsetUser();
			
	}

}
