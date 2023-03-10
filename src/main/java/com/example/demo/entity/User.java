package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.example.demo.utility.Message;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class User {
	@Id
	private String id;

	@NotBlank(message=Message.VALIDATE_USERNAME_NOT_BLANK)
	@Length(min=2, max=45, message=Message.VALIDATE_USERNAME_LENGTH)
	@ApiModelProperty(value="The unique username of the User")
	private String username;

	@NotBlank(message=Message.VALIDATE_PASSWORD_NOT_BLANK)
	//@Length(min=5, max=25, message=Message.VALIDATE_PASSWORD_LENGTH)
	@ApiModelProperty(value = "The User's password")
	private String password;

	@NotBlank(message=Message.VALIDATE_EMAIL_NOT_BLANK)
	@Email(message = Message.VALIDATE_EMAIL)
	@ApiModelProperty(value = "The User's email")
	private String email;

	@ManyToMany(fetch = FetchType.LAZY)
	@ApiModelProperty(value = "The User's authorities")
	@JoinTable(
			  name = "user_authority", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private List<Authority> authorities;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Transaction> transactions;
}