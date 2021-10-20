package org.coderpwh.utils

import org.apache.hadoop.fs.FileStatus
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.apache.hadoop.fs.PathFilter

object HDFSUtil {
    fun getFileStatus(fileSystem:FileSystem,path:Path,filter:PathFilter): Array<out FileStatus>? = fileSystem.listStatus(path,filter)

}