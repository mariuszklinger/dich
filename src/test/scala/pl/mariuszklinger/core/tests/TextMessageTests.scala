package pl.mariuszklinger.core.tests

import pl.mariuszklinger.core.Node

import org.scalatest.{Matchers, FlatSpec}
import junit.framework.Assert._

class TextMessageTests extends FlatSpec with Matchers {

    "Node1" should "send text message and Node2 should receive it" in {

        val n1 = new Node("John", 8081)
        val n2 = new Node("Bob", 8083)

        n1.run()
        n2.run()

        n2.connect("localhost", 8081)

        val message_text = "hello!"
        n2.sendText(message_text)

        assertTrue(n1.message_processor.message_log.getLatestMessages().size == 0)

        Thread.sleep(3000)

        assertTrue(n1.message_processor.message_log.getLatestMessages().size > 0)
    }
}
