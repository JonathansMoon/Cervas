package com.cervas.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class MessageElementTagProcessor extends AbstractAttributeTagProcessor{
	
	private static final String TAG_NAME = "message";
	private static final int PRECEDENCIA = 1000;

	public MessageElementTagProcessor(final String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, TAG_NAME, true, PRECEDENCIA, false);

	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		
		IModelFactory modelFactory	=	context.getModelFactory();
		IModel model	=	modelFactory.createModel();
		
		//Passo o elemento, depois o atributo e depois o valor do atributo
		model.add(modelFactory.createStandaloneElementTag("th:block", "th:include", "fragments/MensagemSucesso"));
		model.add(modelFactory.createStandaloneElementTag("th:block", "th:include", "fragments/MensagensErroValidacao"));
		
		structureHandler.replaceWith(model, true);
	}

}
