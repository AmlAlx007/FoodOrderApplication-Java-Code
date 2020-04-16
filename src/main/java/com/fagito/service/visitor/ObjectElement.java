package com.fagito.service.visitor;

import org.springframework.stereotype.Component;

import com.fagito.dto.SignUpDTO;

@Component
public interface ObjectElement {

	boolean accept(ValidationVisitor validationVisitor) throws Exception;
}
