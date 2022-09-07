package com.sdq.libs.base.designpattern.action11

/**
 *
 * @PackageName com.sdq.libs.base.designpattern.action11
 * @date 2022/9/7 15:14
 * @author songdongqi
 */
/**
 * 访问者模式
 * Allows for one or more operation to be applied to a set of objects at runtime,
 * decoupling the operations from the object structure.
 * 允许一个或者多个操作应用到一组对象上，解耦操作和对象本身。
 */
class Extractor : Visitor {
    override fun visit(pdfFile: PdfFile) {
        TODO("Not yet implemented")
    }

    override fun visit(pptFile: PPTFile) {
        TODO("Not yet implemented")
    }

    override fun visit(wordFile: WordFile) {
        TODO("Not yet implemented")
    }
}

class Compressor : Visitor {
    override fun visit(pdfFile: PdfFile) {
        TODO("Not yet implemented")
    }

    override fun visit(pptFile: PPTFile) {
        TODO("Not yet implemented")
    }

    override fun visit(wordFile: WordFile) {
        TODO("Not yet implemented")
    }
}

abstract class ResourceFile {
    abstract fun accept(visitor: Visitor)
}

class PPTFile : ResourceFile() {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

class PdfFile : ResourceFile() {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

class WordFile : ResourceFile() {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

interface Visitor {
    fun visit(pdfFile: PdfFile)
    fun visit(pptFile: PPTFile)
    fun visit(wordFile: WordFile)
}