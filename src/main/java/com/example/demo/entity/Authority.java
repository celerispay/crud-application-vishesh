package com.example.demo.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.print.attribute.HashAttributeSet;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Authority {
	@Id
	private String id;

	@Size(min=2, max=45, message="Authority must contains character between 2 and 45 characters (Inclusive)")
	@NotBlank(message="Name cannot be blank")
	private String name;

	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	private List<User> users;

	@Override
	public int hashCode() {
		users.forEach(user -> user.setAuthorities(new ArrayList<>()));
		return Objects.hash(id, name, users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authority other = (Authority) obj;
		users.forEach(user -> user.setAuthorities(new ArrayList<>()));
		other.getUsers().forEach(user -> user.setAuthorities(new ArrayList<>()));
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(users, other.users);
	}

	@Override
	public String toString() {
		users.forEach(user -> user.setAuthorities(new ArrayList<>()));
		return "Authority [id=" + id + ", name=" + name + ", users=" + users + "]";
	}
}