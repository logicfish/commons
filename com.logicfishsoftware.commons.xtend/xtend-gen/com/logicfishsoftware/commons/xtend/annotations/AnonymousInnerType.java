package com.logicfishsoftware.commons.xtend.annotations;

import com.logicfishsoftware.commons.xtend.annotations.AnonymousInnerTypeProcessor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;

/**
 * Create an anonymous instance of type 'value', using the optional type parameters. The optional mixin string
 * is used as the constructor parameter list.
 */
@Active(AnonymousInnerTypeProcessor.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface AnonymousInnerType {
  public Class<? extends Object> value() default Object.class;
  public Class<? extends Object>[] parameters() default {};
  public String[] ctorParam() default {};
  public String mixin() default "";
}
