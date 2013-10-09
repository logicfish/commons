package com.logicfishsoftware.commons.xtend.xannotation;

import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation;
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotationElementValuePair;
import org.eclipse.xtext.xbase.interpreter.IEvaluationResult;
import org.eclipse.xtext.xbase.interpreter.impl.XbaseInterpreter;

import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class XAnnotationsInterpreter {
    @Inject
    XbaseInterpreter interpreter;
 
    public Object getXAnnotationValue(XAnnotation annotation, String simpleName) {
        IEvaluationResult result = null;
        if (simpleName.equals("value")) {
            result = interpreter.evaluate(annotation.getValue());
        }
 
        if (result != null) {
            for (XAnnotationElementValuePair pair : annotation
                    .getElementValuePairs()) {
                if (pair.getElement().getSimpleName().equals(simpleName)) {
                    result = interpreter.evaluate(pair.getValue());
                }
            }
        }
 
        return (result != null) ? result.getResult() : null;
    }
}
