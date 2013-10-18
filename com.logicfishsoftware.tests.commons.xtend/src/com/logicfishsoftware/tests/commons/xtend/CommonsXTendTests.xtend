package com.logicfishsoftware.tests.commons.xtend

import java.util.HashMap
import java.util.Map
import org.junit.Test

import static extension com.logicfishsoftware.commons.xtend.CommonsCollections.*
import static extension org.junit.Assert.*

class CommonsXTendTests {
	@Test
	def void testAsCollection() {
		val String x = "Hello"
		val String[] _x = x.asCollection
		1.assertEquals(_x.length)
		x.assertSame(_x.head)
	}

	@Test
	def void testAsNotCollection() {
		val String[] x = #[ "Hello" ]
		val String _x = x.asNotCollection
		x.head.assertSame(_x)
	}
	
	@Test
	def void testWithoutLast() {
		val String[] x = #["One","Two","Three"]
		val String[] _x = x.withoutLast
		2.assertEquals(_x.length)
		#["One","Two"].assertArrayEquals(_x)
	}
	
	@Test
	def void testIterableAppend() {
		val a = #[ 1 ]
		val b = a.append(2)
		
		b => [
			assertEquals(2,length)
			assertEquals(#[1,2].toList,toList)			
		]
		
		val c = b.append(3,4,5)
		val d = b.append(#[3,4,5])
		
		assertEquals(#[1,2,3,4,5].toList,c.toList)
		assertEquals(#[1,2,3,4,5].toList,d.toList)
		assertEquals(c.toList,d.toList)
		
		val e = d.prepend(0)
		assertEquals(#[0,1,2,3,4,5].toList,e.toList)
		
		val f = e.prepend(-3,-2,-1)
		assertEquals(#[-3,-2,-1,0,1,2,3,4,5].toList,f.toList)
	}
	
	@Test
	def void testToCSVString() {
		val String[] x = #["One","Two","Three"]
		val String _x = x.toCSVString()
		"One,Two,Three".assertEquals(_x)
	}	
	@Test
	def void testToString() {
		val String[] x = #["One","Two","Three"]
		val String _x = x.toString("!!")
		"One!!Two!!Three".assertEquals(_x)
	}	
	@Test
	def void testMapPutRemovePairs() {
		val Map<String,String> map = new HashMap<String,String>()
//		map += { "x" -> "Hello"  }
		map => [
			put({ "x" -> "Hello"  })
			put({ "y" -> "Goodbye."  })			
		]

		assertEquals(2,map.size)		
		assertTrue(map.containsKey("x"))
		assertTrue(map.containsKey("y"))

		map.removePair({ "y" -> "Goodbye."  })

		assertEquals(1,map.size)		
		assertTrue(map.containsKey("x"))
		assertFalse(map.containsKey("y"))
	}
	@Test
	def void testFilterKeys() {
		val Map<String,String> map = new HashMap<String,String>()
//		map += { "x" -> "Hello"  }
		map => [ 
			put({ "x" -> "Hello"  })
			put({ "y" -> "Goodbye."  })
		]
		
		assertTrue(map.containsKey("x"))
		assertTrue(map.containsKey("y"))

		val Map<String,String> filtered = map.filterKeys[
			it=="x"
		]
		assertEquals(1,filtered.size())
		assertTrue(filtered.containsKey("x"))
		assertFalse(filtered.containsKey("y"))
		assertEquals("Hello",filtered.get("x"))

		map.put({ "y" -> "Goodbye."  })
		assertFalse(filtered.containsKey("y"))
		map.put({ "z" -> "Goodbye."  })
		assertFalse(filtered.containsKey("z"))
	}
	@Test
	def void testFilterValues() {
		val Map<String,String> map = new HashMap<String,String>()
		map.put({ "x" -> "Hello"  })
		map.put({ "y" -> "Goodbye."  })
		
		assertTrue(map.containsKey("x"))
		assertTrue(map.containsValue("Hello"))
		assertTrue(map.containsKey("y"))
		assertTrue(map.containsValue("Goodbye."))

		val Map<String,String> filtered = map.filterValues[
			it!="Goodbye."
		]
		assertEquals(1,filtered.size())
		assertTrue(filtered.containsKey("x"))
		assertTrue(filtered.containsValue("Hello"))
		assertFalse(filtered.containsKey("y"))
		assertFalse(filtered.containsValue("Goodbye."))
		assertEquals("Hello",filtered.get("x"))

		map.put({ "z" -> "Goodbye."  })
		assertFalse(filtered.containsKey("z"))
		assertFalse(filtered.containsValue("Goodbye."))
	}
}
