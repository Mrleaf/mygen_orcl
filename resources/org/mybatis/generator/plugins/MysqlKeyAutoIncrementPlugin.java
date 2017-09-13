package org.mybatis.generator.plugins;

import java.sql.Types;
import java.util.LinkedList;
import java.util.List;  

import org.mybatis.generator.api.CommentGenerator;  
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;  
import org.mybatis.generator.api.PluginAdapter;  
import org.mybatis.generator.api.dom.java.Field;  
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;  
import org.mybatis.generator.api.dom.java.Method;  
import org.mybatis.generator.api.dom.java.Parameter;  
import org.mybatis.generator.api.dom.java.PrimitiveTypeWrapper;  
import org.mybatis.generator.api.dom.java.TopLevelClass;  
import org.mybatis.generator.api.dom.xml.Attribute;  
import org.mybatis.generator.api.dom.xml.TextElement;  
import org.mybatis.generator.api.dom.xml.XmlElement;
  
/**
 * 查询mysql自增长列的值
 * 
 * @author leaf
 * @since 2014-11-8
 */
public class MysqlKeyAutoIncrementPlugin extends PluginAdapter {
	
    
    // change to return the primaryKey to the Enity for insert element
    @Override
	public boolean sqlMapInsertElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
    	
    	List<IntrospectedColumn> keys=introspectedTable.getPrimaryKeyColumns();
    	
		if(keys.size()>1 || keys.size()==0){
			return super.sqlMapInsertElementGenerated(element, introspectedTable);
		}
    	
    	IntrospectedColumn keyColum = keys.get(0);
    	
    	int jdbcType = keyColum.getJdbcType();
    	String keyProperty = keyColum.getJavaProperty();
    	
    	if(jdbcType==Types.INTEGER || jdbcType==Types.BIGINT){
    		XmlElement isNotNullElement = new XmlElement("selectKey"); //$NON-NLS-1$  
            isNotNullElement.addAttribute(new Attribute("resultType", "java.lang.Integer")); //$NON-NLS-1$ //$NON-NLS-2$  
            isNotNullElement.addAttribute(new Attribute("order", "AFTER")); //$NON-NLS-1$ //$NON-NLS-2$  
            isNotNullElement.addAttribute(new Attribute("keyProperty", "record."+keyProperty)); //$NON-NLS-1$ //$NON-NLS-2$  
            isNotNullElement.addElement(new TextElement("SELECT LAST_INSERT_ID()"));  
            element.addElement(isNotNullElement);
    	}
    	
    	
		return super.sqlMapInsertElementGenerated(element, introspectedTable);
	}
    
    
    // change to return the primaryKey to the Enity for insert selective element
	@Override
	public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		List<IntrospectedColumn> keys=introspectedTable.getPrimaryKeyColumns();
		
		if(keys.size()>1 || keys.size()==0){
			return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
		}
    	
    	IntrospectedColumn keyColum = keys.get(0);
    	
    	int jdbcType = keyColum.getJdbcType();
    	String keyProperty = keyColum.getJavaProperty();
    	
    	if(jdbcType==Types.INTEGER || jdbcType==Types.BIGINT){
    		XmlElement isNotNullElement = new XmlElement("selectKey"); //$NON-NLS-1$  
            isNotNullElement.addAttribute(new Attribute("resultType", "java.lang.Integer")); //$NON-NLS-1$ //$NON-NLS-2$  
            isNotNullElement.addAttribute(new Attribute("order", "AFTER")); //$NON-NLS-1$ //$NON-NLS-2$  
            isNotNullElement.addAttribute(new Attribute("keyProperty", "record."+keyProperty)); //$NON-NLS-1$ //$NON-NLS-2$  
            isNotNullElement.addElement(new TextElement("SELECT LAST_INSERT_ID()"));  
            element.addElement(isNotNullElement);
    	}
		
		return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
	}
	
	
  
	
	
    /** 
     * This plugin is always valid - no properties are required 
     */  
    public boolean validate(List<String> warnings) {  
        return true;  
    }  
    
}  