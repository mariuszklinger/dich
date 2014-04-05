import pl.mariuszklinger.core.msgs.MESSAGE_TYPE
import pl.mariuszklinger.core.Node
import scala.collection.mutable

object Main {

    def main(args: Array[String]){
        var n1 = new Node("John", 8081)
        var n2 = new Node("Bob", 8083)

        n2.connect("localhost", 8081)
        n2.sendEchoRequest("hello!")
    }
}
