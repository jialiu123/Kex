${table.variableName}数字字典
		| 字段 | 类型 | 注释 |
<#list table.columeList as col>
		|    ${col.lowerCaseName}    |    ${col.javaType}   (${col.fieldLength})   |    ${col.remarks}    |
</#list>