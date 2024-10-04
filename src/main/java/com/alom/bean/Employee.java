package com.alom.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

	@Id
	@Column(name = "empid")
	private Integer id;
	
	@Column(name = "empname")
	private String name;
	
	@Column(name = "empsalary")
	private Double salary;
}
