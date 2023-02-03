package com.tfa.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganisationDto {

	private Integer id;
	private String name;
	private String code;
	private String description;
	private LocalDateTime dateCreation;
}
