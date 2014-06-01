package pl.mariuszklinger.core.tools

import pl.mariuszklinger.core.network.DichClient
import scala.collection.mutable

object NodeOrdering extends Ordering[DichClient](){
    def compare(x: DichClient, y: DichClient) = y.ping compare x.ping
}

class NeighboursQueue extends mutable.PriorityQueue[DichClient]()(NodeOrdering){
    override val ord:Ordering[DichClient] = NodeOrdering
}