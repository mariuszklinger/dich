package pl.mariuszklinger.core.tests

import org.scalatest.{Matchers, FlatSpec}
import pl.mariuszklinger.core.Node
import org.mockito.Mockito._
import pl.mariuszklinger.core.network.DichListener
import org.mockito.ArgumentCaptor
import pl.mariuszklinger.core.msgs.{MESSAGE_TYPE, Message}
import com.esotericsoftware.kryonet.Connection
import org.mockito.stubbing.Answer
import org.mockito.invocation.InvocationOnMock

class EchoTest extends FlatSpec with Matchers {

    "A NODE" should "send ECHO_REQ and receive the same ECHO_RES" in {

        val n1 = new Node("John", 8081)
        val n2 = new Node("Bob", 8083)

        val dl = mock(classOf[DichListener])
        n1.dich_server.listener = dl

        val c = ArgumentCaptor.forClass(classOf[Connection])
        val m = ArgumentCaptor.forClass(classOf[Message])

        n2.connect("localhost", 8081)

        val message_text = "hello!"

        when(dl.echoReqCallback(c.capture(), m.capture())).then(new Answer[Unit] {
            override def answer(invocation: InvocationOnMock) {
                m.getValue.obj shouldBe message_text
            }
        })

        n2.sendEchoRequest(message_text)
    }
}
