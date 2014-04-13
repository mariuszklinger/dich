package pl.mariuszklinger.core.tests

import org.scalatest.{Matchers, FlatSpec}
import org.mockito.Mockito._

import pl.mariuszklinger.core.archive.MessageProcessor
import pl.mariuszklinger.core.msgs.{Message, MESSAGE_TYPE}
import junit.framework.Assert

class MessageProcessorTests extends FlatSpec with Matchers {

    "MessageProcessor gets chat Message and" should "archive it" in {
        val msg_processor = mock(classOf[MessageProcessor])
    }

    "MessageProcessor gets chat Message and" should "send it further to neighbours" in {

    }

    "MessageProcessor" should "provide required chat history" in {

    }

    "MessageProcessor gets Message Log and" should "archive it in correct order" in {

    }

    "MessageProcessor" should "not produce the same hash codes for 2 different Messages" in {
        val m1 = new Message(MESSAGE_TYPE.CHAT, "A", "B")
        val m2 = new Message(MESSAGE_TYPE.CHAT, "C", "D")

        Assert.assertNotSame(MessageProcessor.getHash(m1), MessageProcessor.getHash(m2))
    }

}
