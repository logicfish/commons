package com.logicfishsoftware.commons.xtend.xannotation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation;
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotationElementValuePair;
import org.eclipse.xtext.xbase.interpreter.IEvaluationResult;
import org.eclipse.xtext.xbase.interpreter.impl.XbaseInterpreter;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;

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

    public List<String> getXAnnotationValueNames(XAnnotation annotation) {
    	List<String> result = new ArrayList<String>(annotation.getElementValuePairs().size());
        for (XAnnotationElementValuePair pair : annotation
                .getElementValuePairs()) {
        	result.add(pair.getElement().getSimpleName());
        }
        return result;
    }

    public void getXAnnotationValues(XAnnotation annotation,Procedure2<String, Object> fnc) {
        for (XAnnotationElementValuePair pair : annotation
                .getElementValuePairs()) {
        	fnc.apply(pair.getElement().getSimpleName(),interpreter.evaluate(pair.getValue()));
        }
    }
}
