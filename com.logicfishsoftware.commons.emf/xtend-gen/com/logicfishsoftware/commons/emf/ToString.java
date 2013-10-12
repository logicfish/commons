package com.logicfishsoftware.commons.emf;

import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.parsetree.reconstr.Serializer;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class ToString {
  @Inject
  @Extension
  private static IQualifiedNameProvider _iQualifiedNameProvider;
  
  @Inject
  private static Serializer SERIALIZER;
  
  private static Serializer getSerializer() {
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
    Serializer _serializer = ToString.getSerializer();
    String _serialize = _serializer.serialize(o);
    return _serialize;
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
