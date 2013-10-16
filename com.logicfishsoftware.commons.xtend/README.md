Commons Library For XTend

Released under the Eclipse License 1.0 - please feel free to use and extend!

Language features

Synchronize active annotation - adds 'synchronized' to any method.  See also Synchronization extension which synchronises
a Function over an object instance.
AnonymousInnerType - creates an inline anonymous constructor for a field or method (replaces method body).

XAnnotation tools
XAnnotationsInterpreter - quick expression parser for XAnnotation.
Notes - quick helper to pull XAnnotations from Type instances as collections or objects; Class<?> helpers.

Collection classes
Marginally useful functions; not much here really.


AnonymousInnterType Examples:

// causes 'anon' to be initialised 'new MyClassOrInterface(){}'
@AnonymousInnerType
MyClassOrInterface anon 

// causes the function body to be overwritten with the parametrised 
// constructor invocation.
@AnonymousInnerType(typeParam=#[typeof(Object),typeof(Class<?>)])
MyGenericType<Object,Class<?>> newMyGenericType() {} 

// causes the function body to be overwritten with the parametrised 
// constructor invocation, using mixin strings for the type parameters, rather than classes.
// Strings are only used when given an empty list for the classes parameter 'typeParam'.
@AnonymousInnerType(value=typeof(MyClass),typeParamS=#["U","T"],ctorParam=#["u","t"])
public def static <T extends Something,U> MyInterface<U,? extends T> newMyInterface(T t,U u) {}

Here are the annotation parameters for AnonymousInnerType

	Class<?> value=typeof(Object)  -- the actual type to use in the 'new' statement.

	String[] ctorParam=#[] -- constructor parameters as strings.

	String[] typeParamS=#[] -- type parameters as classes.

	Class<?>[] typeParam=#[] -- type parameters as strings.

	String mixin="" -- this string is inserted as the anonymous class body, in between the curly braces after the constructor call.
	
