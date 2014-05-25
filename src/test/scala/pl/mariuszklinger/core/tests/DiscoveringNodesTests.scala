package pl.mariuszklinger.core.tests

import junit.framework.Assert._
import org.scalatest.{Matchers, FlatSpec}
import org.junit.runner.RunWith
import java.net.URLClassLoader
import java.lang.reflect.Method

import pl.mariuszklinger.core.archive.MessageProcessor
import pl.mariuszklinger.core.msgs.{MESSAGE_TYPE, Message}
import pl.mariuszklinger.core.Node

class DiscoveringNodesTests  extends FlatSpec with Matchers {

    "Node A" should "connect to Node C" in {

        val n1 = new Node("A", 8081)
        val n2 = new Node("B", 8083)
        val n3 = new Node("C", 8084)

        n2.connect("localhost", 8084)
        n1.connect("localhost", 8083)

        //assertTrue(n1.neighbours.size == 2)
    }
}
