package pl.mariuszklinger.core.msgs

import pl.mariuszklinger.core.archive.MessageProcessor

object MESSAGE_TYPE {

    type MT = Byte

    val ECHO_REQ:MT = -2.toByte
    val ECHO_RES:MT = -1.toByte

    val CHAT:MT = 1.toByte
    val NOTICE:MT = 2.toByte
    val HANDSHAKE_REQ:MT = 3.toByte
    val HANDSHAKE_RES:MT = 4.toByte
}

object Message{

    def getPlaceholderForHash(h: String): Message = {
        val m = new Message(MESSAGE_TYPE.CHAT, "", "", null)
        m.msg_hash = h
        m
    }
}

class Message(_mtype: MESSAGE_TYPE.MT, _nick: String, _msg_obj: String, _parent_hc: String = null) {

    var obj = _msg_obj
    val mtype = _mtype
    val nick = _nick

    var msg_hash = MessageProcessor.getHash(this)

    // keep hashcode of parent message
    var parent_hc = null

    def this() = this(MESSAGE_TYPE.CHAT, "Unknown", "= DEFAULT MESSAGE =")

    override def toString(): String = {
        ">" + nick + ": " + obj
    }
}
