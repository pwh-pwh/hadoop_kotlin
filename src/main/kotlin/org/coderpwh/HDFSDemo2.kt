package org.coderpwh

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FSDataInputStream
import org.apache.hadoop.fs.FSDataOutputStream
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.apache.hadoop.hdfs.client.HdfsUtils
import org.coderpwh.filter.PathFilterDemo
import org.coderpwh.utils.HDFSUtil
import java.io.PrintStream
import java.net.URI

class HDFSDemo2 {
    fun doMerge(inputPath:String,outPath:String) {
        val configuration:Configuration = Configuration()
        var fsSource = FileSystem.get(URI(inputPath), configuration, "hadoop")
        var fsDst = FileSystem.get(URI(outPath), configuration, "hadoop")


        var fileStatus = HDFSUtil.getFileStatus(fsSource, Path(inputPath), PathFilterDemo(".*\\.xml"))
        var outStream:FSDataOutputStream = fsDst.create(Path(outPath))
        var printStream = PrintStream(System.out)
        fileStatus?.forEach {
            var path = it.path
            var inputStream:FSDataInputStream = fsSource.open(path)
            var byteArray = ByteArray(1024)
            var read:Int = inputStream.read(byteArray)
            while (read>0) {
                outStream.write(byteArray)
                read = inputStream.read(byteArray)
                printStream.write(byteArray)
            }
            inputStream.close()
        }
        printStream.close()
        outStream.close()

    }
}
fun main() {
    var hdfsDemo2 = HDFSDemo2()
    hdfsDemo2.doMerge("hdfs://localhost:9000/user/hadoop/input/","hdfs://localhost:9000/user/hadoop/demo1.txt")
}