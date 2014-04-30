package pl.mariuszklinger.core.tests

import org.scalatest.{Matchers, FlatSpec}

import pl.mariuszklinger.core.archive.MessageProcessor
import pl.mariuszklinger.core.msgs.{Message, MESSAGE_TYPE}

import junit.framework.Assert._

class MessageProcessorTests extends FlatSpec with Matchers {

    "MessageProcessor gets chat Message and" should "archive it" in {
        val msg_processor = new MessageProcessor
        val msg = new Message(MESSAGE_TYPE.CHAT, "John", "X")
        msg_processor.archiveMessage(msg)

        var res = false
        res = msg_processor.message_log.getLatestMessages().contains(msg)

        assertTrue(res)
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

        assertNotSame(MessageProcessor.getHash(m1), MessageProcessor.getHash(m2))
    }

}