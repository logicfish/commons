package com.logicfishsoftware.commons.emf;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.logicfishsoftware.commons.emf.RuntimeInjectorProvider;
import java.util.Arrays;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.parsetree.reconstr.Serializer;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;

@SuppressWarnings("all")
public class ToString {
  @Inject
  @Extension
  private static IQualifiedNameProvider _iQualifiedNameProvider;
  
  private static Serializer SERIALIZER = null;
  
  @Inject
  private static RuntimeInjectorProvider injectorProvider;
  
  private static Serializer getSerializer() {
    boolean _equals = Objects.equal(ToString.SERIALIZER, null);
    if (_equals) {
      Injector _injector = null;
      if (ToString.injectorProvider!=null) {
        _injector=ToString.injectorProvider.getInjector();
      }
      Serializer _instance = null;
      if (_injector!=null) {
        _instance=_injector.<Serializer>getInstance(Serializer.class);
      }
      ToString.SERIALIZER = _instance;
    }
    return ToString.SERIALIZER;
  }
  
  public static <T extends Object> String valueOf(final T t) {
    String _stringValue = ToString.getStringValue(t);
    return _stringValue;
  }
  
  protected static String _getStringValue(final Object o) {
    String _plus = ("" + o);
    return _plus;
  }
  
  protected static String _getStringValue(final EObject o) {
    String _elvis = null;
    Serializer _serializer = ToString.getSerializer();
    String _serialize = null;
    if (_serializer!=null) {
      _serialize=_serializer.serialize(o);
    }
    if (_serialize != null) {
      _elvis = _serialize;
    } else {
      _elvis = ObjectExtensions.<String>operator_elvis(_serialize, "null");
    }
    return _elvis;
  }
  
  public static <T extends Object> String toString(final EObject o) {
    QualifiedName _fullyQualifiedName = ToString._iQualifiedNameProvider.getFullyQualifiedName(o);
    String _string = _fullyQualifiedName.toString();
    return _string;
  }
  
  public static String getStringValue(final Object o) {
    if (o instanceof EObject) {
      return _getStringValue((EObject)o);
    } else if (o != null) {
      return _getStringValue(o);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(o).toString());
    }
  }
}
