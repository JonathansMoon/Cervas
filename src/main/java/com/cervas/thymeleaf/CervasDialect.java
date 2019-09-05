package com.cervas.thymeleaf;



import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.cervas.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.cervas.thymeleaf.processor.MessageElementTagProcessor;
@Configuration
public class CervasDialect extends AbstractProcessorDialect{

	public CervasDialect() {
		super("Sistema Cervas", "cervas", StandardDialect.PROCESSOR_PRECEDENCE);
		
	}

	@Override
	public Set<IProcessor> getProcessors(final String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));	
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		return processadores;
	}
	

}
