package com.logicfishsoftware.commons.xtend.annotations;

import com.logicfishsoftware.commons.xtend.annotations.SynchronizeProcessor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;

@Active(SynchronizeProcessor.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Synchronize {
}
