package cn.tjucic.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
 
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ExcelReader {
	
	//*************xlsx�ļ���ȡ����************************
	//excel_nameΪ�ļ�����argΪ��Ҫ��ѯ���к�
	//���ض�ά�ַ�������
	@SuppressWarnings({ "resource", "unused" })
	public ArrayList<ArrayList<String>> xlsx_reader(String excel_url,int ... args) throws IOException {
 
        //��ȡxlsx�ļ�
        XSSFWorkbook xssfWorkbook = null;
        //Ѱ��Ŀ¼��ȡ�ļ�
        File excelFile = new File(excel_url); 
        InputStream is = new FileInputStream(excelFile);
        xssfWorkbook = new XSSFWorkbook(is);
      
        if(xssfWorkbook==null){
        	System.out.println("δ��ȡ������,����·����");
        	return null;
        }
        
        ArrayList<ArrayList<String>> ans=new ArrayList<ArrayList<String>>();
        //����xlsx�е�sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // ����ÿ��sheet����ȡ���е�ÿһ��
            for (int rowNum = 2; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) continue; 
                ArrayList<String> curarr=new ArrayList<String>();
                for(int columnNum = 0 ; columnNum<args.length ; columnNum++){
                	XSSFCell cell = xssfRow.getCell(args[columnNum]);
                	
                	curarr.add( Trim_str( getValue(cell) ) );
                }
                ans.add(curarr);
            }
        }
        return ans;
    }
    
    //�жϺ�׺Ϊxlsx��excel�ļ���������
    @SuppressWarnings("deprecation")
	private static String getValue(XSSFCell xssfRow) {
    	CellType cellType = CellType.NUMERIC;
    	if(xssfRow==null){
    		return "---";
    	}
        if (xssfRow.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } 
    	else if (xssfRow.getCellType() == CellType.NUMERIC) {
        	double cur=xssfRow.getNumericCellValue();
        	long longVal = Math.round(cur);
        	Object inputValue = null;
        	if(Double.parseDouble(longVal + ".0") == cur)  
        	        inputValue = longVal;  
        	else  
        	        inputValue = cur; 
            return String.valueOf(inputValue);
    	
        } else if(xssfRow.getCellType() == CellType.BLANK || xssfRow.getCellType() == CellType.ERROR){
        	return "---";
        }
        else {
        	//System.out.println(xssfRow.getCellType());
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }
    
    //�жϺ�׺Ϊxls��excel�ļ�����������
    @SuppressWarnings("deprecation")
	private static String getValue(HSSFCell hssfCell) {
    	if(hssfCell==null){
    		return "---";
    	}
        if (hssfCell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } 
    	else if (hssfCell.getCellType() == CellType.NUMERIC) {
        	double cur=hssfCell.getNumericCellValue();
        	long longVal = Math.round(cur);
        	Object inputValue = null;
        	if(Double.parseDouble(longVal + ".0") == cur)  
        	        inputValue = longVal;  
        	else  
        	        inputValue = cur; 
            return String.valueOf(inputValue);
    	
        } else if(hssfCell.getCellType() == CellType.BLANK || hssfCell.getCellType() == CellType.ERROR){
        	return "---";
        }
        else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
    
  //�ַ����޼�  ȥ�����пհ׷��� �� �ʺ� �� ���Ŀո�
    static private String Trim_str(String str){
        if(str==null)
            return null;
        return str.replaceAll("[\\s\\?]", "").replace("��", "");
    }
}

