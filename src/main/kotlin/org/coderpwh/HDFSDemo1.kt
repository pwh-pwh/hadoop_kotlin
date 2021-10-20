package org.coderpwh

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.IOUtils
import java.net.URI

class HDFSDemo1 {
    val HDFSPath:String = "hdfs://localhost:9000"
    var fileSystem:FileSystem
    var configuration:Configuration?
    init {
        configuration = Configuration()
        configuration!!.set("fs.hdfs.impl","org.apache.hadoop.hdfs.DistributedFileSystem")
        this.fileSystem = FileSystem.get(URI(HDFSPath),configuration,"hadoop")
    }
    fun mkdir() {
        fileSystem.mkdirs(Path("test1"))
    }



}


fun main() {
    var hdfsDemo1 = HDFSDemo1()
    hdfsDemo1.mkdir()

}

