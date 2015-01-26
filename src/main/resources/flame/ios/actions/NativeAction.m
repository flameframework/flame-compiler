<#list nativeActions as action>
<&output file="${action.name}Action.m" overwrite="false">
//
// File generated by Flame.
//

#import "../generated/headers/${action.name}Action.h"


@implementation ${action.name}Action

+ (${action.outputType}) perform<#list action.inputVariables as input>${input.name.toUpperCamelCase}: (${input.type}) ${input.name}<#if input_has_next> </#if></#list> {
    // add your implementation here
}

@end
</&output>
</#list>