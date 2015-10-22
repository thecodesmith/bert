package com.thecodesmith.bert.dsl

import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.codehaus.groovy.control.CompilerConfiguration

class ScriptRunner {

    static void main(args) {
        runScript args[0]
    }

    static void runScript(String filename) {
        shell.evaluate new File(filename)
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
        configuration.scriptBaseClass = RobotBaseScript.class.name
        configuration
    }

    static ImportCustomizer getImportCustomizer() {
        def importCustomizer = new ImportCustomizer()
        importCustomizer.addStarImport 'com.thecodesmith.bert.dsl'
        importCustomizer
    }
}
