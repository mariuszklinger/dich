
import pl.mariuszklinger.core.network.DichListener
import pl.mariuszklinger.core.Node

object Main {

    def main(args: Array[String]){
        val n1 = new Node("John", 8081)
        val n2 = new Node("Bob", 8083)

        val dl = new DichListener
        n1.dich_server.listener = dl

        n1.run()
        n2.run()

        n2.connect("localhost", 8081)
        n2.sendEchoRequest("hello!")
    }
}
