import com.thecodesmith.bert.dsl.ScriptRunner

// TODO: Error checking

result = ScriptRunner.runScript params.script

// TODO: Helpful responses on error

json {
    script params.script
    result result
}

redirect '/'
