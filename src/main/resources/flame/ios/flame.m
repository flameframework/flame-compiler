<#-- TODO remove necessity to add &output tag here -->
<&output file=flame.m>
//
// File generated by Flame.
//

<#list domainClasses as domainClass>
#include "flame/domain/${domainClass.name}.m"
</#list>

<#list composedActions as action>
#include "flame/actions/composed/${action.name}Action.m"
</#list>

<#list nativeActions as action>
#include "flame/actions/native/${action.name}Action.m"
</#list>
</&output>