package pl.mariuszklinger.core.archive

import com.google.common.hash.{HashCode, Hashing, HashFunction}
import com.google.common.base.Charsets

import pl.mariuszklinger.core.msgs._

object MessageProcessor{

    def getHash(m: Message): String = {
        val hf: HashFunction = Hashing.md5()
        val hc: HashCode = hf.newHasher().putString(m.nick + m.obj + m.parent_hc, Charsets.UTF_8).hash()
        hc.toString
    }
}

class MessageProcessor {

    val message_log = new MessageLog

    def archiveMessage(m: Message){
        message_log.addMessage(m)
    }

}
