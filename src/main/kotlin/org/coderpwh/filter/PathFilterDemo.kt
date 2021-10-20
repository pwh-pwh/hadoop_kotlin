package org.coderpwh.filter

import org.apache.hadoop.fs.Path
import org.apache.hadoop.fs.PathFilter
import org.apache.hadoop.io.IOUtils

class PathFilterDemo constructor(reg:String):PathFilter {
    private var reg:String
    init{
        this.reg = reg
    }
    override fun accept(path: Path): Boolean {
        return !path.toString().matches(Regex(reg))
    }
}