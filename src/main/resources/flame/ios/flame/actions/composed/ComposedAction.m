<#list composedActions as action>
<&output file="${action.name}Action.m">
//
// File generated by Flame.
//

#import "../../../flame.h"


@implementation ${action.name}Action

+ (${action.outputType}*) perform<#list action.inputVariables as input>${input.name}: (${input.type}*) ${input.name}<#if input_has_next> </#if></#list> {
    <#list action.actionCalls as actionCall>
    <#if actionCall.outputVariable?has_content>${actionCall.outputVariable.type} *${actionCall.outputVariable.name} = </#if>[${actionCall.action.name}Action perform<#list actionCall.inputValues as inputValue>${inputValue}: ${inputValue}<#if inputValue_has_next> </#if></#list>];
    </#list>
    <#if action.outputVariable?has_content>return *${action.outputVariable.name}</#if>;
}

@end
</&output>
</#list>
