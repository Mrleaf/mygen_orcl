package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

public class ExampleWhereClauseElementMyGenerator  extends AbstractXmlElementGenerator {
	
	private boolean isForUpdateByExample;
	
	public ExampleWhereClauseElementMyGenerator(boolean isForUpdateByExample){
		super();
        this.isForUpdateByExample = isForUpdateByExample;
	}

	@Override
	public void addElements(XmlElement parentElement) {
    	if(!isForUpdateByExample){
    		// By leaf
    		return ;
    	}
		
		
		
		XmlElement answer = new XmlElement("sql");
		answer.addAttribute(new Attribute("id", introspectedTable.getMyBatis3UpdateByExampleWhereClauseId())); 
		
		XmlElement whereElement = new XmlElement("where"); //$NON-NLS-1$
        answer.addElement(whereElement);
		
        for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
        	eq(whereElement, introspectedColumn);
        	if(introspectedColumn.isJdbcCharacterColumn() || introspectedColumn.isStringColumn()  || introspectedColumn.isIntegerNumber() ){
        	in(whereElement, introspectedColumn);
        	}
        }
        
        for (IntrospectedColumn introspectedColumn : introspectedTable.getNonPrimaryKeyColumns()) {
            eq(whereElement, introspectedColumn);
            if(introspectedColumn.isJdbcCharacterColumn() || introspectedColumn.isStringColumn() || introspectedColumn.isIntegerNumber() ){
            in(whereElement, introspectedColumn);
            }
        }
		
        parentElement.addElement(answer);
	}

	
	private void eq(XmlElement whereElement, IntrospectedColumn introspectedColumn){
		XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
		StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        sb.append(introspectedColumn.getJavaProperty("example."));
        sb.append(" != null"); //$NON-NLS-1$
        isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
        whereElement.addElement(isNotNullElement);

        sb.setLength(0);
        sb.append(" and ");
        sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
        sb.append(" = "); //$NON-NLS-1$
        sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "example."));
        isNotNullElement.addElement(new TextElement(sb.toString()));
	}
	
	private void ge(XmlElement whereElement, IntrospectedColumn introspectedColumn){
		XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
		StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        sb.append(introspectedColumn.getJavaProperty("example."));
        sb.append(" != null"); //$NON-NLS-1$
        isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
        whereElement.addElement(isNotNullElement);

        sb.setLength(0);
        sb.append(" and ");
        sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
        sb.append(" >= "); //$NON-NLS-1$
        sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "example."));
        isNotNullElement.addElement(new TextElement(sb.toString()));
	}
	
	private void le(XmlElement whereElement, IntrospectedColumn introspectedColumn){
		XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
		StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        sb.append(introspectedColumn.getJavaProperty("example."));
        sb.append(" != null"); //$NON-NLS-1$
        isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
        whereElement.addElement(isNotNullElement);

        sb.setLength(0);
        sb.append(" and ");
        sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
        sb.append(" <= "); //$NON-NLS-1$
        sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "example."));
        isNotNullElement.addElement(new TextElement(sb.toString()));
	}
	
	private void in(XmlElement whereElement, IntrospectedColumn introspectedColumn){
		XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
		StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        sb.append(introspectedColumn.getJavaProperty("example.")+"List");
        sb.append(" != null "); //$NON-NLS-1$
        sb.append(" and  ");
        sb.append(introspectedColumn.getJavaProperty("example.")+"List");
        sb.append(".size() > 0 ");
        isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
        whereElement.addElement(isNotNullElement);

        sb.setLength(0);
        sb.append(" and ");
        sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
        sb.append(" in "); //$NON-NLS-1$
        isNotNullElement.addElement(new TextElement(sb.toString()));
        
        XmlElement innerForEach = new XmlElement("foreach"); //$NON-NLS-1$
        innerForEach.addAttribute(new Attribute("collection", introspectedColumn.getJavaProperty("example.")+"List")); //$NON-NLS-1$ //$NON-NLS-2$
        innerForEach.addAttribute(new Attribute("item", "listItem")); //$NON-NLS-1$ //$NON-NLS-2$
        innerForEach.addAttribute(new Attribute("open", "(")); //$NON-NLS-1$ //$NON-NLS-2$
        innerForEach.addAttribute(new Attribute("close", ")")); //$NON-NLS-1$ //$NON-NLS-2$
        innerForEach.addAttribute(new Attribute("separator", ",")); //$NON-NLS-1$ //$NON-NLS-2$
        sb.setLength(0);
        sb.append("#{listItem");
        sb.append('}');
        innerForEach.addElement(new TextElement(sb.toString()));
        
        isNotNullElement.addElement(innerForEach);
	}
}
