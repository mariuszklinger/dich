package pl.mariuszklinger.core.tests

import pl.mariuszklinger.core.archive.MessageProcessor
import pl.mariuszklinger.core.msgs.{MESSAGE_TYPE, Message}
import pl.mariuszklinger.core.Node
import pl.mariuszklinger.tools.CustomClassLoader
import pl.mariuszklinger.core.tests.A

import junit.framework.Assert._
import org.scalatest.{Matchers, FlatSpec}
import org.junit.runner.RunWith
import java.net.URLClassLoader
import java.lang.reflect.Method

class DiscoveringNodesTests  extends FlatSpec with Matchers {

    "Node A" should "connect to Node C" in {

        val n1 = new Node("A", 8081)
        val n2 = new Node("B", 8083)
        val n3 = new Node("C", 8084)

        n2.connect("localhost", 8084)
        n1.connect("localhost", 8083)

        assertTrue(true)
    }

    "obj1 and obj2" should "have not-shared companion object each" in {

        val class_loader1 = new CustomClassLoader()
        val class_loader2 = new CustomClassLoader()

        val clazz1 = Class.forName("pl.mariuszklinger.core.tests.A", true, class_loader1)
        val clazz2 = Class.forName("pl.mariuszklinger.core.tests.A", true, class_loader2)


        val obj1 = clazz1.getDeclaredConstructor(classOf[String]).newInstance("1")
        val obj2 = clazz2.getDeclaredConstructor(classOf[String]).newInstance("2")

        val m1: Method = obj1.getClass.getMethod("getSecret")
        val m2: Method = obj2.getClass.getMethod("getSecret")

        println("obj1.getSecret() == " + m1.invoke(obj1))
        println("obj2.getSecret() == " + obj2.asInstanceOf[{def getSecret : String}].getSecret)
    }
}
