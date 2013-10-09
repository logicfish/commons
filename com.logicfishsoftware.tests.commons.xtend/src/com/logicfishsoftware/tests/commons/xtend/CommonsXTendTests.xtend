package com.logicfishsoftware.tests.commons.xtend

import org.junit.Test

import static extension com.logicfishsoftware.commons.xtend.CommonsCollections.*
import static extension com.logicfishsoftware.commons.xtend.ToString.*
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
	def void testToString() {
		val String[] x = #["One","Two","Three"]
		val String _x = x.toString("!!")
		"One!!Two!!Three".assertEquals(_x)
	}	
}
