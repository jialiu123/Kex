<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- @author liu,jia -->
<mapper namespace="com.moba.mapper.${table.javaName}Mapper">

	<resultMap id="${table.variableName}ResultForList" type="${table.javaName}">
		<#list table.columeList as col>
		<result column="${col.name}" property="${col.lowerCaseName}" jdbcType="${col.jdbcType}" />
		</#list>
	</resultMap>

	<resultMap id="${table.variableName}Result" extends="${table.variableName}ResultForList" type="${table.javaName}">
	</resultMap>

	<sql id="sf-${table.variableName}">
	 <#list table.columeList as col>
	  <#if col.jdbcType != 'VARCHAR'>
		<if test="${col.lowerCaseName} != null"> AND a.${col.name} = ${r"#{"}${col.lowerCaseName}${r"}"}</if>
	  <#else>
		<if test="${col.lowerCaseName} != null and ${col.lowerCaseName} != ''"> AND a.${col.name} = ${r"#{"}${col.lowerCaseName}${r"}"}</if>
	  </#if>
	 </#list>
	</sql>

	<select id="selectEntity" resultMap="${table.variableName}Result" >
		select * from ${table.name} a where 1 = 1
		<include refid="sf-${table.variableName}" />
	</select>

	<select id="selectEntityList" resultMap="${table.variableName}ResultForList">
		select * from ${table.name} a where 1 = 1
		<include refid="sf-${table.variableName}" />
		order by a.ID desc
		<if test="row.count != null and row.count != ''">limit 0, ${r"${row.count}"}</if>
	</select>

	<select id="selectEntityCount" resultType="int">
		select count(*) from ${table.name} a where 1 = 1
		<include refid="sf-${table.variableName}" />
	</select>

	<select id="selectEntityPaginatedList" resultMap="${table.variableName}Result">
		select * from ${table.name} a where 1 = 1
		<include refid="sf-${table.variableName}" />
		order by a.ID desc
		<if test="row.count != null and row.count != ''">limit ${r"${row.first}"}, ${r"${row.count}"}</if>
	</select>

	<insert id="insertEntity" useGeneratedKeys="true" keyProperty="id">
		<![CDATA[insert into ${table.name} (]]>
		<trim suffixOverrides=",">
			<#list table.columeList as col>
			<if test="${col.lowerCaseName} != null">${col.name},</if>
			</#list>
		</trim>
		<![CDATA[) values (]]>
		<trim suffixOverrides=",">
			<#list table.columeList as col>
			<if test="${col.lowerCaseName} != null">${r"#{"}${col.lowerCaseName}${r"}"},</if>
			</#list>
		</trim>
		<![CDATA[)]]>
	</insert>

	<update id="updateEntity">
		update ${table.name}
		<set>
			<#list table.columeList as col>
			<if test="${col.lowerCaseName} != null">${col.name} = ${r"#{"}${col.lowerCaseName}${r"}"},</if>
			</#list>
		</set>
		<where>
		<if test="id != null">ID = ${r"#{id}"}</if>
		<if test="map.pks != null">
		  ID in
		  <foreach collection="map.pks" index="index" item="item" open="(" separator="," close=")">
		   ${r"#{item}"}
		  </foreach>
		 </if>
		</where>
	</update>

	<delete id="deleteEntity">
		delete from ${table.name} where 1 = 1
		<if test="id != null"> AND ID = ${r"#{id}"}</if>
		<if test="map.pks != null">
		  AND ID in
		  <foreach collection="map.pks" index="index" item="item" open="(" separator="," close=")">
		   ${r"#{item}"}
		  </foreach>
		</if>
	</delete>

</mapper>