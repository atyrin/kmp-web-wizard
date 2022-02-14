package org.jetbrains.webwiz

import org.w3c.dom.HTMLElement


@JsModule("./prism.js")
@JsNonModule
external class Prism {
    companion object {
        fun highlight(code: String, lang: dynamic, language: String): String
        val languages: dynamic
    }
}

/*
https://prismjs.com/download.html#themes=prism&languages=markup+clike+bash+c+groovy+ignore+java+kotlin+objectivec+properties+swift
regenerate with new languages and put to prism.js
 */
enum class SupportedLanguages(name: String){
    Kotlin("kotlin"),
    XML("xml"),
    Properties("properties"),
    Txt("txt"),
    Shell("shell"),
    Java("java"),
    Ignore("ignore"),
    ObjectiveC("objc"),
    Swift("swift"),
    Groovy("groovy")
}

fun HTMLElement.setPrism(code: String, language: SupportedLanguages) {
    innerHTML = Prism.highlight(code, language.convertToPrism(), language.name).addLineNumbers()
}


private fun String.addLineNumbers() = this
    .split('\n')
    .mapIndexed { num, line -> "<span style=\"pointer-events: none; user-select: none; width: 3em; display: inline-block;\">${(num + 1)}</span>$line"  }
    .joinToString ('\n'.toString())

private fun SupportedLanguages.convertToPrism() = when(this){
    SupportedLanguages.XML -> Prism.languages.xml
    SupportedLanguages.Kotlin -> Prism.languages.kotlin
    SupportedLanguages.Properties -> Prism.languages.properties
    SupportedLanguages.Txt -> Prism.languages.txt
    SupportedLanguages.Shell -> Prism.languages.shell
    SupportedLanguages.Java -> Prism.languages.java
    SupportedLanguages.Ignore -> Prism.languages.ignore
    SupportedLanguages.ObjectiveC -> Prism.languages.objc
    SupportedLanguages.Swift -> Prism.languages.swift
    SupportedLanguages.Groovy -> Prism.languages.groovy
}