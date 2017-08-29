package com.hibernate.model.manytomany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MBOOK")
public class MBook {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TITLE")
	private String title;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "MAUTHOR_MBOOK", 
		joinColumns=@JoinColumn(name="MBOOK_ID"),
		inverseJoinColumns=@JoinColumn(name="MAUTHOR_ID"))
	private Set<MAuthor> authors = new HashSet<>();

	public MBook() {
	}

	public MBook(String name) {
		this.title = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<MAuthor> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<MAuthor> authors) {
		this.authors = authors;
	}

	public void addAuthor(MAuthor author) {
		this.authors.add(author);
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", authors=" + authors + "]";
	}

}
