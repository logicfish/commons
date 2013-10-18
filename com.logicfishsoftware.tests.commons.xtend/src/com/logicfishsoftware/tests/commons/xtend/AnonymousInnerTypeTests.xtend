package com.logicfishsoftware.tests.commons.xtend

import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester
import org.junit.Test
import com.logicfishsoftware.commons.xtend.annotations.AnonymousInnerType

interface TestDummyInterface {}

class TestDummyClass implements TestDummyInterface {}

interface TestDummyParamInterface<A,B,C> {}

class TestDummyParamClass<A,B,C> implements TestDummyParamInterface<A,B,C> {}

class AnonymousInnerTypeTests {
	
	extension XtendCompilerTester compilerTester = 
      XtendCompilerTester::newXtendCompilerTester(typeof(AnonymousInnerType),typeof(TestDummyInterface)) 
	
	@Test
	def void testAnonymousInnerType() {
		'''
		import «typeof(AnonymousInnerType).name»
		import «typeof(TestDummyInterface).name»
				
		class AnonymousInnerTypeContainer {
			@AnonymousInnerType
			TestDummyInterface i
		}
		'''.assertCompilesTo('''
		import «typeof(AnonymousInnerType).name»;
		import «typeof(TestDummyInterface).name»;
		
		@SuppressWarnings("all")
		public class AnonymousInnerTypeContainer {
		  @AnonymousInnerType
		  private TestDummyInterface i = new «typeof(TestDummyInterface).name»(){};
		}
		''')
	}
	@Test
	def void testAnonymousInnerTypeMethod() {
		'''
		import «typeof(AnonymousInnerType).name»
		import «typeof(TestDummyInterface).name»
				
		class AnonymousInnerTypeContainer {
			@AnonymousInnerType
			def TestDummyInterface i() { return null; }
		}
		'''.assertCompilesTo('''
		import «typeof(AnonymousInnerType).name»;
		import «typeof(TestDummyInterface).name»;
		
		@SuppressWarnings("all")
		public class AnonymousInnerTypeContainer {
		  @AnonymousInnerType
		  public TestDummyInterface i() {
		     return new «typeof(TestDummyInterface).name»(){};
		  }
		}
		''')
	}
	@Test
	def void testAnonymousInnerTypeMethod2() {
		'''
		import «typeof(AnonymousInnerType).name»
		import «typeof(TestDummyInterface).name»
				
		class AnonymousInnerTypeContainer {
			@AnonymousInnerType
			def TestDummyInterface i() { 
				val i = 10
				return null
			}
		}
		'''.assertCompilesTo('''
		import «typeof(AnonymousInnerType).name»;
		import «typeof(TestDummyInterface).name»;
		
		@SuppressWarnings("all")
		public class AnonymousInnerTypeContainer {
		  @AnonymousInnerType
		  public TestDummyInterface i() {
		     return new «typeof(TestDummyInterface).name»(){};
		  }
		}
		''')
	}
	@Test
	def void testAnonymousInnerTypeClass() {
		'''
		import «typeof(AnonymousInnerType).name»
		import «typeof(AnonymousInnerType).name»
		import «typeof(TestDummyInterface).name»
		import «typeof(TestDummyClass).name»
				
		class AnonymousInnerTypeContainer {
			@AnonymousInnerType(value=typeof(TestDummyClass))
			TestDummyInterface i
		}
		'''.assertCompilesTo('''
		import «typeof(AnonymousInnerType).name»;
		import «typeof(TestDummyClass).name»;
		import «typeof(TestDummyInterface).name»;
		
		@SuppressWarnings("all")
		public class AnonymousInnerTypeContainer {
		  @AnonymousInnerType(value = TestDummyClass.class)
		  private TestDummyInterface i = new «typeof(TestDummyClass).name»(){};
		}
		''')
	}

	@Test
	def void testAnonymousInnerTypeClassArgs() {
		'''
		import «typeof(AnonymousInnerType).name»
		import «typeof(TestDummyInterface).name»
		import «typeof(TestDummyClass).name»
				
		class AnonymousInnerTypeContainer {
			@AnonymousInnerType(value=typeof(TestDummyClass),ctorParam=#["\"Hello\"","11"])
			TestDummyInterface i
		}
		'''.assertCompilesTo('''
		import «typeof(AnonymousInnerType).name»;
		import «typeof(TestDummyClass).name»;
		import «typeof(TestDummyInterface).name»;
		
		@SuppressWarnings("all")
		public class AnonymousInnerTypeContainer {
		  @AnonymousInnerType(value = TestDummyClass.class, ctorParam = { "\"Hello\"", "11" })
		  private TestDummyInterface i = new «typeof(TestDummyClass).name»("Hello",11){};
		}
		''')
	}

	@Test
	def void testAnonymousInnerTypeClassParam() {
		'''
		import «typeof(AnonymousInnerType).name»
		import «typeof(TestDummyParamInterface).name»
		import «typeof(TestDummyParamClass).name»
				
		class AnonymousInnerTypeContainer {
			@AnonymousInnerType(value=typeof(TestDummyParamClass),parameters=#[typeof(Boolean),typeof(Long),typeof(Double)])
			TestDummyParamInterface<Boolean,Long,Double> i
		}
		'''.assertCompilesTo('''
		import «typeof(AnonymousInnerType).name»;
		import «typeof(TestDummyParamClass).name»;
		import «typeof(TestDummyParamInterface).name»;
		
		@SuppressWarnings("all")
		public class AnonymousInnerTypeContainer {
		  @AnonymousInnerType(value = TestDummyParamClass.class, parameters = { Boolean.class, Long.class, Double.class })
		  private TestDummyParamInterface<Boolean,Long,Double> i = new «typeof(TestDummyParamClass).name»<java.lang.Boolean,java.lang.Long,java.lang.Double>(){};
		}
		''')
	}
	@Test
	def void testAnonymousInnerTypeMixin() {
		'''
		import «typeof(AnonymousInnerType).name»
		import «typeof(TestDummyParamInterface).name»
		import «typeof(TestDummyParamClass).name»
				
		class AnonymousInnerTypeContainer {
			@AnonymousInnerType(value=typeof(TestDummyParamClass),parameters=#[typeof(Boolean),typeof(Long),typeof(Double)],mixin="String s = null;")
			TestDummyParamInterface<Boolean,Long,Double> i
		}
		'''.assertCompilesTo('''
		import «typeof(AnonymousInnerType).name»;
		import «typeof(TestDummyParamClass).name»;
		import «typeof(TestDummyParamInterface).name»;
		
		@SuppressWarnings("all")
		public class AnonymousInnerTypeContainer {
		  @AnonymousInnerType(value = TestDummyParamClass.class, parameters = { Boolean.class, Long.class, Double.class }, mixin = "String s = null;")
		  private TestDummyParamInterface<Boolean,Long,Double> i = new «typeof(TestDummyParamClass).name»<java.lang.Boolean,java.lang.Long,java.lang.Double>(){String s = null;};
		}
		''')
	}
}