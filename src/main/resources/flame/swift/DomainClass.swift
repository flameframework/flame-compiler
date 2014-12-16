<#list domainClasses as domainClass>
<&output file="generated/domain/${domainClass.name}.swift">
//
// File generated by Flame.
//

import Foundation

class ${domainClass.name} {

    <#list domainClass.properties as property>
    var ${property.name} : ${property.type}

    </#list>

}
</&output>
</#list>

<#list nativeActions as action>
<&output file="actions/${action.name}Action.swift">
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

<#list composedActions as action>
<&output file="generated/actions/${action.name}Action.swift">
//
// File generated by Flame.
//

class ${action.name}Action {

    class func perform(<#list action.inputVariables as input>${input.name} : ${input.type}<#if input_has_next>, </#if></#list>)<#if action.outputType?has_content> -> ${action.outputType}</#if> {
       <#list action.actionCalls as actionCall>
       <#if actionCall.outputVariable?has_content>let ${actionCall.outputVariable.name} : ${actionCall.outputVariable.type} = </#if>${actionCall.action.name}Action.perform(<#list actionCall.inputValues as inputValue>${inputValue}<#if inputValue_has_next>,  </#if></#list>)
       </#list>
       <#if action.outputVariable?has_content>return ${action.outputVariable.name}</#if>
    }
}

</&output>
</#list>