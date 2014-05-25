package pl.mariuszklinger.core.tests

import org.scalatest.{Matchers, FlatSpec}
import junit.framework.Assert._

import pl.mariuszklinger.core.archive.MessageProcessor
import pl.mariuszklinger.core.msgs.{Message, MESSAGE_TYPE}

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


    "MessageProcessor" should "produce the same hash codes for 2 the same Messages" in {
        val m1 = new Message(MESSAGE_TYPE.CHAT, "A", "B")
        val m2 = new Message(MESSAGE_TYPE.CHAT, "A", "B")

        assertTrue(MessageProcessor.getHash(m1).equals(MessageProcessor.getHash(m2)))
    }

    "MessageProcessor" should "not produce the same hash codes for 2 different Messages" in {
        val m1 = new Message(MESSAGE_TYPE.CHAT, "A", "B")
        val m2 = new Message(MESSAGE_TYPE.CHAT, "C", "D")

        assertFalse(MessageProcessor.getHash(m1).equals(MessageProcessor.getHash(m2)))
    }

    "MessageProcessor gets messages and" should "archive every once." in {

        val msg_processor = new MessageProcessor

        val msg1 = new Message(MESSAGE_TYPE.CHAT, "John", "Message text.", "abcdef")
        val msg2 = new Message(MESSAGE_TYPE.CHAT, "John", "Message text.", "abcdef")

        msg_processor.archiveMessage(msg1)
        msg_processor.archiveMessage(msg2)

        assertTrue(msg_processor.message_log.getLatestMessages().size == 1)
    }
}