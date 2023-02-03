package com.tfa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfa.entite.Organisation;

public interface OrganisationRepository extends JpaRepository<Organisation, Integer> {
	Organisation findByCode(String code);
	List<Organisation> findByName(String name);
}
