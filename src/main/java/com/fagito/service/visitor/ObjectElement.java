package com.fagito.service.visitor;

import org.springframework.stereotype.Component;

@Component
public interface ObjectElement {

	boolean accept(ValidationVisitor validationVisitor) throws Exception;
}
