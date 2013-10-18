package com.logicfishsoftware.commons.xtend.annotations;

import com.google.common.base.Objects;
import com.logicfishsoftware.commons.xtend.CommonsCollections;
import com.logicfishsoftware.commons.xtend.annotations.AnonymousInnerType;
import com.logicfishsoftware.commons.xtend.xannotation.AbstractMemberProcessor;
import com.logicfishsoftware.commons.xtend.xannotation.Notes;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext;
import org.eclipse.xtend.lib.macro.declaration.MemberDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMemberDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.expression.Expression;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;

@SuppressWarnings("all")
public class AnonymousInnerTypeProcessor extends AbstractMemberProcessor {
  public void doTransform(final MutableMemberDeclaration annotatedMember, final TransformationContext context) {
    this.doTransformInternal(annotatedMember, context);
  }
  
  protected void _doTransformInternal(final MutableMemberDeclaration annotatedField, @Extension final TransformationContext context) {
  }
  
  protected void _doTransformInternal(final MutableFieldDeclaration annotatedField, @Extension final TransformationContext context) {
    final CompilationStrategy _function = new CompilationStrategy() {
      public CharSequence compile(final CompilationContext it) {
        TypeReference _type = annotatedField.getType();
        Type _type_1 = _type.getType();
        CharSequence _buildAnonymousInner = AnonymousInnerTypeProcessor.this.buildAnonymousInner(annotatedField, _type_1, context);
        return _buildAnonymousInner;
      }
    };
    annotatedField.setInitializer(_function);
  }
  
  protected void _doTransformInternal(final MutableMethodDeclaration annotatedMethod, @Extension final TransformationContext context) {
    annotatedMethod.setAbstract(false);
    final CompilationStrategy _function = new CompilationStrategy() {
      public CharSequence compile(final CompilationContext it) {
        String _elvis = null;
        Expression _body = annotatedMethod.getBody();
        String _string = null;
        if (_body!=null) {
          _string=_body.toString();
        }
        if (_string != null) {
          _elvis = _string;
        } else {
          _elvis = ObjectExtensions.<String>operator_elvis(_string, "");
        }
        String _plus = (_elvis + " return ");
        TypeReference _returnType = annotatedMethod.getReturnType();
        Type _type = _returnType.getType();
        CharSequence _buildAnonymousInner = AnonymousInnerTypeProcessor.this.buildAnonymousInner(annotatedMethod, _type, context);
        String _plus_1 = (_plus + _buildAnonymousInner);
        String _plus_2 = (_plus_1 + ";");
        return _plus_2;
      }
    };
    annotatedMethod.setBody(_function);
  }
  
  public CharSequence buildAnonymousInner(final MemberDeclaration member, final Type type, @Extension final TransformationContext context) {
    CharSequence _xblockexpression = null;
    {
      final Type annoType = context.findTypeGlobally(AnonymousInnerType.class);
      String _elvis = null;
      TypeDeclaration _head = null;
      Iterable<TypeDeclaration> _notesAsClasses = Notes.notesAsClasses(AnonymousInnerType.class, member, context);
      if (_notesAsClasses!=null) {
        _head=IterableExtensions.<TypeDeclaration>head(_notesAsClasses);
      }
      String _qualifiedName = null;
      if (_head!=null) {
        _qualifiedName=_head.getQualifiedName();
      }
      if (_qualifiedName != null) {
        _elvis = _qualifiedName;
      } else {
        String _qualifiedName_1 = type.getQualifiedName();
        _elvis = ObjectExtensions.<String>operator_elvis(_qualifiedName, _qualifiedName_1);
      }
      final String qName = _elvis;
      Iterable<String> _map = null;
      Iterable<TypeDeclaration> _notesAsClasses_1 = Notes.notesAsClasses(AnonymousInnerType.class, "typeParam", member, context);
      if (_notesAsClasses_1!=null) {
        final Function1<TypeDeclaration,String> _function = new Function1<TypeDeclaration,String>() {
          public String apply(final TypeDeclaration it) {
            String _qualifiedName = it.getQualifiedName();
            return _qualifiedName;
          }
        };
        _map=IterableExtensions.<TypeDeclaration, String>map(_notesAsClasses_1, _function);
      }
      final Iterable<String> param = _map;
      Type _findTypeGlobally = context.findTypeGlobally(String.class);
      final Iterable<String[]> paramS = Notes.<String[]>notes(_findTypeGlobally, "typeParamS", member);
      Iterable<String> _elvis_1 = null;
      Iterable<String> _notes = Notes.<String>notes(annoType, "ctorParam", member);
      if (_notes != null) {
        _elvis_1 = _notes;
      } else {
        List<String> _emptyList = Collections.<String>emptyList();
        _elvis_1 = ObjectExtensions.<Iterable<String>>operator_elvis(_notes, _emptyList);
      }
      final Iterable<String> ctorParam = _elvis_1;
      final String mixin = Notes.<String>note(annoType, "mixin", member);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("new ");
      _builder.append(qName, "");
      {
        boolean _and = false;
        boolean _notEquals = (!Objects.equal(param, null));
        if (!_notEquals) {
          _and = false;
        } else {
          boolean _isEmpty = IterableExtensions.isEmpty(param);
          boolean _not = (!_isEmpty);
          _and = (_notEquals && _not);
        }
        if (_and) {
          _builder.append("<");
          String _cSVString = CommonsCollections.<String>toCSVString(((String[])Conversions.unwrapArray(param, String.class)));
          _builder.append(_cSVString, "");
          _builder.append(">");
        } else {
          boolean _and_1 = false;
          boolean _notEquals_1 = (!Objects.equal(paramS, null));
          if (!_notEquals_1) {
            _and_1 = false;
          } else {
            boolean _isEmpty_1 = IterableExtensions.isEmpty(paramS);
            boolean _not_1 = (!_isEmpty_1);
            _and_1 = (_notEquals_1 && _not_1);
          }
          if (_and_1) {
            _builder.append("<");
            String _cSVString_1 = CommonsCollections.<String[]>toCSVString(((String[][])Conversions.unwrapArray(paramS, String[].class)));
            _builder.append(_cSVString_1, "");
            _builder.append(">");
          }
        }
      }
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("(");
      String _cSVString_2 = null;
      if (((String[])Conversions.unwrapArray(ctorParam, String.class))!=null) {
        _cSVString_2=CommonsCollections.<String>toCSVString(((String[])Conversions.unwrapArray(ctorParam, String.class)));
      }
      _builder.append(_cSVString_2, "	");
      _builder.append("){");
      String _elvis_2 = null;
      if (mixin != null) {
        _elvis_2 = mixin;
      } else {
        _elvis_2 = ObjectExtensions.<String>operator_elvis(mixin, "");
      }
      _builder.append(_elvis_2, "	");
      _builder.append("}");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  protected void doTransformInternal(final MutableMemberDeclaration annotatedMethod, final TransformationContext context) {
    if (annotatedMethod instanceof MutableMethodDeclaration) {
      _doTransformInternal((MutableMethodDeclaration)annotatedMethod, context);
      return;
    } else if (annotatedMethod instanceof MutableFieldDeclaration) {
      _doTransformInternal((MutableFieldDeclaration)annotatedMethod, context);
      return;
    } else if (annotatedMethod != null) {
      _doTransformInternal(annotatedMethod, context);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(annotatedMethod, context).toString());
    }
  }
}
