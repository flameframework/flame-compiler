<#list nativeActions as action>
<&output file="${action.name}Action.swift">
//
// File generated by Flame.
//

class ${action.name}Action {

    class func perform(<#list action.inputVariables as input>${input.name} : ${input.type}<#if input_has_next>, </#if></#list>)<#if action.outputType?has_content> -> ${action.outputType}</#if> {
        // add your implementation here
    }

}
</&output>
</#list>