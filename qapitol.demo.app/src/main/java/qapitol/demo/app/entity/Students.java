package qapitol.demo.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data 
@Entity
public class Students {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	public int id;
	public String name;
	public int age;
	public String email;
	

}
