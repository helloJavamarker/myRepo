package com.test.mark.zhang.test.agency.tao.kafka

import java.util.Random
import scala.collection.mutable

/**
 * 源码中的 主题创建分区副本分布算法  测试
 */
//object AssignPartitionTest {
//
//    val rand:Random = new Random()
//
//    private def assign(nPartitions: Int,
//                       replicationFactor: Int,
//                       brokerList: Seq[Int],
//                       fixedStartIndex: Int,
//                       startPartitionId: Int): mutable.Map[Int, Seq[Int]] = {
//
//      val ret = mutable.Map[Int, Seq[Int]]()
//      val brokerArray = brokerList.toArray
//      val startIndex = if (fixedStartIndex >= 0) fixedStartIndex else rand.nextInt(brokerArray.length)
//      var currentPartitionId = math.max(0, startPartitionId)
//      var nextReplicaShift = if (fixedStartIndex >= 0) fixedStartIndex else rand.nextInt(brokerArray.length)
//      for (_ <- 0 until nPartitions) {
//        if (currentPartitionId > 0 && (currentPartitionId % brokerArray.length == 0))
//          nextReplicaShift += 1
//        val firstReplicaIndex = (currentPartitionId + startIndex) % brokerArray.length
//        val replicaBuffer = mutable.ArrayBuffer(brokerArray(firstReplicaIndex))
//        for (j <- 0 until replicationFactor - 1)
//          replicaBuffer += brokerArray(replicaIndex(firstReplicaIndex, nextReplicaShift, j, brokerArray.length))
//        ret.put(currentPartitionId, replicaBuffer)
//        currentPartitionId += 1
//      }
//      ret
//    }
//
//    private def replicaIndex(firstReplicaIndex: Int, secondReplicaShift: Int, replicaIndex: Int, nBrokers: Int): Int = {
//      val shift = 1 + (secondReplicaShift + replicaIndex) % (nBrokers - 1)
//      (firstReplicaIndex + shift) % nBrokers
//    }
//
//    def main(args: Array[String]): Unit = {
//      val intToInts: mutable.Map[Int, Seq[Int]] = assign(3, 3, Seq(0, 1, 2,3,4), -1, -1)
//      println(intToInts)
//    }
//
//}
