package com.logicfishsoftware.commons.xtend.annotations;

import com.google.common.base.Objects;
import com.logicfishsoftware.commons.xtend.CommonsCollections;
import com.logicfishsoftware.commons.xtend.annotations.AnonymousInnerType;
import com.logicfishsoftware.commons.xtend.xannotation.Notes;
import java.util.Collections;
import java.util.List;
import org.eclipse.xtend.lib.macro.AbstractFieldProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;

@SuppressWarnings("all")
public class AnonymousInnerTypeProcessor extends AbstractFieldProcessor {
  public void doTransform(final MutableFieldDeclaration annotatedField, @Extension final TransformationContext context) {
    final Type annoType = context.findTypeGlobally(AnonymousInnerType.class);
    TypeDeclaration _head = null;
    Iterable<TypeDeclaration> _notesAsClasses = Notes.notesAsClasses(AnonymousInnerType.class, annotatedField, context);
    if (_notesAsClasses!=null) {
      _head=IterableExtensions.<TypeDeclaration>head(_notesAsClasses);
    }
    final Type cls = _head;
    final Iterable<TypeDeclaration> param = Notes.notesAsClasses(AnonymousInnerType.class, "parameters", annotatedField, context);
    Iterable<String> _elvis = null;
    Iterable<String> _notes = Notes.<String>notes(annoType, "ctorParam", annotatedField);
    if (_notes != null) {
      _elvis = _notes;
    } else {
      List<String> _emptyList = Collections.<String>emptyList();
      _elvis = ObjectExtensions.<Iterable<String>>operator_elvis(_notes, _emptyList);
    }
    final Iterable<String> ctorParam = _elvis;
    final String mixin = Notes.<String>note(annoType, "mixin", annotatedField);
    final CompilationStrategy _function = new CompilationStrategy() {
      public CharSequence compile(final CompilationContext it) {
        String _xblockexpression = null;
        {
          String _xifexpression = null;
          boolean _or = false;
          TypeReference _object = context.getObject();
          Type _type = _object.getType();
          boolean _equals = Objects.equal(_type, cls);
          if (_equals) {
            _or = true;
          } else {
            boolean _equals_1 = Objects.equal(cls, null);
            _or = (_equals || _equals_1);
          }
          if (_or) {
            TypeReference _type_1 = annotatedField.getType();
            Type _type_2 = _type_1.getType();
            String _qualifiedName = _type_2.getQualifiedName();
            _xifexpression = _qualifiedName;
          } else {
            String _qualifiedName_1 = cls.getQualifiedName();
            _xifexpression = _qualifiedName_1;
          }
          final String qName = _xifexpression;
          String _xifexpression_1 = null;
          boolean _or_1 = false;
          boolean _equals_2 = Objects.equal(param, null);
          if (_equals_2) {
            _or_1 = true;
          } else {
            boolean _isEmpty = IterableExtensions.isEmpty(param);
            _or_1 = (_equals_2 || _isEmpty);
          }
          if (_or_1) {
            _xifexpression_1 = "";
          } else {
            final Function1<TypeDeclaration,String> _function = new Function1<TypeDeclaration,String>() {
              public String apply(final TypeDeclaration it) {
                String _qualifiedName = it.getQualifiedName();
                return _qualifiedName;
              }
            };
            Iterable<String> _map = IterableExtensions.<TypeDeclaration, String>map(param, _function);
            String _cSVString = CommonsCollections.<String>toCSVString(((String[])Conversions.unwrapArray(_map, String.class)));
            String _plus = ("<" + _cSVString);
            String _plus_1 = (_plus + ">");
            _xifexpression_1 = _plus_1;
          }
          final String typeParam = _xifexpression_1;
          String _plus_2 = ("new " + qName);
          String _plus_3 = (_plus_2 + typeParam);
          String _plus_4 = (_plus_3 + "(");
          String _cSVString_1 = CommonsCollections.<String>toCSVString(((String[])Conversions.unwrapArray(ctorParam, String.class)));
          String _plus_5 = (_plus_4 + _cSVString_1);
          String _plus_6 = (_plus_5 + "){");
          String _plus_7 = (_plus_6 + mixin);
          String _plus_8 = (_plus_7 + "}");
          _xblockexpression = (_plus_8);
        }
        return _xblockexpression;
      }
    };
    annotatedField.setInitializer(_function);
  }
}
