package pl.mariuszklinger.core.tests

import org.mockito.Mockito._
import org.mockito.ArgumentCaptor
import org.mockito.stubbing.Answer
import org.mockito.invocation.InvocationOnMock

import org.scalatest.{Matchers, FlatSpec}
import com.esotericsoftware.kryonet.Connection

import pl.mariuszklinger.core.network.DichListener
import pl.mariuszklinger.core.msgs.{MESSAGE_TYPE, Message}
import pl.mariuszklinger.core.Node

class EchoTest extends FlatSpec with Matchers {

    "A NODE" should "send ECHO_REQ and receive the same ECHO_RES" in {

        val n1 = new Node("John", 8081)
        val n2 = new Node("Bob", 8083)

        val dl = mock(classOf[DichListener])
        n1.dich_server.server.addListener(dl)

        val m = ArgumentCaptor.forClass(classOf[Message])

        n1.run()
        n2.run()

        n2.connect("localhost", 8081)

        val message_text = "hello!"
        n2.sendEchoRequest(message_text)

        //Thread.sleep(3000)

        when(dl.echoReqCallback(m.capture()), timeout(35000)).thenAnswer(new Answer[Unit] {
            override def answer(invocation: InvocationOnMock) {
                m.getValue shouldBe message_text
            }
        })
    }
}
