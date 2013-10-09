package com.logicfishsoftware.tests.commons.xtend

import com.logicfishsoftware.commons.xtend.annotations.AnonymousInstance
import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester
import org.junit.Test

interface TestDummyInterface {}

class TestDummyClass implements TestDummyInterface {}

interface TestDummyParamInterface<A,B,C> {}

class TestDummyParamClass<A,B,C> implements TestDummyParamInterface<A,B,C> {}

class AnonymousInstanceTests {
	
	extension XtendCompilerTester compilerTester = 
      XtendCompilerTester::newXtendCompilerTester(typeof(AnonymousInstance),typeof(TestDummyInterface)) 
	
	@Test
	def void testAnonymousInstance() {
		'''
		import «typeof(AnonymousInstance).name»
		import «typeof(TestDummyInterface).name»
				
		class AnonymousInstanceContainer {
			@AnonymousInstance
			TestDummyInterface i
		}
		'''.assertCompilesTo('''
		import «typeof(AnonymousInstance).name»;
		import «typeof(TestDummyInterface).name»;
		
		@SuppressWarnings("all")
		public class AnonymousInstanceContainer {
		  @AnonymousInstance
		  private TestDummyInterface i = new «typeof(TestDummyInterface).name»(){};
		}
		''')
	}

	@Test
	def void testAnonymousInstanceClass() {
		'''
		import «typeof(AnonymousInstance).name»
		import «typeof(AnonymousInstance).name»
		import «typeof(TestDummyInterface).name»
		import «typeof(TestDummyClass).name»
				
		class AnonymousInstanceContainer {
			@AnonymousInstance(value=typeof(TestDummyClass))
			TestDummyInterface i
		}
		'''.assertCompilesTo('''
		import «typeof(AnonymousInstance).name»;
		import «typeof(TestDummyClass).name»;
		import «typeof(TestDummyInterface).name»;
		
		@SuppressWarnings("all")
		public class AnonymousInstanceContainer {
		  @AnonymousInstance(value = TestDummyClass.class)
		  private TestDummyInterface i = new «typeof(TestDummyClass).name»(){};
		}
		''')
	}

	@Test
	def void testAnonymousInstanceClassArgs() {
		'''
		import «typeof(AnonymousInstance).name»
		import «typeof(TestDummyInterface).name»
		import «typeof(TestDummyClass).name»
				
		class AnonymousInstanceContainer {
			@AnonymousInstance(value=typeof(TestDummyClass),mixin=#["\"Hello\"","11"])
			TestDummyInterface i
		}
		'''.assertCompilesTo('''
		import «typeof(AnonymousInstance).name»;
		import «typeof(TestDummyClass).name»;
		import «typeof(TestDummyInterface).name»;
		
		@SuppressWarnings("all")
		public class AnonymousInstanceContainer {
		  @AnonymousInstance(value = TestDummyClass.class, mixin = { "\"Hello\"", "11" })
		  private TestDummyInterface i = new «typeof(TestDummyClass).name»("Hello",11){};
		}
		''')
	}

	@Test
	def void testAnonymousInstanceClassParam() {
		'''
		import «typeof(AnonymousInstance).name»
		import «typeof(TestDummyParamInterface).name»
		import «typeof(TestDummyParamClass).name»
				
		class AnonymousInstanceContainer {
			@AnonymousInstance(value=typeof(TestDummyParamClass),parameters=#[typeof(Boolean),typeof(Long),typeof(Double)])
			TestDummyParamInterface<Boolean,Long,Double> i
		}
		'''.assertCompilesTo('''
		import «typeof(AnonymousInstance).name»;
		import «typeof(TestDummyParamClass).name»;
		import «typeof(TestDummyParamInterface).name»;
		
		@SuppressWarnings("all")
		public class AnonymousInstanceContainer {
		  @AnonymousInstance(value = TestDummyParamClass.class, parameters = { Boolean.class, Long.class, Double.class })
		  private TestDummyParamInterface<Boolean,Long,Double> i = new «typeof(TestDummyParamClass).name»<java.lang.Boolean,java.lang.Long,java.lang.Double>(){};
		}
		''')
	}

}