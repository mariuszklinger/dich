package pl.mariuszklinger.core.tests

import org.scalatest.{Matchers, FlatSpec}
import org.mockito.Mockito._
import pl.mariuszklinger.core.network.DichListener
import pl.mariuszklinger.core.archive.MessageProcessor

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
}
