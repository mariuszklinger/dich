package pl.mariuszklinger.core.tools

import scala.collection.mutable.PriorityQueue
import pl.mariuszklinger.core.network.DichClient
import scala.collection.mutable


object NodeOrdering extends Ordering[DichClient](){
    def compare(x:DichClient, y:DichClient) = -1
}

object NeighboursQueue extends mutable.PriorityQueue[DichClient]()(NodeOrdering){
    println("Constructor: " + this.getClass.getSimpleName)
}