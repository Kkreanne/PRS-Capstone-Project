package com.maxTrainBootcamp.prs.vendor;

import org.springframework.data.repository.CrudRepository;

public interface VendorRepository extends CrudRepository<Vendor, Integer> {
	Vendor findByCodeAndName(String code, String name);
}
