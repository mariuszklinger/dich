package pl.mariuszklinger.core.tools

import pl.mariuszklinger.core.Node

import java.net.BindException

object NodeFactory {

    private val START_PORT = 10000

    private val getNextPort = (() => {
        var n = START_PORT
        () => {
            n += 1
            n
        }
    })()

    private val getNextNick = (() => {
        var i = 0
        val nicknames = Array("Luke", "Anakin", "Boba", "Leia", "Han", "Amidala")

        () => {
            i += 1
            nicknames(i % nicknames.length)
        }
    })()

    def getNode(): Node = {

        do{
            try{
                return new Node(getNextNick(), getNextPort())
            }
            catch{
                case e: BindException =>
            }
        } while(true)

        null
    }
}