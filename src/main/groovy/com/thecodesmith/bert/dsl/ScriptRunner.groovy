package com.thecodesmith.bert.dsl

import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.codehaus.groovy.control.CompilerConfiguration

class ScriptRunner {

    static void main(args) {
        def filename = args[0]
        runScript new File(filename).text
    }

    static runScript(String script) {
        shell.evaluate script
    }

    static GroovyShell getShell() {
        new GroovyShell(binding, configuration)
    }

    static Binding getBinding() {
        new Binding(Direction.values().collectEntries { [(it.name()): it] })
    }

    static CompilerConfiguration getConfiguration() {
        def configuration = new CompilerConfiguration()
        configuration.addCompilationCustomizers(importCustomizer)
        configuration.scriptBaseClass = Robot.class.name
        configuration
    }

    static ImportCustomizer getImportCustomizer() {
        def importCustomizer = new ImportCustomizer()
        importCustomizer.addStarImport 'com.thecodesmith.bert.dsl'
        importCustomizer
    }
}
