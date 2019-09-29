package ${packagePrefix}.model;

<#--import io.swagger.annotations.ApiModel;-->
<#--import io.swagger.annotations.ApiModelProperty;-->
<#--import lombok.Data;-->

import java.io.Serializable;
<#list packageImportList as packageImport>
import ${packageImport};
</#list>
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author liu, jia
 * @version ${generatorTime}
 * @date ${generatorTime}
 */
<#--@ApiModel(value = "${table.javaName}")-->
<#--@Data-->
public class ${table.javaName} extends BaseEntity {

	<#list table.columeList as col>
	<#--@ApiModelProperty(value = "${col.remarks}")-->
	private ${col.javaType} ${col.lowerCaseName};

	</#list>

	<#list table.columeList as col>

	public ${col.javaType} get${col.javaName}() {
	  return ${col.lowerCaseName};
	}

	public void set${col.javaName}(${col.javaType} ${col.lowerCaseName}) {
	  this.${col.lowerCaseName} = ${col.lowerCaseName};
	}
	</#list>
}