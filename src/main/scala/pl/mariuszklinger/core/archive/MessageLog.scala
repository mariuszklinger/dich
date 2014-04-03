package pl.mariuszklinger.core.archive

import scala.collection.mutable
import pl.mariuszklinger.core.msgs.Message

object MessageLog extends mutable.HashMap[String, Message]{
    val message_buffer = new mutable.Stack[Message]()
}
