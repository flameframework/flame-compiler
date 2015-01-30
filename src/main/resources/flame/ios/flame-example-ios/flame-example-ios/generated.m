<#-- TODO remove necessity to add &output tag here -->
<&output file=generated.m>
//
// File generated by Flame.
//

<#list domainClasses as domainClass>
#include "generated/domain/${domainClass.name}.m"
</#list>

<#list composedActions as action>
#include "generated/composed/${action.name}Action.m"
</#list>

<#list nativeActions as action>
#include "actions/${action.name}Action.m"
</#list>
</&output>