package ${packagePrefix}.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${packagePrefix}.mapper.${table.javaName}Mapper;
import ${packagePrefix}.domain.${table.javaName};


/**
 * @author liu, jia
 * @version ${generatorTime}
 * @date ${generatorTime}
 */
@Service
@Transactional
public class ${table.javaName}Service{

	@Resource
	private ${table.javaName}Mapper ${table.variableName}Mapper;
	
	public ${table.javaName} getEntity(${table.javaName} t) {
		return this.${table.variableName}Mapper.selectEntity(t);
	}

	public Integer getEntityCount(${table.javaName} t) {
		return this.${table.variableName}Mapper.selectEntityCount(t);
	}

	public List<${table.javaName}> getEntityList(${table.javaName} t) {
		return this.${table.variableName}Mapper.selectEntityList(t);
	}

	public Integer createEntity(${table.javaName} t) {
        this.${table.variableName}Mapper.insertEntity(t);
		return t.getId();
	}

	public int updateEntity(${table.javaName} t) {
		return this.${table.variableName}Mapper.updateEntity(t);
	}
	
	public int removeEntity(${table.javaName} t) {
		return this.${table.variableName}Mapper.deleteEntity(t);
	}

	public List<${table.javaName}> getEntityPaginatedList(${table.javaName} t) {
		return this.${table.variableName}Mapper.selectEntityPaginatedList(t);
	}

}
