<#list nativeActions as action>
<&output file="${action.name}Action.h">
//
// File generated by Flame.
//

@interface ${action.name}Action : NSObject

+ (${action.outputType}) perform<#list action.inputVariables as input>${input.name}: (${input.type}) ${input.name}<#if input_has_next> </#if></#list>;

@end
</&output>
</#list>