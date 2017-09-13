/*
 *  Copyright 2009 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mybatis.generator.codegen.mybatis3.xmlmapper;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.List;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.BaseColumnListElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.BlobColumnListElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.CountByExampleElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.DeleteByExampleElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.DeleteByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ExampleWhereClauseElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ExampleWhereClauseElementMyGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.InsertElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.InsertSelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ResultMapWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ResultMapWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SelectByExampleWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SelectByExampleWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SelectByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByExampleSelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByExampleWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByExampleWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeySelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeyWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeyWithoutBLOBsElementGenerator;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.TableConfiguration;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class ReadXMLMapperGenerator extends AbstractXmlGenerator {

    public ReadXMLMapperGenerator() {
        super();
    }

    protected XmlElement getSqlMapElement() {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString("Progress.12", table.toString())); //$NON-NLS-1$
        XmlElement answerRead = new XmlElement("mapper"); //$NON-NLS-1$
        String namespace = introspectedTable.getMyBatis3SqlMapNamespaceRead();  // introspectedTable.getMyBatis3SqlMapNamespace();
        answerRead.addAttribute(new Attribute("namespace", namespace)); //$NON-NLS-1$

        context.getCommentGenerator().addRootComment(answerRead);

        addResultMapWithoutBLOBsElement(answerRead);
        addResultMapWithBLOBsElement(answerRead);
        sqlMapperXmlHashMapResultMapGenerated(answerRead);
        
        addExampleWhereClauseElement(answerRead);
        addMyBatis3UpdateByExampleWhereClauseElement(answerRead);
        
        addBaseColumnListElement(answerRead);
        addBlobColumnListElement(answerRead);
        addSelectByExampleWithBLOBsElement(answerRead);
        addSelectByExampleWithoutBLOBsElement(answerRead);
        addSelectByPrimaryKeyElement(answerRead);
        addCountByExampleElement(answerRead);
        
        sqlMapperXmlselectByExampleForOne(answerRead);
        sqlMapperXmlselectMapByPrimaryKey(answerRead);
        sqlMapperXmlselectMapByExampleForOne(answerRead);
        sqlMapperXmlselectMapByExample(answerRead);
        
        //addDeleteByPrimaryKeyElement(answerRead);
        //addDeleteByExampleElement(answerRead);
        //addInsertElement(answerRead);
        //addInsertSelectiveElement(answerRead);
        //addUpdateByExampleSelectiveElement(answerRead);
        //addUpdateByExampleWithBLOBsElement(answerRead);
        //addUpdateByExampleWithoutBLOBsElement(answerRead);
        //addUpdateByPrimaryKeySelectiveElement(answerRead);
        //addUpdateByPrimaryKeyWithBLOBsElement(answerRead);
        //addUpdateByPrimaryKeyWithoutBLOBsElement(answerRead);

        return answerRead;
    }
    
	private void sqlMapperXmlHashMapResultMapGenerated(XmlElement rootElement){
		
		
		XmlElement resultMap = new XmlElement("resultMap");
		resultMap.addAttribute(new Attribute("id","HashMapBaseResultMap"));
		resultMap.addAttribute(new Attribute("type","hashmap"));
		
		for(IntrospectedColumn  column : introspectedTable.getPrimaryKeyColumns()){
			XmlElement idElement = new XmlElement("id");
			idElement.addAttribute(new Attribute("column",column.getActualColumnName()));
			idElement.addAttribute(new Attribute("property",column.getJavaProperty()));
			idElement.addAttribute(new Attribute("jdbcType",column.getJdbcTypeName()));
			resultMap.addElement(idElement);
		}

		for(IntrospectedColumn  column : introspectedTable.getNonPrimaryKeyColumns()){
			XmlElement resultElement = new XmlElement("result");
			resultElement.addAttribute(new Attribute("column",column.getActualColumnName()));
			resultElement.addAttribute(new Attribute("property",column.getJavaProperty()));
			resultElement.addAttribute(new Attribute("jdbcType",column.getJdbcTypeName()));
			resultMap.addElement(resultElement);
		}
		
		/*
		for(IntrospectedColumn  column : introspectedTable.getNonBLOBColumns()){
			XmlElement resultElement = new XmlElement("result");
			resultElement.addAttribute(new Attribute("column",column.getActualColumnName()));
			resultElement.addAttribute(new Attribute("property",column.getJavaProperty()));
			resultElement.addAttribute(new Attribute("jdbcType",column.getJdbcTypeName()));
			resultMap.addElement(resultElement);
		}
		
		for(IntrospectedColumn  column : introspectedTable.getBLOBColumns()){
			XmlElement resultElement = new XmlElement("result");
			resultElement.addAttribute(new Attribute("column",column.getActualColumnName()));
			resultElement.addAttribute(new Attribute("property",column.getJavaProperty()));
			resultElement.addAttribute(new Attribute("jdbcType",column.getJdbcTypeName()));
			resultMap.addElement(resultElement);
		}
		*/
		rootElement.addElement(1, resultMap);
	}
	
	private void sqlMapperXmlselectByExampleForOne(XmlElement rootElement){

		
		
		//selectByExampleForOne
		XmlElement selectOne = new XmlElement("select");
		selectOne.addAttribute(new Attribute("id", "selectByExampleForOne"));
		if(introspectedTable.hasBLOBColumns()){
			selectOne.addAttribute(new Attribute("resultMap", introspectedTable.getResultMapWithBLOBsId()));
		}else{
			selectOne.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
		}
		
		selectOne.addAttribute(new Attribute("parameterType", "map")); //By leaf introspectedTable.getExampleType()
		selectOne.addElement(new TextElement("select"));
		
		// distinct
		XmlElement ifOne = new XmlElement("if");
		ifOne.addAttribute(new Attribute("test", "example.distinct"));
		ifOne.addElement(new TextElement("distinct"));
		selectOne.addElement(ifOne);
		
		{
		// include Base_Column_List
		XmlElement includeXml = new XmlElement("include");
		includeXml.addAttribute(new Attribute("refid", introspectedTable.getBaseColumnListId()));
		selectOne.addElement(includeXml);
		}
		
		if(introspectedTable.hasBLOBColumns()){
			selectOne.addElement(new TextElement(","));
			XmlElement includeXml = new XmlElement("include");
			includeXml.addAttribute(new Attribute("refid", introspectedTable.getBlobColumnListId()));
			selectOne.addElement(includeXml);
		}
		
		// text table
		selectOne.addElement(new TextElement("from " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));

		// _parameter
		XmlElement ifTwo = new XmlElement("if");
		ifTwo.addAttribute(new Attribute("test", "_parameter != null"));
		
		XmlElement includeRefXml = new XmlElement("include");
		includeRefXml.addAttribute(new Attribute("refid", introspectedTable.getMyBatis3UpdateByExampleWhereClauseId()));
		ifTwo.addElement(includeRefXml);
		
		
		ifTwo.addElement(new TextElement(" and rownum = 1"));//leaf
		
		selectOne.addElement(ifTwo);
		
		// orderByClause
		XmlElement ifThree = new XmlElement("if");
		ifThree.addAttribute(new Attribute("test", "example.orderByClause != null"));
		ifThree.addElement(new TextElement("order by ${example.orderByClause}"));
		selectOne.addElement(ifThree);
//		selectOne.addElement(new TextElement(" limit 0 , 1 "));//leaf
		rootElement.addElement(selectOne);
	
	}
	
	private void sqlMapperXmlselectMapByPrimaryKey(XmlElement rootElement) {
		List<IntrospectedColumn> keys = introspectedTable.getPrimaryKeyColumns();
		
		int keySize=keys.size();
		System.out.printf("%-40s  PrimaryKey Count:[%2d]  \n",introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime(),keySize );
		
		if(keySize == 0){
			System.out.println(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()+" no primary key!");
			return ;
		}
		
		TableConfiguration tableConfiguration = introspectedTable.getTableConfiguration();
		
		//selectMapByPrimaryKey
		XmlElement select = new XmlElement("select");
		select.addAttribute(new Attribute("id", "selectMapByPrimaryKey"));
		select.addAttribute(new Attribute("resultMap", "HashMapBaseResultMap"));
		
		if(keySize == 1){
			IntrospectedColumn key = keys.get(0);
			select.addAttribute(new Attribute("parameterType", "map")); //By leaf key.getFullyQualifiedJavaType().getFullyQualifiedName() ) );
		}else if(keySize > 1){
			if(tableConfiguration.getModelType() == ModelType.FLAT){
				select.addAttribute( new Attribute("parameterType",  "map") );
			}else{
				select.addAttribute( new Attribute("parameterType",  "map")); //By leaf introspectedTable.getPrimaryKeyType() ) );
			}
		}
		
		select.addElement(new TextElement("select"));
		
		{
		// include Base_Column_List
		XmlElement includeXml = new XmlElement("include");
		includeXml.addAttribute(new Attribute("refid", introspectedTable.getBaseColumnListId()));
		select.addElement(includeXml);
		}
		
		if(introspectedTable.hasBLOBColumns()){
			select.addElement(new TextElement(","));
			XmlElement includeXml = new XmlElement("include");
			includeXml.addAttribute(new Attribute("refid", introspectedTable.getBlobColumnListId()));
			select.addElement(includeXml);
		}
		
		// text table
		select.addElement(new TextElement("from " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));
		XmlElement where = new XmlElement("where");
		
		for(IntrospectedColumn key : keys){
			where.addElement(new TextElement(" and "+key.getActualColumnName()+"=#{"+key.getJavaProperty()+",jdbcType="+key.getJdbcTypeName()+"}"));
		}
		select.addElement(where);
		
		rootElement.addElement(select);
	
	}
	
	private void sqlMapperXmlselectMapByExampleForOne(XmlElement rootElement) {

		//selectMapByExampleForOne
		XmlElement select = new XmlElement("select");
		select.addAttribute(new Attribute("id", "selectMapByExampleForOne"));
		select.addAttribute(new Attribute("resultMap", "HashMapBaseResultMap"));
		select.addAttribute(new Attribute("parameterType", "map")); //By leaf introspectedTable.getExampleType()));
		select.addElement(new TextElement("select"));
		
		// distinct
		XmlElement ifOne = new XmlElement("if");
		ifOne.addAttribute(new Attribute("test", "example.distinct"));
		ifOne.addElement(new TextElement("distinct"));
		select.addElement(ifOne);
		
		{
		// include Base_Column_List
		XmlElement includeXml = new XmlElement("include");
		includeXml.addAttribute(new Attribute("refid", introspectedTable.getBaseColumnListId()));
		select.addElement(includeXml);
		}
		
		if(introspectedTable.hasBLOBColumns()){
			select.addElement(new TextElement(","));
			XmlElement includeXml = new XmlElement("include");
			includeXml.addAttribute(new Attribute("refid", introspectedTable.getBlobColumnListId()));
			select.addElement(includeXml);
		}
		
		
		// text table
		select.addElement(new TextElement("from " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));

		// _parameter
		XmlElement ifTwo = new XmlElement("if");
		ifTwo.addAttribute(new Attribute("test", "_parameter != null"));
		
		XmlElement includeRefXml = new XmlElement("include");
		includeRefXml.addAttribute(new Attribute("refid", introspectedTable.getMyBatis3UpdateByExampleWhereClauseId()));
		ifTwo.addElement(includeRefXml);
		
		ifTwo.addElement(new TextElement(" and rownum = 1"));//leaf
		
		select.addElement(ifTwo);
		
		// orderByClause
		XmlElement ifThree = new XmlElement("if");
		ifThree.addAttribute(new Attribute("test", "example.orderByClause != null"));
		ifThree.addElement(new TextElement("order by ${example.orderByClause}"));
		select.addElement(ifThree);
//		select.addElement(new TextElement(" limit 0 , 1 "));//leaf
		rootElement.addElement(select);
	
	}
	
	private void sqlMapperXmlselectMapByExample(XmlElement rootElement) {

		
		//selectMapByExample
		XmlElement selectMapList = new XmlElement("select");
		selectMapList.addAttribute(new Attribute("id", "selectMapByExample"));
		if(introspectedTable.hasBLOBColumns()){
			selectMapList.addAttribute(new Attribute("resultMap", "HashMapBaseResultMap"));
		}else{
			selectMapList.addAttribute(new Attribute("resultMap", "HashMapBaseResultMap"));
		}
		
		selectMapList.addAttribute(new Attribute("parameterType","map")); //By leaf introspectedTable.getExampleType()));
		selectMapList.addElement(new TextElement("select"));
		
		// distinct
		XmlElement ifOne = new XmlElement("if");
		ifOne.addAttribute(new Attribute("test", "example.distinct"));
		ifOne.addElement(new TextElement("distinct"));
		selectMapList.addElement(ifOne);
		
		{
		// include Base_Column_List
		XmlElement includeXml = new XmlElement("include");
		includeXml.addAttribute(new Attribute("refid", introspectedTable.getBaseColumnListId()));
		selectMapList.addElement(includeXml);
		}
		
		if(introspectedTable.hasBLOBColumns()){
			selectMapList.addElement(new TextElement(","));
			XmlElement includeXml = new XmlElement("include");
			includeXml.addAttribute(new Attribute("refid", introspectedTable.getBlobColumnListId()));
			selectMapList.addElement(includeXml);
		}
		
		// text table
		selectMapList.addElement(new TextElement("from " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));

		// _parameter
		XmlElement ifTwo = new XmlElement("if");
		ifTwo.addAttribute(new Attribute("test", "_parameter != null"));
		
		XmlElement includeRefXml = new XmlElement("include");
		includeRefXml.addAttribute(new Attribute("refid", introspectedTable.getMyBatis3UpdateByExampleWhereClauseId()));
		ifTwo.addElement(includeRefXml);
		
		selectMapList.addElement(ifTwo);
		
		// orderByClause
		XmlElement ifThree = new XmlElement("if");
		ifThree.addAttribute(new Attribute("test", "example.orderByClause != null"));
		ifThree.addElement(new TextElement("order by ${example.orderByClause}"));
		selectMapList.addElement(ifThree);
		
//		XmlElement pageElement = new XmlElement("if"); //$NON-NLS-1$  
//		pageElement.addAttribute(new Attribute("test", "example.limitStart >= 0 and example.pageSize > 0 ")); //$NON-NLS-1$ //$NON-NLS-2$  
//		pageElement.addElement(new TextElement(  
//                "limit #{example.limitStart} , #{example.pageSize}"));  
//        selectMapList.addElement(pageElement);//leaf
		
		rootElement.addElement(selectMapList);
	
	}


    protected void addResultMapWithoutBLOBsElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateBaseResultMap()) {
            AbstractXmlElementGenerator elementGenerator = new ResultMapWithoutBLOBsElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addResultMapWithBLOBsElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateResultMapWithBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new ResultMapWithBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addExampleWhereClauseElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateSQLExampleWhereClause()) {
            AbstractXmlElementGenerator elementGenerator = new ExampleWhereClauseElementMyGenerator(false);// new ExampleWhereClauseElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addMyBatis3UpdateByExampleWhereClauseElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules()
                .generateMyBatis3UpdateByExampleWhereClause()) {
            AbstractXmlElementGenerator elementGenerator = new ExampleWhereClauseElementMyGenerator(true); // new ExampleWhereClauseElementGenerator(true);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addBaseColumnListElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateBaseColumnList()) {
            AbstractXmlElementGenerator elementGenerator = new BaseColumnListElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addBlobColumnListElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateBlobColumnList()) {
            AbstractXmlElementGenerator elementGenerator = new BlobColumnListElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addSelectByExampleWithoutBLOBsElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules().generateSelectByExampleWithoutBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new SelectByExampleWithoutBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addSelectByExampleWithBLOBsElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateSelectByExampleWithBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new SelectByExampleWithBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addSelectByPrimaryKeyElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateSelectByPrimaryKey()) {
            AbstractXmlElementGenerator elementGenerator = new SelectByPrimaryKeyElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addDeleteByExampleElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateDeleteByExample()) {
            AbstractXmlElementGenerator elementGenerator = new DeleteByExampleElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addDeleteByPrimaryKeyElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateDeleteByPrimaryKey()) {
            AbstractXmlElementGenerator elementGenerator = new DeleteByPrimaryKeyElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addInsertElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateInsert()) {
            AbstractXmlElementGenerator elementGenerator = new InsertElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addInsertSelectiveElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateInsertSelective()) {
            AbstractXmlElementGenerator elementGenerator = new InsertSelectiveElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addCountByExampleElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateCountByExample()) {
            AbstractXmlElementGenerator elementGenerator = new CountByExampleElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByExampleSelectiveElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateUpdateByExampleSelective()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByExampleSelectiveElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByExampleWithBLOBsElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateUpdateByExampleWithBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByExampleWithBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByExampleWithoutBLOBsElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules().generateUpdateByExampleWithoutBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByExampleWithoutBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByPrimaryKeySelectiveElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeySelectiveElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByPrimaryKeyWithBLOBsElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByPrimaryKeyWithoutBLOBsElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules()
                .generateUpdateByPrimaryKeyWithoutBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithoutBLOBsElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void initializeAndExecuteGenerator(
            AbstractXmlElementGenerator elementGenerator,
            XmlElement parentElement) {
        elementGenerator.setContext(context);
        elementGenerator.setIntrospectedTable(introspectedTable);
        elementGenerator.setProgressCallback(progressCallback);
        elementGenerator.setWarnings(warnings);
        elementGenerator.addElements(parentElement);
    }

    @Override
    public Document getDocument() {
        Document document = new Document(
                XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,
                XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
        document.setRootElement(getSqlMapElement());

        if (!context.getPlugins().sqlMapDocumentGenerated(document,
                introspectedTable)) {
            document = null;
        }

        return document;
    }
}
