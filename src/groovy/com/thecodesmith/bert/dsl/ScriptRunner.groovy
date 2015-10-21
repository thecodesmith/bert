import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.codehaus.groovy.control.CompilerConfiguration

def binding = new Binding(
    com.thecodesmith.bert.dsl.Direction.values().collectEntries { [(it.name()): it] }
)

def importCustomizer = new ImportCustomizer()
importCustomizer.addStarImport 'com.thecodesmith.bert.dsl'

def configuration = new CompilerConfiguration()
configuration.addCompilationCustomizers(importCustomizer)

def shell = new GroovyShell(binding, configuration)
shell.evaluate new File('Script.groovy')
